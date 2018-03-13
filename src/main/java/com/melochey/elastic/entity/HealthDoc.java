package com.melochey.elastic.entity;
/**
 * health document 
 * @author chey
 *
 */
public class HealthDoc {
	private String personalName;
	private String idCard;
	private long provinceId;
	private long cityId;
	private long districtId;
	public String getPersonalName() {
		return personalName;
	}
	public void setPersonalName(String personalName) {
		this.personalName = personalName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public long getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(long l) {
		this.provinceId = l;
	}
	public long getCityId() {
		return cityId;
	}
	public void setCityId(long l) {
		this.cityId = l;
	}
	public long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(long districtId) {
		this.districtId = districtId;
	}
}
