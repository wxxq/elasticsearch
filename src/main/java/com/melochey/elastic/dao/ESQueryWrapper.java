package com.melochey.elastic.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.max.MaxAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCountAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import com.melochey.elastic.entity.Index;
import com.melochey.elastic.entity.ES.BaseField;
import com.melochey.elastic.entity.ES.ESParam;
import com.melochey.elastic.entity.ES.GenerateField;
import com.melochey.elastic.entity.ES.RangeField;

public class ESQueryWrapper<T> {
	private Index index;
	private final RestHighLevelClient client;

	public ESQueryWrapper(RestHighLevelClient client, Index index) {
		this.client = client;
		this.index = index;
	}

	public void wrapperBuilder(SearchSourceBuilder searchSourceBuilder, ESParam param) {
		int from = param.getFrom();
		int size = param.getSize();
		searchSourceBuilder.from(from);
		searchSourceBuilder.size(size);
		// 传入查询字段
		if (param.searchedFields != null) {
			searchSourceBuilder.fetchSource(param.searchedFields, null);
		}
		// 排序
		for (String key : param.sortKeys.keySet()) {
			FieldSortBuilder fieldSort = SortBuilders.fieldSort(key)
					.order(param.sortKeys.get(key) ? SortOrder.ASC : SortOrder.DESC);
			searchSourceBuilder.sort(fieldSort);
		}
	}

	public SearchHit[] searchedHits(SearchSourceBuilder searchSourceBuilder) {
		SearchRequest searchRequest = new SearchRequest(index.getName());
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse = null;
		SearchHit[] searchHits = null;
		try {
			searchResponse = client.search(searchRequest);
			SearchHits hits = searchResponse.getHits();
			searchHits = hits.getHits();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return searchHits;
	}

	public SearchResponse searchedResponse(SearchSourceBuilder searchSourceBuilder) {
		SearchRequest searchRequest = new SearchRequest(index.getName());
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse = null;
		try {
			searchResponse = client.search(searchRequest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return searchResponse;
	}

	public SearchHit[] commonQuery(ESParam param) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		wrapperBuilder(searchSourceBuilder, param);
		// 装载查询参数
		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
		List<BaseField> fieldList = param.getFieldList();
		for (BaseField eskv : fieldList) {
			if (eskv.getFlag() == 1) {
				boolQuery.filter(QueryBuilders.termQuery(eskv.getFieldName(), ((GenerateField) eskv).getFieldValue()));
			} else if (eskv.getFlag() == 2) {
				boolQuery.filter(QueryBuilders.matchQuery(eskv.getFieldName(), ((GenerateField) eskv).getFieldValue()));
			} else if (eskv.getFlag() == 3) {
				RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery(eskv.getFieldName());
				rangeQuery.from(((RangeField) eskv).getLowerValue(), ((RangeField) eskv).isIncludeLower());
				rangeQuery.to(((RangeField) eskv).getUpperValue(), ((RangeField) eskv).isIncludeUpper());
				boolQuery.filter(rangeQuery);
			}
		}
		// 传入 查询参数
		searchSourceBuilder.query(boolQuery);
		return searchedHits(searchSourceBuilder);
	}

	public void aggretationQuery() {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		TermsAggregationBuilder aggregation = AggregationBuilders.terms("count_age").field("age");
		// aggregation.subAggregation(AggregationBuilders.avg("average_age").field("age"));
		searchSourceBuilder.aggregation(aggregation);
		SearchResponse searchResponse = searchedResponse(searchSourceBuilder);
		Aggregations aggregations = searchResponse.getAggregations();
		Terms terms = aggregations.get("count_age");
		List<? extends Bucket> buckets = terms.getBuckets();
		for(Bucket bucket:buckets){
			System.out.println(bucket.getKey()+":"+bucket.getDocCount());
		}
	}

}
