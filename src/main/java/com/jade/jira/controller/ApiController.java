package com.jade.jira.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jade.jira.model.Response;
import com.jade.jira.service.impl.ApiService;

/**
 * @author abhay.thakur
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/jira")
public class ApiController {

	public static final String CREATE_ISSUE = "/create-issue";
	public static final String DELETE_ISSUE = "/delete-issue/{issueIdOrKey}";
	public static final String CHECK_JIRA_CONNECTION = "/getConnection";
	public static final String GET_TRANSITIONS = "/getTransitions/{issueIdOrKey}";
	public static final String GET_ISSUE_TYPES = "/getAllComments/{issueIdOrKey}";
	public static final String AUTHO = "authorization"; // Username:Password- with- Base64 Encode
	public static final String URL = "url";

	Logger logger = LoggerFactory.getLogger(ApiController.class);

	@Autowired
	private ApiService apiService;

	/**
	 * check connection to jira
	 */

	@GetMapping(path = CHECK_JIRA_CONNECTION)
	public Response getJiraConnection(HttpServletRequest request) {
		if (apiService.getConnectionFromJira(request.getHeader(AUTHO), request.getHeader(URL)) != null) {
			return new Response(HttpStatus.OK.value(), "OK",
					apiService.getConnectionFromJira(request.getHeader(AUTHO), request.getHeader(URL)));
		} else {
			return new Response(HttpStatus.BAD_REQUEST.value(), "Username or Password is invalid!",
					apiService.getConnectionFromJira(request.getHeader(AUTHO), request.getHeader(URL)));
		}

	}

}