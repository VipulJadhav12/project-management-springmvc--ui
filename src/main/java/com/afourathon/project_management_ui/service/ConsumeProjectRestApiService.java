package com.afourathon.project_management_ui.service;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.afourathon.project_management_ui.data.entity.Project;
import com.afourathon.project_management_ui.data.payloads.request.ProjectRequest;
import com.afourathon.project_management_ui.data.payloads.response.ApiResponse;

@Service
public class ConsumeProjectRestApiService {

	@Autowired
	RestTemplate restTemplate;

	public List<Project> getAllProjects() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<List<Project>> entity = new HttpEntity<>(headers);
		
		URI uri = URI.create("http://localhost:9191/api/v1/projects/getAllBy=NONE");

		ResponseEntity<List<Project>> response = restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Project>>(){});
		
		List<Project> projects = response.getBody();
		
		return projects;
	}
	
	public Project getProjectById(Long projectId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Project> entity = new HttpEntity<>(headers);
		
		URI uri = URI.create("http://localhost:9191/api/v1/projects/getBy=ID/project/" + projectId);

		ResponseEntity<Project> response = restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<Project>(){});
		
		Project project = response.getBody();
		
		return project;
	}

	public ApiResponse addProject(ProjectRequest projectRequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<ProjectRequest> entity = new HttpEntity<>(projectRequest, headers);
		
		URI uri = URI.create("http://localhost:9191/api/v1/projects/add");
		
		ResponseEntity<ApiResponse> response = restTemplate.exchange(uri, HttpMethod.POST, entity, ApiResponse.class);
		
		ApiResponse apiResponse = response.getBody();
		
		System.out.println(apiResponse.toString());
		
		return apiResponse;
	}
	
	public ApiResponse updateProject(Long projectId, ProjectRequest projectRequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<ProjectRequest> entity = new HttpEntity<>(projectRequest, headers);
		
		URI uri = URI.create("http://localhost:9191/api/v1/projects/updateBy=ID/project/" + projectId);
		
		ResponseEntity<ApiResponse> response = restTemplate.exchange(uri, HttpMethod.PUT, entity, ApiResponse.class);
		
		ApiResponse apiResponse = response.getBody();
		
		System.out.println(apiResponse.toString());
		
		return apiResponse;
	}
	
	public String assignEmailToProject(Long projectId, Long mailId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Project> entity = new HttpEntity<>(headers);
		
		URI uri = URI.create("http://localhost:9191/api/v1/projects/assignEmail/project/" + projectId + "/email/" + mailId);

		ResponseEntity<Project> response = restTemplate.exchange(uri, HttpMethod.PUT, entity, new ParameterizedTypeReference<Project>(){});
		
		Project project = response.getBody();
		
		String apiResponse = null;
		
		if(null != project)
			apiResponse = "Project's mailing list has been updated successfully!";
		
		System.out.println(apiResponse);
		
		return apiResponse;
	}
	
	public String removeAssignedEmailFromProject(Long projectId, Long mailId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Project> entity = new HttpEntity<>(headers);
		
		URI uri = URI.create("http://localhost:9191/api/v1/projects/removeEmail/project/" + projectId + "/email/" + mailId);

		ResponseEntity<Project> response = restTemplate.exchange(uri, HttpMethod.PUT, entity, new ParameterizedTypeReference<Project>(){});
		
		Project project = response.getBody();
		
		String apiResponse = null;
		
		if(null != project)
			apiResponse = "Project's mailing list has been updated successfully!";
		
		System.out.println(apiResponse);
		
		return apiResponse;
	}

	public ApiResponse deleteProjectById(Long projectId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		URI uri = URI.create("http://localhost:9191/api/v1/projects/deleteBy=ID/project/" + projectId);

		ResponseEntity<ApiResponse> response = restTemplate.exchange(uri, HttpMethod.DELETE, entity, ApiResponse.class);
		
		ApiResponse apiResponse = response.getBody();
		
		System.out.println(apiResponse.toString());
		
		return apiResponse;
	}

}
