package com.melochey.elastic.entity.ES;

import java.util.List;

public class BoolField extends BaseField {
	
	private List<BaseField> childBool;

	
	public List<BaseField> getChildBool() {
		return childBool;
	}

	public void setChildBool(List<BaseField> childBool) {
		this.childBool = childBool;
	}
}
