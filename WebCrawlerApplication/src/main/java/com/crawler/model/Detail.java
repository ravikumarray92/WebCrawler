package com.crawler.model;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
@Component
public class Detail {
	@JsonProperty("page_title")
	private String pageTitle;
	@JsonProperty("page_link")
	private String pageLink;
	@JsonProperty("image_count")
	private String imageCount;
	
	@JsonProperty("page_title")
	public String getPageTitle() {
	return pageTitle;
	}

	@JsonProperty("page_title")
	public void setPageTitle(String pageTitle) {
	this.pageTitle = pageTitle;
	}

	@JsonProperty("page_link")
	public String getPageLink() {
	return pageLink;
	}

	@JsonProperty("page_link")
	public void setPageLink(String pageLink) {
	this.pageLink = pageLink;
	}

	@JsonProperty("image_count")
	public String getImageCount() {
	return imageCount;
	}

	@JsonProperty("image_count")
	public void setImageCount(String imageCount) {
	this.imageCount = imageCount;
	}
}
