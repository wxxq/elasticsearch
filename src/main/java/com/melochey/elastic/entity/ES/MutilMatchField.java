package com.melochey.elastic.entity.ES;

import java.util.List;

/*
 * @author melo
*/
public class MutilMatchField extends MatchField {

	private String[] mutilFields;


	public MutilMatchField(Object queryValue, String[] mutilFields) {
		super("",queryValue, ESQueryType.MUITL_MATCH);
		this.mutilFields = mutilFields;
	}

	public String[] getMutilFields() {
		return mutilFields;
	}

	public void setMutilFields(String[] mutilFields) {
		this.mutilFields = mutilFields;
	}


}
