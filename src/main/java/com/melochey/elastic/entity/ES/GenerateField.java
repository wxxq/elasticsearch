package com.melochey.elastic.entity.ES;

public class GenerateField extends BaseField {

	private Object fieldValue;

	public GenerateField(String fieldName, Object fieldValue, ESQueryType flag) {
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.flag = flag;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Object getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}


}
