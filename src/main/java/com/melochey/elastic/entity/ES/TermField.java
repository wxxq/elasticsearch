package com.melochey.elastic.entity.ES;

public class TermField extends BaseField {


	public TermField(String fieldName, Object fieldValue) {
		super(fieldName,fieldValue,ESQueryType.TERM);
	}
	
	public TermField(String fieldName, Object fieldValue,ESSearchType searchType) {
		super(fieldName,fieldValue,ESQueryType.TERM,searchType);
	}

}
