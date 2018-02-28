package com.melochey.elastic.entity.ES;

import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.search.MatchQuery.Type;

public class MatchField extends BaseField {

	private Operator operator = Operator.OR;
	
	private String minimum_should_match = "1";

	private String fieldValue;
	
	private Type type = Type.BOOLEAN;
	
	public Type getType() {
		return type;
	}


	public void setType(Type type) {
		this.type = type;
	}


	public MatchField(String fieldName) {
		super(fieldName, ESQueryType.MATCH);
	}


	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public String getMinimum_should_match() {
		return minimum_should_match;
	}

	public void setMinimum_should_match(String minimum_should_match) {
		this.minimum_should_match = minimum_should_match;
	}


	public String getFieldValue() {
		return fieldValue;
	}


	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	
}
