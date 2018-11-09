package com.project.stationdata.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;

import com.project.stationdata.dao.mapper.StationMapper;
import com.project.stationdata.model.Station;

@RunWith(MockitoJUnitRunner.class)
public class StationDaoTest {
	
	@InjectMocks
	private StationDao stationdao;
	
	@Mock
	private StationMapper mapper;
	
	Station mockStation1 = new Station("Nine","Jazz",true,"KUII");
	Station mockStation2 = new Station("Ten","90s",true,"KFYR");
	List<Station> mockStations = new ArrayList<Station>(){{add(mockStation1);add(mockStation2);}};
	
	@Test
	public void getStation_Test() throws Exception
	{
		Mockito.when(mapper.getStation(Mockito.anyString())).thenReturn(mockStation1);
		Station result = stationdao.getStation("Nine");
        String expectedId = "Nine";
		JSONAssert.assertEquals(expectedId, result.getStationId(), false);	
	}
	
	@Test
	public void getStationByName_Test() throws Exception
	{
		Mockito.when(mapper.getStationByName(Mockito.anyString())).thenReturn(mockStations);
		List<Station> result = stationdao.getStationByName("Jazz");
        String expectedName = "Jazz";
		JSONAssert.assertEquals(expectedName, result.get(0).getName(), false);
		
	}
	@Test
	public void getStationByHDenabled_Test() throws Exception
	{
		Mockito.when(mapper.getStationByHDenabled(Mockito.anyBoolean())).thenReturn(mockStations);
		List<Station> result = stationdao.getStationByHDenabled("true");
        String expectedName = "Ten";
		JSONAssert.assertEquals(expectedName, result.get(1).getStationId(), false);
		
	}
	@Test
	public void getAllStations_Test() throws Exception
	{
		Mockito.when(mapper.getAllStations()).thenReturn(mockStations);
		List<Station> result = stationdao.getAllStations();
        String expectedName = "Ten";
		JSONAssert.assertEquals(expectedName, result.get(1).getStationId(), false);
		
	}
	
	@Test
	public void addStation_Test() throws Exception {
		Mockito.when(mapper.addStation(Mockito.any(Station.class))).thenReturn(1);
		boolean result=stationdao.addStation(mockStation1);
		assertEquals(true,result);
	}	
	@Test
	public void updateStation_Test() throws Exception {
		Mockito.when(mapper.updateStation(Mockito.any(Station.class))).thenReturn(1);
		int result=stationdao.updateStation(mockStation1);
		assertEquals(1,result);
	}	
	
	public int updateStation(Station station) {
		return mapper.updateStation(station);
	}

}
