package com.melochey.elastic;


import org.elasticsearch.search.SearchHit;
import org.junit.Test;

import com.melochey.elastic.dao.ESQueryWrapper;
import com.melochey.elastic.entity.Document;
import com.melochey.elastic.entity.Index;
import com.melochey.elastic.entity.ES.ESParam;
import com.melochey.elastic.entity.ES.RangeField;
import com.melochey.elastic.util.ESConnector;

public class LatestTest {
	Index index = new Index("school", "students");
	ESQueryWrapper<Document> dao = new ESQueryWrapper<Document>(ESConnector.getClient(), index);

	//@Test
	public void commonQueryTest() {
		ESParam param = new ESParam();
		// GenerateField eskv = new GenerateField("firstname","melo2",2);
		String[] fields = new String[] { "age", "height" };
		param.searchedFields = fields;
		RangeField range = new RangeField("age", 12, true, 30, true, ESQ);
		param.getFieldList().add(range);
		param.sortKeys.put("age", true);
		param.sortKeys.put("height", false);
		SearchHit[] hits = dao.commonQuery(param);
		for (SearchHit hit : hits) {
			System.out.println(hit.getSourceAsString());
		}
	}

	@Test
	public void aggretationQueryTest() {
		dao.aggretationQuery();
	}
}
