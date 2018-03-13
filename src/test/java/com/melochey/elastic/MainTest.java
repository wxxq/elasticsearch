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
import com.melochey.elastic.entity.HealthDoc;
import com.melochey.elastic.entity.Index;
import com.melochey.elastic.entity.ES.BaseField;
import com.melochey.elastic.entity.ES.ESParam;
import com.melochey.elastic.entity.ES.TermField;
import com.melochey.elastic.entity.ES.RangeField;
import com.melochey.elastic.util.ESConnector;
import com.google.gson.Gson;

public class MainTest {
	Index index = new Index("pub_health", "health_docs");
	SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
	Gson gson = new Gson();
	ElasticDao<HealthDoc> dao = new ElasticDao<HealthDoc>(ESConnector.getClient(), sourceBuilder, index, gson);

	public static double nextDouble(final double min, final double max) {
		if (max < min) {
			return 0.0;
		}
		if (min == max) {
			return min;
		}
		return min + ((max - min) * new Random().nextDouble());
	}

	@Test
	public void initData() {
		String[] categorys = new String[] { "class1", "class2", "class3", "class4", "class5" };
		String[] school = new String[] { "school1", "school2", "school3", "school4", "school5", "school6" };
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
			document.setProvince_id(random.nextInt(33));
			document.setCity_id(random.nextInt(330));
			document.setMoney(MainTest.nextDouble(0.0, 100000.0));
//			dao.createIndex(index, document);
		}

	}

	// @Test
	public void search() {
		List<Document> list = dao.matchAllQuery();
		System.out.println(list.size());
		String result = gson.toJson(list);
		System.out.println(result);
	}

	@Test
	public void add() {
		for (int i = 0; i < 100000; i++) {
			HealthDoc doc = new HealthDoc();
			doc.setProvinceId(new Random().nextLong());
			doc.setCityId(new Random().nextLong());
			doc.setDistrictId(new Random().nextLong());
			doc.setIdCard(String.valueOf(new Random().nextLong()));
			dao.createIndex(index, doc);
		}
	}
}
