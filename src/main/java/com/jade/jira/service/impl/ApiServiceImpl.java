package com.jade.jira.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
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
	public Map<String, String> getConnectionFromJira(String authorization, String url) {
		Map<String, String> map = new HashMap<>();
		try {
			JSONObject proj = new JSONObject();
			String projects = JiraRESTClient.invokeGetMethod(authorization, url + jiraConfig.getProjectList());
			JSONArray projectArray = new JSONArray(projects);
			if (projectArray.length() > 0) {
				for (int i = 0; i < projectArray.length(); i++) {
					proj = projectArray.getJSONObject(i);
					map.put(proj.getString("key"), proj.getString("name"));
				}
			} else {
				map.put(proj.getString("NA"), proj.getString("Create Project"));
			}
		} catch (Exception e) {
			map = null;
			e.printStackTrace();
		}
		return map;

	}

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

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return issueObj;
	}

	@Override
	public String deleteIssue(String authorization, String url, String issueKey) {
		try {
			return JiraRESTClient.invokeDeleteMethod(authorization,
					url + jiraConfig.getDeleteIssue().replace("{issueIdOrKey}", issueKey));
		} catch (Exception e) {
			e.printStackTrace();
			return "Server Error.";
		}
	}

	@Override
	public Map<String, String> getTransitionsByissueKey(String authorization, String url, String issueKey) {
		Map<String, String> map = new HashMap<>();
		try {
			String transitions = JiraRESTClient.invokeGetMethod(authorization,
					url + jiraConfig.getTransitions().replace("{issueIdOrKey}", issueKey));
			JSONObject jsonObject = new JSONObject(transitions);
			JSONArray jsonArray = new JSONArray(jsonObject.getString("transitions"));
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject trans = jsonArray.getJSONObject(i);
				map.put(trans.getString("id"), trans.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
