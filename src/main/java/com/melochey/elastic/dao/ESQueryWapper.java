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
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import com.melochey.elastic.entity.Index;
import com.melochey.elastic.entity.ES.BaseField;
import com.melochey.elastic.entity.ES.ESParam;
import com.melochey.elastic.entity.ES.GenerateField;
import com.melochey.elastic.entity.ES.RangeField;

public class ESQueryWapper<T> {
	private Index index;
	private final RestHighLevelClient client;

	public ESQueryWapper(RestHighLevelClient client, Index index) {
		this.client = client;
		this.index = index;
	}

	public SearchHit[] query(ESParam param) {
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
		int from = param.getFrom();
		int size = param.getSize();

		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		for (String itemField : param.searchedFields) {
			searchSourceBuilder.docValueField(itemField);
		}
		searchSourceBuilder.from(from);
		searchSourceBuilder.size(size);
		// 传入 查询参数
		searchSourceBuilder.query(boolQuery);
		// 排序
		for (String key : param.sortKeys.keySet()) {
			FieldSortBuilder fieldSort = SortBuilders.fieldSort(key)
					.order(param.sortKeys.get(key) ? SortOrder.ASC : SortOrder.DESC);
			searchSourceBuilder.sort(fieldSort);
		}
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
}
