package com.jade.jira.model;

public class Project {
private String key;
private String name;
public String getKey() {
	return key;
}
public void setKey(String key) {
	this.key = key;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@Override
public String toString() {
	return "Project [key=" + key + ", name=" + name + "]";
}

}
