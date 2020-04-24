package com.crawler.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "ResponseMessage" })
@Component
public class ResponseStatus {

	@JsonProperty("ResponseMessage")
	private String responseMessage;

	@JsonProperty("ResponseMessage")
	public String getResponseMessage() {
		return responseMessage;
	}

	@JsonProperty("ResponseMessage")
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

}
