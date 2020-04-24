package com.crawler.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.crawler.configuration.SimpleCache;
import com.crawler.model.GetSingleResult;
import com.crawler.model.RequestBodyTemplate;
import com.crawler.model.ResponseBodyTemplate;
import com.crawler.service.WebCrawlerService;
import com.crawler.constants.StringConstants;

@RestController
@RequestMapping("crawl/webpage")
public class WebCrawlerController {

	@Autowired
	WebCrawlerService webCrawlerService;
	@Autowired
	private SimpleCache simpleCache;

	public WebCrawlerController() {
		System.out.println(simpleCache);

	}

	@RequestMapping(value = "/submitURLs", method = RequestMethod.POST)
	public String submitURLsForCrawling(@RequestBody List<RequestBodyTemplate> requestBodyList) {

		for (RequestBodyTemplate requestBody : requestBodyList) {
			simpleCache.getsubmittedDataCache().put(requestBody.getURL(), requestBody.getDepth());
		}
		return StringConstants.REQUEST_SUBMITTED;

	}

	@Async
	@Scheduled(fixedRate = 1000)
	public void crawlURLs() throws InterruptedException {
		System.out.println("Fixed rate task async - " + System.currentTimeMillis() / 1000);
		if (simpleCache.getsubmittedDataCache().size() > 0)
			simpleCache.getsubmittedDataCache().forEach((URL, depth) -> {
				if (!simpleCache.getCrawledDataCache().containsKey(URL))
					webCrawlerService.processDetails(URL, depth);
			});
		Thread.sleep(2000);
	}

	@RequestMapping(value = "/getCrawledResult", method = RequestMethod.GET)
	public ResponseBodyTemplate getCrawledURLResult(@RequestBody GetSingleResult getRequestBody) {

		return webCrawlerService.getSingleURLCrawledData(getRequestBody.getURL());

	}

	@RequestMapping(value = "/getStatus", method = RequestMethod.GET)
	public String getURLStatus(@RequestBody GetSingleResult getRequestBody) {

		return webCrawlerService.getStatus(getRequestBody.getURL());

	}

	@RequestMapping(value = "/getAllStatus", method = RequestMethod.GET)
	public List<ResponseBodyTemplate> getAllURLStatus() {

		return webCrawlerService.getAllURLStatus();

	}

}
