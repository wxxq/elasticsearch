package com.melochey.elastic.entity.ES;

import org.elasticsearch.search.SearchHits;

public class ESResult {
	private long totalSize;
	private SearchHits hist;
	public long getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}
	public SearchHits getHist() {
		return hist;
	}
	public void setHist(SearchHits hist) {
		this.hist = hist;
	}
}
