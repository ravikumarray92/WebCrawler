package com.crawler.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.crawler.model.Detail;
import com.crawler.model.ResponseBodyTemplate;
import com.crawler.constants.StringConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.crawler.configuration.SimpleCache;

@Component
public class WebCrawlerService {

	@Autowired
	ResponseBodyTemplate responseBodyTemplate;
	@Autowired
	Detail detail;

	@Autowired
	private SimpleCache simpleCache;
	List<Detail> detailsList;
	Document document = null;
	String pageTitle = null;
	String imageCount = null;

	public WebCrawlerService() {
		detailsList = new ArrayList<>();
	}

	public void getCrawlingDetails(String URL, int depth) {

		if ((!simpleCache.getCrawledDataCache().containsKey(URL) && (depth < StringConstants.MAX_DEPTH))) {
			if (document != null)
				populateDetails(URL);
			try {
				document = Jsoup.connect(URL).get();
				Elements linksOnPage = document.select("a[href]");

				depth++;
				for (Element page : linksOnPage) {
					getCrawlingDetails(page.attr("abs:href"), depth);
				}
			} catch (IOException e) {
				System.err.println("For '" + URL + "': " + e.getMessage());
				simpleCache.getfailedDataCache().add(URL);
			}
		}

	}

	public void populateDetails(String URL) {
		detail.setPageLink(URL);
		if (document != null && document.select(StringConstants.PAGE_TITLE) != null
				&& document.select(StringConstants.PAGE_TITLE).first() != null)
			pageTitle = document.select(StringConstants.PAGE_TITLE).first().text();
		detail.setPageTitle(pageTitle);
		imageCount = String.valueOf(document.getElementsByTag(StringConstants.IMAGE_TAG).size());
		detail.setImageCount(imageCount);
		detailsList.add(detail);

	}

	public void processDetails(String URL, int depth) {

		if (simpleCache.getCrawledDataCache().containsKey(URL)) {
			return;
		}
		getCrawlingDetails(URL, depth);
		List<Detail> linksList = detailsList.stream().filter(detail -> detail.getPageLink() != null)
				.collect(Collectors.toList());
		int totalLinks = linksList.size();
		int totalImages = 0;
		for (Detail detail : detailsList) {
			totalImages += Integer.parseInt(detail.getImageCount());
		}
		responseBodyTemplate.setTotalLinks(String.valueOf(totalLinks));
		responseBodyTemplate.setTotalImages(String.valueOf(totalImages));
		responseBodyTemplate.setDetails(detailsList);
		simpleCache.getCrawledDataCache().put(URL, responseBodyTemplate);
		simpleCache.getsubmittedDataCache().remove(URL);
	}

	public ResponseBodyTemplate getSingleURLCrawledData(String URL) {

		if (simpleCache.getCrawledDataCache().containsKey(URL)) {
			return simpleCache.getCrawledDataCache().get(URL);
		}

		return new ResponseBodyTemplate();
	}

	public String getStatus(String URL) {
		if (simpleCache.getCrawledDataCache().containsKey(URL) && !simpleCache.getsubmittedDataCache().containsKey(URL))
			return StringConstants.PROCESSED_STATUS;

		if (simpleCache.getsubmittedDataCache().containsKey(URL)
				&& !simpleCache.getCrawledDataCache().containsKey(URL)) {
			return StringConstants.PROCESSING_STATUS;
		}

		if (simpleCache.getfailedDataCache().contains(URL)) {
			return StringConstants.FAILED_STATUS + URL;
		}

		return StringConstants.URL_UNAVAILABLE;
	}

	public List<ResponseBodyTemplate> getAllURLStatus() {
		List<ResponseBodyTemplate> responseList = new ArrayList<>();
		simpleCache.getCrawledDataCache().forEach((URL, responseBodyTemplates) -> {
			responseList.add(responseBodyTemplates);
		});
		return responseList;
	}

}
