package com.crawler.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "URL" })
@Component
public class GetSingleResult {

	@JsonProperty("URL")
	private String uRL;

	@JsonProperty("URL")
	public String getURL() {
		return uRL;
	}

	@JsonProperty("URL")
	public void setURL(String uRL) {
		this.uRL = uRL;
	}

}