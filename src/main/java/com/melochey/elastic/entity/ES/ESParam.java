package com.melochey.elastic.entity.ES;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ESParam {

	private int from = 0;
	private int size = 10;

	public List<BaseField> fieldList = new ArrayList<>();

	public Map<String, Boolean> sortKeys = new HashMap<>();
	
	public String[] searchedFields;

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
