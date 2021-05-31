package com.jade.jira.service.impl;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jade.jira.config.JiraConfig;
import com.jade.jira.model.CreateIssueRequest;
import com.jade.jira.util.JiraRESTClient;

@Service(value = "apiService")
public class ApiServiceImpl implements ApiService {
	Logger logger = LoggerFactory.getLogger(ApiServiceImpl.class);
	@Autowired
	private JiraConfig jiraConfig;

	@Override
	public JSONObject createIssue(CreateIssueRequest createIssueRequest, String authorization, String url) {
		JSONObject issueObj = null;
		try {
			JSONObject issuetype = new JSONObject();
			issuetype.put("name", createIssueRequest.getIssueType());
			JSONObject project = new JSONObject();
			project.put("key", createIssueRequest.getProjectKey());
			JSONObject priority = new JSONObject();
			priority.put("name", "High");
			JSONObject fields = new JSONObject();
			fields.put("summary", createIssueRequest.getIssueSummary());
			fields.put("project", project);
			fields.put("issuetype", issuetype);
			JSONObject request = new JSONObject();
			request.put("fields", fields);
			String issue = JiraRESTClient.createIssue(authorization, url + jiraConfig.getCreateIssue(),
					request.toString());
			issueObj = new JSONObject(issue);

		} catch (Exception exception) {
			logger.error("Error: ", exception);
			return null;
		}
		return issueObj;
	}

	@Override
	public String deleteIssue(String authorization, String url, String issueKey) {
		try {
			return JiraRESTClient.invokeDeleteMethod(authorization,
					url + jiraConfig.getDeleteIssue().replace("{issueIdOrKey}", issueKey));
		} catch (Exception exception) {
			logger.error("Error: ", exception);
			return "Server Error.";
		}
	}

}
