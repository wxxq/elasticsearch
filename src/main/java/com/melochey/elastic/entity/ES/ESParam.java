package com.melochey.elastic.entity.ES;

import java.util.List;
import java.util.Map;

public class ESParam {

	private int from = 0;
	private int size = 10;

	public List<BaseField> fieldList ;

	public Map<String, Boolean> sortKeys;
	
	public String[] searchedFields;
	
	public List<String> aggregationFields;
	
	// key:field name  value:metric value
	public Map<String,ESMetrics> aggregationMetrics;
	
	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<BaseField> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<BaseField> fieldList) {
		this.fieldList = fieldList;
	}

}
