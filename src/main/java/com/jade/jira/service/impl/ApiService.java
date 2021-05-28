package com.jade.jira.service.impl;

import org.json.JSONObject;

import com.jade.jira.model.CreateIssueRequest;

public interface ApiService {
	JSONObject createIssue(CreateIssueRequest createIssueRequest, String authorization, String url);

	String deleteIssue(String authorization, String url, String issueKey);

}
