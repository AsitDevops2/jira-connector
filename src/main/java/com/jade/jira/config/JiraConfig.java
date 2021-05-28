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

	private String projectList;

	public String getProjectList() {
		return projectList;
	}

	public void setProjectList(String projectList) {
		this.projectList = projectList;
	}

}
