package com.melochey.elastic.entity.ES;

public class TermField extends BaseField {

	private Object fieldValue;

	public TermField(String fieldName, Object fieldValue) {
		super(fieldName,ESQueryType.TERM);
		this.fieldValue = fieldValue;
	}
	
	public TermField(String fieldName, Object fieldValue,ESSearchType searchType) {
		super(fieldName,ESQueryType.TERM,searchType);
		this.fieldValue = fieldValue;
	}

	public Object getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}

}
