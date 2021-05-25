package com.jade.jira.model;

public class JiraConnectionRequest {
	private String username;
	private String password;
	private String jiraUrl;


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJiraUrl() {
		return jiraUrl;
	}

	public void setJiraUrl(String jiraUrl) {
		this.jiraUrl = jiraUrl;
	}

}
