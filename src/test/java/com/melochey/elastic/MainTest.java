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
		String[] categorys = new String[]{"class1","class2","class3","class4","class5"};
		String[] school = new String[]{"school1","school2","school3","school4","school5","school6"};
		for (int i = 0; i < 1000; i++) {
			Document document = new Document();
			Random random = new Random();
			int randNum = random.nextInt(1000);
			document.setFirstname("melo" + randNum);
			document.setLastname("sdf" + randNum);
			document.setCategory(categorys[random.nextInt(5)]);
			document.setSchool(school[random.nextInt(6)]);
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
	

}
