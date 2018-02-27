package com.melochey.elastic.entity.ES;

public class GenerateField extends BaseField {

	private Object fieldValue;

	public GenerateField(String fieldName, Object fieldValue, ESQueryType flag) {
		super(fieldName,flag);
		this.fieldValue = fieldValue;
	}

	public Object getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}

}
