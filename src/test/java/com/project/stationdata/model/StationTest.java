package com.project.stationdata.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class StationTest {


	Station station = new Station("One","70s",true,"KABC");	
	@Test	
	public void getStationId_Test(){
		String result=station.getStationId();
		assertEquals("One",result);
	}
	@Test	
	public void setStationId_Test(){
		station.setStationId("Two");
		String result=station.getStationId();
		assertEquals("Two",result);
	}
	@Test	
	public void getStationName_Test(){
		String result=station.getName();
		assertEquals("70s",result);
	}
	@Test	
	public void setStationName_Test(){
		station.setName("Today's Music");
		String result=station.getName();
		assertEquals("Today's Music",result);
	}
	@Test	
	public void getStationHDenabled_Test(){
		Boolean result=station.hdEnabled;
		assertEquals(true,result);
	}
	@Test	
	public void setStationHDenabled_Test(){
		station.setHdEnabled(false);
		Boolean result=station.hdEnabled;
		assertEquals(false,result);
	}
	@Test	
	public void getStationCallSign_Test(){
		String result=station.getCallSign();
		assertEquals("KABC",result);
	}
	@Test	
	public void setStationCallSign_Test(){
		station.setCallSign("KXYZ");
		String result=station.getCallSign();
		assertEquals("KXYZ",result);
	}

	@Test
	public void toString_Test(){
		String result=station.toString();
		assertEquals("Station [stationId=One, name=70s, hdEnabled=true, callSign=KABC]",result);
	}
	


}
