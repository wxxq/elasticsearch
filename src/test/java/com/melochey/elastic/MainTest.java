package com.melochey.elastic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import com.google.gson.Gson;
import com.melochey.elastic.dao.ElasticDao;
import com.melochey.elastic.entity.Document;
import com.melochey.elastic.entity.Index;
import com.melochey.elastic.entity.ES.BaseField;
import com.melochey.elastic.entity.ES.ESParam;
import com.melochey.elastic.entity.ES.GenerateField;
import com.melochey.elastic.entity.ES.RangeField;
import com.melochey.elastic.util.ESConnector;
import com.google.gson.Gson;

public class MainTest {
	Index index = new Index("school", "students");
	SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
	Gson gson = new Gson();
	ElasticDao dao = new ElasticDao(ESConnector.getClient(), sourceBuilder, index, gson);

	@Test
	public void initData() {
		for (int i = 0; i < 1000; i++) {
			Document document = new Document();
			Random random = new Random();
			int randNum = random.nextInt(10000);
			document.setFirstname("melo" + randNum);
			document.setLastname("sdf" + randNum);
			document.setMessage("test value");
			document.setAge(random.nextInt(100));
			document.setHeight(random.nextInt(200));
			dao.createIndex(index, document);
		}

	}
	
	//@Test
	public void search(){
		List<Document> list = dao.matchAllQuery();
		System.out.println(list.size());
		String result=gson.toJson(list);
		System.out.println(result);
	}
	
	//@Test	
	public void query(){
		ESParam param = new ESParam();
		//GenerateField eskv = new GenerateField("firstname","melo2",2);
		RangeField range = new RangeField("age",12,true,30,true,3);
		//param.getFieldList().add(eskv);
		param.getFieldList().add(range);
		param.sortKeys.put("age", true);
		param.sortKeys.put("height", false);
		List<Document> list = dao.query(param);
		for (Document document : list) {
			
			System.out.println(gson.toJson(document));
		}
	}
}
