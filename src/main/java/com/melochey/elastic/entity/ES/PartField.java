package com.melochey.elastic.entity.ES;

/*
 * @author melo
*/
public class PartField extends BaseField {
	
	private String value;

	public PartField(String fieldName,String value, ESQueryType flag) {
		super(fieldName, flag);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
