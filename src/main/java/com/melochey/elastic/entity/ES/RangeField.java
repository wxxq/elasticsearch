package com.melochey.elastic.entity.ES;

public class RangeField extends BaseField {
	private Object lowerValue;
	private boolean includeLower;
	private Object upperValue;
	private boolean includeUpper;

	public RangeField(String fieldName, Object lower, boolean includeLower, Object upper, boolean includeUpper) {
		super(fieldName,ESQueryType.RANGE);
		this.lowerValue = lower;
		this.includeLower = includeLower;
		this.upperValue = upper;
		this.includeUpper = includeUpper;
	}
	
	public RangeField(String fieldName, Object lower, boolean includeLower, Object upper, boolean includeUpper,ESSearchType searchType) {
		super(fieldName,ESQueryType.RANGE,searchType);
		this.lowerValue = lower;
		this.includeLower = includeLower;
		this.upperValue = upper;
		this.includeUpper = includeUpper;
	}

	public Object getLowerValue() {
		return lowerValue;
	}

	public void setLowerValue(Object lowerValue) {
		this.lowerValue = lowerValue;
	}

	public boolean isIncludeLower() {
		return includeLower;
	}

	public void setIncludeLower(boolean includeLower) {
		this.includeLower = includeLower;
	}

	public Object getUpperValue() {
		return upperValue;
	}

	public void setUpperValue(Object upperValue) {
		this.upperValue = upperValue;
	}

	public boolean isIncludeUpper() {
		return includeUpper;
	}

	public void setIncludeUpper(boolean includeUpper) {
		this.includeUpper = includeUpper;
	}

}
