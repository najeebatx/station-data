package com.project.stationdata.model;

public class Station {
	
	String stationId;
	String name;
	Boolean hdEnabled;
	String callSign;
	
	public Station(){
	}
	
	public Station(String stationId, String name, Boolean hdEnabled, String callSign) {
		this.stationId = stationId;
		this.name = name;
		this.hdEnabled = hdEnabled;
		this.callSign = callSign;
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	public String getCallSign() {
		return callSign;
	}
	public Boolean getHdEnabled() {
		return hdEnabled;
	}
	public void setHdEnabled(Boolean hdEnabled) {
		this.hdEnabled = hdEnabled;
	}
	public void setCallSign(String callSign) {
		this.callSign = callSign;
	}

	@Override
	public String toString() {
		return "Station [stationId=" + stationId + ", name=" + name + ", hdEnabled=" + hdEnabled + ", callSign="
				+ callSign + "]";
	}

}
