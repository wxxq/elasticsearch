package com.melochey.elastic.entity.ES;

import java.util.List;
import java.util.Map;

public class ESParam {

	private int from = 0;
	private int size = 10;
	
	// query condition
	public List<BaseField> fieldList ;
	
	// need sorted field
	public Map<String, Boolean> sortKeys;
	
	// displayed fields
	public String[] searchedFields;
	
	// group by fields
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
