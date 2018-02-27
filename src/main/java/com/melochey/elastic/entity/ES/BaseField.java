package com.melochey.elastic.entity.ES;

public class BaseField {
	
	protected String fieldName;

	public ESQueryType flag ;
	
	// default filter
	public ESSearchType searchType = ESSearchType.FILTER;
	
	public BaseField(String fieldName,ESQueryType flag){
		this.fieldName = fieldName;
		this.flag = flag;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public ESSearchType getSearchType() {
		return searchType;
	}

	public void setSearchType(ESSearchType searchType) {
		this.searchType = searchType;
	}

	public ESQueryType getFlag() {
		return flag;
	}

	public void setFlag(ESQueryType flag) {
		this.flag = flag;
	}

	
}
