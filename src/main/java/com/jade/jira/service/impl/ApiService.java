package com.jade.jira.service.impl;

import java.util.Map;

import org.json.JSONObject;

import com.jade.jira.model.CreateIssueRequest;

public interface ApiService {
	Map<String, String> getConnectionFromJira(String authorization, String url);

	JSONObject createIssue(CreateIssueRequest createIssueRequest, String authorization, String url);

	Boolean deleteIssue(String authorization, String url, String issueKey);

	Map<String, String> getTransitionsByissueKey(String authorization, String url, String issueKey);

}
