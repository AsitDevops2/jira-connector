package com.jade.jira.util;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class JiraRESTClient {

	private JiraRESTClient() {

	}
	public static String createIssue(String auth, String url, String data) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add(JiraConstant.AUTHO, auth);

		HttpEntity<String> entity = new HttpEntity<>(data, headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		if (response.getStatusCodeValue() == 401) {
			return JiraConstant.INVALID_CREDENTIAL;
		} else if (response.getStatusCodeValue() == 400) {
			return JiraConstant.BAD_REQUEST;
		}
		return response.getBody();
	}

	public static String invokeDeleteMethod(String auth, String url) {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add(JiraConstant.AUTHO, auth);

		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);

		switch (response.getStatusCodeValue()) {
		case 204:
			return "Successfully Deleted.";
		case 401:
			return JiraConstant.INVALID_CREDENTIAL;
		case 403:
			return "User does not have permission to delete the issue.";
		case 404:
			return "The issue is not found or the user does not have permission to view the issue.";
		default:
			return "Jira Connection error.";
		}

	}

}
