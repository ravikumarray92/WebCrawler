package com.crawler.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "URL", "depth" })
@Component
public class RequestBodyTemplate extends ResponseStatus{

	@JsonProperty("URL")
	private String uRL;
	@JsonProperty("depth")
	private Integer depth;

	@JsonProperty("URL")
	public String getURL() {
		return uRL;
	}

	@JsonProperty("URL")
	public void setURL(String uRL) {
		this.uRL = uRL;
	}

	@JsonProperty("depth")
	public Integer getDepth() {
		return depth;
	}

	@JsonProperty("depth")
	public void setDepth(Integer depth) {
		this.depth = depth;
	}

}
