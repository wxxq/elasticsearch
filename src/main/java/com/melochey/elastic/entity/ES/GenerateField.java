package com.melochey.elastic.entity.ES;

public class GenerateField extends BaseField {

	private Object fieldValue;

	public GenerateField(String fieldName, Object fieldValue, int flag) {
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

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}
