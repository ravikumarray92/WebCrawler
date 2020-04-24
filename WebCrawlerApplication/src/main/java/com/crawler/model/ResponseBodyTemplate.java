package com.crawler.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "total_links", "total_images", "details" })
@Component
public class ResponseBodyTemplate {
	@JsonProperty("total_links")
	private String totalLinks;
	@JsonProperty("total_images")
	private String totalImages;
	@JsonProperty("details")
	private List<Detail> details = null;

	@JsonProperty("total_links")
	public String getTotalLinks() {
		return totalLinks;
	}

	@JsonProperty("total_links")
	public void setTotalLinks(String totalLinks) {
		this.totalLinks = totalLinks;
	}

	@JsonProperty("total_images")
	public String getTotalImages() {
		return totalImages;
	}

	@JsonProperty("total_images")
	public void setTotalImages(String totalImages) {
		this.totalImages = totalImages;
	}

	@JsonProperty("details")
	public List<Detail> getDetails() {
		return details;
	}

	@JsonProperty("details")
	public void setDetails(List<Detail> details) {
		this.details = details;
	}

}
