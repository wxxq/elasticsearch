package com.melochey.elastic.entity.ES;

public class BaseField {
	
	public String fieldName;



	// 0 termQuery 1 matchQuery 2 rangeQuery
	public int flag;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}



	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
}
