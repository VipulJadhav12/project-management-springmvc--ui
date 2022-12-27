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

import com.afourathon.project_management_ui.data.entity.MailingList;
import com.afourathon.project_management_ui.data.payloads.request.MailingListRequest;
import com.afourathon.project_management_ui.data.payloads.response.ApiResponse;

@Service
public class ConsumeMailingListRestApiService {
	
	@Autowired
	RestTemplate restTemplate;
	
	public List<MailingList> getAllMailingList() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<List<MailingList>> entity = new HttpEntity<>(headers);
		
		URI uri = URI.create("http://localhost:9191/api/v1/emails/getAllBy=NONE");

		ResponseEntity<List<MailingList>> response = restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<List<MailingList>>(){});
		
		List<MailingList> mailingList = response.getBody();
		
		return mailingList;
	}
	
	public MailingList getEmailById(Long mailId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<MailingList> entity = new HttpEntity<>(headers);
		
		URI uri = URI.create("http://localhost:9191/api/v1/emails/getBy=ID/email/" + mailId);

		ResponseEntity<MailingList> response = restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<MailingList>(){});
		
		MailingList email = response.getBody();
		
		return email;
	}
	
	public List<MailingList> getUnassignedMailingList(Long projectId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<List<MailingList>> entity = new HttpEntity<>(headers);
		
		URI uri = URI.create("http://localhost:9191/api/v1/emails/getAllUnAssignedBy=PROJECT_ID/project/" + projectId);

		ResponseEntity<List<MailingList>> response = restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<List<MailingList>>(){});
		
		List<MailingList> mailingList = response.getBody();
		
		return mailingList;
	}

	public ApiResponse addEmail(MailingListRequest mailingListRequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<MailingListRequest> entity = new HttpEntity<>(mailingListRequest, headers);
		
		URI uri = URI.create("http://localhost:9191/api/v1/emails/add");
		
		ResponseEntity<ApiResponse> response = restTemplate.exchange(uri, HttpMethod.POST, entity, ApiResponse.class);
		
		ApiResponse apiResponse = response.getBody();
		
		System.out.println(apiResponse.toString());
		
		return apiResponse;
	}

	public ApiResponse updateEmail(Long mailId, MailingListRequest mailingListRequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<MailingListRequest> entity = new HttpEntity<>(mailingListRequest, headers);
		
		URI uri = URI.create("http://localhost:9191/api/v1/emails/updateBy=ID/email/" + mailId);
		
		ResponseEntity<ApiResponse> response = restTemplate.exchange(uri, HttpMethod.PUT, entity, ApiResponse.class);
		
		ApiResponse apiResponse = response.getBody();
		
		System.out.println(apiResponse.toString());
		
		return apiResponse;
	}

	public ApiResponse deleteEmailById(Long mailId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		URI uri = URI.create("http://localhost:9191/api/v1/emails/deleteBy=ID/email/" + mailId);

		ResponseEntity<ApiResponse> response = restTemplate.exchange(uri, HttpMethod.DELETE, entity, ApiResponse.class);
		
		ApiResponse apiResponse = response.getBody();
		
		System.out.println(apiResponse.toString());
		
		return apiResponse;
	}

}
