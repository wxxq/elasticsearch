package com.melochey.elastic.util;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.json.JSONArray;
import org.json.JSONObject;

public class ESResponseParse {
	public static String parseJsonFromResponse(SearchResponse response) {
		SearchHits hits = response.getHits();
		long totalSize = hits.totalHits;
		JSONObject obj = new JSONObject();
		obj.put("totalSize", totalSize);
		JSONArray arr = new JSONArray();
		for (SearchHit hit : hits) {
			String resultStr=hit.getSourceAsString();
			JSONObject item = new JSONObject(resultStr);
			arr.put(item);
		}
		obj.put("documents", arr);
		return obj.toString();
	}
}
