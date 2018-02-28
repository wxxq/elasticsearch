package com.melochey.elastic.entity.ES;

import java.util.List;

public class BoolField extends BaseField {
	
	private List<BaseField> childBool;

	public BoolField(List<BaseField> list){
		super("",ESQueryType.BOOL);
		this.childBool = list;
	}
	
	public BoolField(List<BaseField> list,ESSearchType searchType){
		super("",ESQueryType.BOOL,searchType);
	}
	
	public List<BaseField> getChildBool() {
		return childBool;
	}

	public void setChildBool(List<BaseField> childBool) {
		this.childBool = childBool;
	}
}
