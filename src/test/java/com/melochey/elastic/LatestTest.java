package com.melochey.elastic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.junit.Test;

import com.google.gson.Gson;
import com.melochey.elastic.dao.ESQueryWrapper;
import com.melochey.elastic.entity.Document;
import com.melochey.elastic.entity.Index;
import com.melochey.elastic.entity.ES.ESMetrics;
import com.melochey.elastic.entity.ES.ESParam;
import com.melochey.elastic.entity.ES.ESQueryType;
import com.melochey.elastic.entity.ES.RangeField;
import com.melochey.elastic.util.ESConnector;
import com.melochey.elastic.util.ESResponseParse;

public class LatestTest {
	Index index = new Index("school", "students");
	ESQueryWrapper<Document> dao = new ESQueryWrapper<Document>(ESConnector.getClient(), index);
	Gson gson = new Gson();

	// @Test
	public void commonQueryTest() {
		ESParam param = new ESParam();
		String[] fields = new String[] { "age", "height" };
		param.searchedFields = fields;
		RangeField range = new RangeField("age", 12, true, 30, true, ESQueryType.RANGE);
		param.fieldList = new ArrayList<>();
		param.sortKeys = new HashMap<>();
		param.getFieldList().add(range);
		param.sortKeys.put("age", true);
		param.sortKeys.put("height", false);
		SearchResponse response = dao.commonQuery(param);
		String json = ESResponseParse.parseJsonFromResponse(response);
		System.out.println(json.toString());
	}

	@Test
	public void aggretationQueryTest() {
		ESParam param = new ESParam();
		param.aggregationFields = new ArrayList<>();
		param.aggregationFields.add("school");
		param.aggregationMetrics = new HashMap<>();
//		param.aggregationMetrics.put("class", ESMetrics.SUM);
		SearchResponse response = dao.commonQuery(param);
		Aggregations aggregations = response.getAggregations();
		Terms terms = aggregations.get("term_school");
		List<? extends Bucket> buckets = terms.getBuckets();
		for(Bucket bucket:buckets){
			long count = bucket.getDocCount();
			String key=(String) bucket.getKey();
			System.out.println(key+":"+count);
		}
	}
}
