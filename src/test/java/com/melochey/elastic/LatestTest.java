package com.melochey.elastic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.aggregations.metrics.min.Min;
import org.junit.Test;

import com.google.gson.Gson;
import com.melochey.elastic.dao.ESQueryWrapper;
import com.melochey.elastic.entity.Document;
import com.melochey.elastic.entity.Index;
import com.melochey.elastic.entity.ES.BaseField;
import com.melochey.elastic.entity.ES.BoolField;
import com.melochey.elastic.entity.ES.ESMetrics;
import com.melochey.elastic.entity.ES.ESParam;
import com.melochey.elastic.entity.ES.ESQueryType;
import com.melochey.elastic.entity.ES.ESSearchType;
import com.melochey.elastic.entity.ES.GenerateField;
import com.melochey.elastic.entity.ES.RangeField;
import com.melochey.elastic.util.ESConnector;
import com.melochey.elastic.util.ESResponseParse;

import static java.lang.String.format;
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
	
//	@Test
	public void queryTest2(){
		ESParam param = new ESParam();
		param.fieldList = new ArrayList<>();
		List<BaseField> list= new ArrayList<>();
		GenerateField g = new GenerateField("school", "school3", ESQueryType.TERM);
		GenerateField g2 = new GenerateField("school", "school5", ESQueryType.TERM);
		g2.setSearchType(ESSearchType.SHOULD);
		RangeField r = new RangeField("age",  12, true, null, false, ESQueryType.RANGE);
		list.add(g);
		list.add(r);
		BoolField b = new BoolField();
		b.setChildBool(list);
		b.setFlag(ESQueryType.BOOL);
		param.fieldList.add(b);
		param.fieldList.add(g2);
		SearchResponse response = dao.commonQuery(param);
		String json = ESResponseParse.parseJsonFromResponse(response);
		System.out.println(json.toString());
	}
	
	@Test
	public  void queryTest3(){
		ESParam param = new ESParam();
		param.setSize(100);
		param.fieldList = new ArrayList<>();
		GenerateField g = new GenerateField("category", "class3", ESQueryType.TERM);
		g.setSearchType(ESSearchType.SHOULD);
		GenerateField g2 = new GenerateField("category", "class2", ESQueryType.TERM);
		g2.setSearchType(ESSearchType.SHOULD);
		GenerateField g3 = new GenerateField("school", "school1", ESQueryType.TERM);
		g3.setSearchType(ESSearchType.MUST);
		param.fieldList.add(g);
		param.fieldList.add(g2);
		param.fieldList.add(g3);
		SearchResponse response = dao.commonQuery(param);
		String json = ESResponseParse.parseJsonFromResponse(response);
		System.out.println(json.toString());
	}

//	@Test
	public void aggretationQueryTest() {
		ESParam param = new ESParam();
		param.aggregationFields = new ArrayList<>();
		param.aggregationFields.add("school");
		param.aggregationFields.add("category");
		param.aggregationMetrics = new HashMap<String, ESMetrics[]>();
		ESMetrics[] metrics = {ESMetrics.MAX,ESMetrics.MIN,ESMetrics.CARDINALITY};
		param.aggregationMetrics.put("age", metrics);
		SearchResponse response = dao.commonQuery(param);
		Aggregations aggregations = response.getAggregations();
		Terms terms = aggregations.get("term_school");
		List<? extends Bucket> buckets = terms.getBuckets();
		for(Bucket bucket:buckets){
			long schoolNum = bucket.getDocCount();
			String keySchool=(String) bucket.getKey();
			System.out.println(keySchool+":"+schoolNum+"---------");
			Terms terms2 = bucket.getAggregations().get("term_category");
			List<? extends Bucket> buckets2 = terms2.getBuckets();
			for(Bucket bucket2:buckets2){
				String keyClass=(String) bucket2.getKey();
				long classNum=bucket2.getDocCount();
				Max maxAge=bucket2.getAggregations().get("max_age");
				Min minAge=bucket2.getAggregations().get("min_age");
				String formated=format("current_class:%s,class_total_num:%d,max_age:%f,min_age:%f",keyClass,classNum,maxAge.getValue(),minAge.getValue());
				System.out.println(formated);
				
			}
		}
	}
}
