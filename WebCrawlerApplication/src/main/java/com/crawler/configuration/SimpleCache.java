package com.crawler.configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.crawler.model.ResponseBodyTemplate;

@Component
@Scope(value="singleton")
public class SimpleCache {

	Map<String, ResponseBodyTemplate> crawledDataCache = new HashMap<String, ResponseBodyTemplate>();
	Map<String, Integer> submittedDataCache = new HashMap<String, Integer>();
	Set<String> failedDataCache = new HashSet<String>();

	public Map<String, ResponseBodyTemplate> getCrawledDataCache() {
		return crawledDataCache;

	}

	public Map<String, Integer> getsubmittedDataCache() {

		return submittedDataCache;
	}

	public Set<String> getfailedDataCache() {

		return failedDataCache;
	}

}
