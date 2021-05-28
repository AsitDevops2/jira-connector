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

	public static String invokeGetMethod(String auth, String url) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add(JiraConstant.AUTHO, auth);

		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		if (response.getStatusCodeValue() == 401) {
			return JiraConstant.INVALID_CREDENTIAL;
		}
		return response.getBody();
	}


}
