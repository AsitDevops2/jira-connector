package com.jade.jira.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author abhay.thakur
 *
 */
@Configuration
@PropertySource("classpath:jira.properties")
@ConfigurationProperties(prefix = "jira")
public class JiraConfig {

	private String createIssue;
	private String checkIssue;
	private String deleteIssue;
	public String getCreateIssue() {
		return createIssue;
	}

	public void setCreateIssue(String createIssue) {
		this.createIssue = createIssue;
	}

	public String getCheckIssue() {
		return checkIssue;
	}

	public void setCheckIssue(String checkIssue) {
		this.checkIssue = checkIssue;
	}

	public String getDeleteIssue() {
		return deleteIssue;
	}

	public void setDeleteIssue(String deleteIssue) {
		this.deleteIssue = deleteIssue;
	}

}
