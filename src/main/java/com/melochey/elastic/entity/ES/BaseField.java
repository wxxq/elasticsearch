package com.melochey.elastic.entity.ES;

public class BaseField {
	
	public String fieldName;

	// 0 termQuery 1 matchQuery 2 rangeQuery
	public ESQueryType flag;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	
}
