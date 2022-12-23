package com.afourathon.project_management_ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.afourathon.project_management_ui.data.entity.MailingList;
import com.afourathon.project_management_ui.data.entity.Project;
import com.afourathon.project_management_ui.data.payloads.request.ProjectRequest;
import com.afourathon.project_management_ui.service.ConsumeMailingListRestApiService;
import com.afourathon.project_management_ui.service.ConsumeProjectRestApiService;

@Controller
public class ProjectController {
	
	@Autowired
	ConsumeProjectRestApiService projectService;
	
	@Autowired
	ConsumeMailingListRestApiService mailingListService;
	
	@GetMapping({"/", "/displayAllProjects"})
	public ModelAndView displayAllProjects() {
		ModelAndView modelAndView = new ModelAndView("display-projects");
		List<Project> projects = projectService.getAllProjects();
		modelAndView.addObject("projects", projects);
		
		return modelAndView;
	}
	
	@GetMapping("/displayProject")
	public ModelAndView displayProject(@RequestParam Long projectId) {
		ModelAndView modelAndView = new ModelAndView("display-project");
		Project project = projectService.getProjectById(projectId);
		modelAndView.addObject("project", project);
		
		return modelAndView;
	}
	
	@GetMapping("/addProjectForm")
	public ModelAndView addProjectForm() {
		ModelAndView modelAndView = new ModelAndView("add-project-form");
		ProjectRequest projectRequest = new ProjectRequest();
		modelAndView.addObject("projectRequest", projectRequest);
		
		return modelAndView;
	}
	
	@PostMapping("/addProject")
	public String addProject(@ModelAttribute ProjectRequest projectRequest) {
		projectService.addProject(projectRequest);
		
		return "redirect:/displayAllProjects";
	}
	
	@GetMapping("/updateProjectForm")
	public ModelAndView updateProjectForm(@RequestParam Long projectId) {
		ModelAndView modelAndView = new ModelAndView("update-project-form");
		
		Project project = projectService.getProjectById(projectId);
		ProjectRequest projectRequest = new ProjectRequest();
		projectRequest.setName(project.getName());
		projectRequest.setStartDate(project.getStartDate().toString());
		projectRequest.setEndDate(project.getEndDate().toString());
		projectRequest.setManagerName(project.getManagerName());
		projectRequest.setManagerEmail(project.getManagerEmail());
		
		modelAndView.addObject("projectId", projectId);
		modelAndView.addObject("projectRequest", projectRequest);
		
		return modelAndView;
	}
	
	@PostMapping("/updateProject")
	public String updateProject(@RequestParam Long projectId, @ModelAttribute ProjectRequest projectRequest) {
		projectService.updateProject(projectId, projectRequest);
		
		return "redirect:/displayAllProjects";
	}
	
	@GetMapping("/displayUnassignedMailingList")
	public ModelAndView displayUnassignedMailingList(@RequestParam Long projectId) {
		ModelAndView modelAndView = new ModelAndView("display-unassigned-mailing-list");
		
		Project project = projectService.getProjectById(projectId);
		modelAndView.addObject("project", project);
		
		List<MailingList> unassignedMailingList =  mailingListService.getUnassignedMailingList(projectId);
		modelAndView.addObject("unassignedMailingList", unassignedMailingList);
		
		return modelAndView;
	}
	
	@GetMapping("/assignEmailToProject")
	public String assignEmailToProject(@RequestParam Long projectId, @RequestParam Long mailId) {
		projectService.assignEmailToProject(projectId, mailId);
		
		return "redirect:/displayUnassignedMailingList?projectId=" + String.valueOf(projectId);
	}
	
	@GetMapping("/removeEmailFromProject")
	public String removeEmailFromProject(@RequestParam Long projectId, @RequestParam Long mailId) {
		projectService.removeAssignedEmailFromProject(projectId, mailId);
		
		return "redirect:/displayProject?projectId=" + String.valueOf(projectId);
	}
	
	@GetMapping("/deleteProject")
	public String deleteProject(@RequestParam Long projectId) {
		projectService.deleteProjectById(projectId);
		
		return "redirect:/displayAllProjects";
	}

}
