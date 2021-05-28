package com.jade.jira.service.impl;

import java.util.Map;

public interface ApiService {
	Map<String, String> getConnectionFromJira(String authorization, String url);
}
