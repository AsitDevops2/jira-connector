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
		} catch (Exception exception) {
			map = null;
			logger.error("Error: ", exception);
		}
		return map;

	}

}
