package com.melochey.elastic.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ESParam {

	private int from = 0;
	private int size = 10;

	private List<BaseField> fieldList = new ArrayList<>();

	private Map<String, Boolean> sortKeys = new HashMap<>();

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
