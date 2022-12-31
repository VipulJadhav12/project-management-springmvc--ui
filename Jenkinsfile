pipeline{
    environment {
        def imageName = "krashnat922/afourathon2.0:projectmanagement-fe"
        mavenhome = tool name: 'Maven 3.5', type: 'maven'
    }
    agent any
    stages{
        stage("Build") {
          steps{
            script {
                sh "${mavenhome}/bin/mvn clean package -DskipTests"            
            }
        }
    }
        stage("Build Docker Image") {
          steps{
            script {
              docker.withRegistry("", "ktapdiya-dockerhub") {
                  sh "docker build --network=host -t ${imageName} ."
                }
            }
        }
    }

        stage("Push Image to Registry") {
          steps{
            script {
              docker.withRegistry("", "ktapdiya-dockerhub") {
                  sh "docker push ${imageName}"
                  sh "docker rmi --force \$(docker images -q ${imageName} | uniq)"
                }
            }
        }
    }

        stage("Deploy service on MightyMinions Cluster") {
          steps {

            /*
            kubernetesDeploy(kubeconfigId: 'mightyminions-kubeconfig',
               configs: 'projectmanagement-fe.yaml,projectmanagement-fe-svc.yaml',
               enableConfigSubstitution: true)
            */

            withKubeConfig( credentialsId: 'mightyminions-kubeconfig' ) {
            sh '''
                kubectl apply -f projectmanagement-fe.yaml -f projectmanagement-fe-svc.yaml
            '''
            
                }
            }
        }




    }
}