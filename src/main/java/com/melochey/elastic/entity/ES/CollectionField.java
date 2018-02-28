package com.melochey.elastic.entity.ES;

import java.util.List;

public class CollectionField extends BaseField{
	private List<Object> fieldCollection;
	
	public CollectionField(String fieldName, List<Object> fieldCollection, ESQueryType flag){
		super(fieldName,flag);
		this.fieldCollection = fieldCollection;
	}
	
	public CollectionField(String fieldName, List<Object> fieldCollection, ESQueryType flag,ESSearchType searchType){
		super(fieldName,flag);
		this.fieldCollection = fieldCollection;
		this.searchType = searchType;
	}

	public List<Object> getFieldCollection() {
		return fieldCollection;
	}

	public void setFieldCollection(List<Object> fieldCollection) {
		this.fieldCollection = fieldCollection;
	}
}
