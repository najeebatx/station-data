package com.project.stationdata.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.project.stationdata.dao.StationDao;
import com.project.stationdata.dao.mapper.StationMapper;
import com.project.stationdata.model.Station;

@RunWith(SpringRunner.class)
@WebMvcTest(StationController.class)
public class StationControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private StationDao stationdao;
	
	@MockBean
	private StationMapper stationmapper;
	
	
	Station mockStation1 = new Station("Nine","Jazz",true,"KUII");
	Station mockStation2 = new Station("Ten","90s",true,"KFYR");
	String mockStation = "{\"stationId\":\"Eleven\",\"name\":\"Jazz\",\"hdEnabled\":true,\"callSign\":\"KMNR\"}";
	@SuppressWarnings("serial")
	List<Station> mockStations = new ArrayList<Station>(){{add(mockStation1);add(mockStation2);}};
	
	@Test
	public void getStationById_Test() throws Exception {		
		Mockito.when(stationdao.getStation(Mockito.anyString())).thenReturn(mockStation1);
		RequestBuilder request = MockMvcRequestBuilders
				.get("/api/v1/stations/Nine")
				.accept(MediaType.APPLICATION_JSON);		
		MvcResult result = mockMvc.perform(request).andReturn();
		String expected = "{\"stationId\":\"Nine\",\"name\":\"Jazz\",\"hdEnabled\":true,\"callSign\":\"KUII\"}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	@Test
	public void getStationByName_Test() throws Exception {		
		Mockito.when(stationdao.getStationByName(Mockito.anyString())).thenReturn(mockStations);
		RequestBuilder request = MockMvcRequestBuilders
				.get("/api/v1/stations/name/Jazz")
				.accept(MediaType.APPLICATION_JSON);		
		MvcResult result = mockMvc.perform(request).andReturn();
		String expected = "[{\"stationId\":\"Nine\",\"name\":\"Jazz\",\"hdEnabled\":true,\"callSign\":\"KUII\"},"
				+ "{\"stationId\":\"Ten\",\"name\":\"90s\",\"hdEnabled\":true,\"callSign\":\"KFYR\"}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	@Test
	public void getAllStations_Test() throws Exception {
		
		Mockito.when(stationdao.getAllStations()).thenReturn(mockStations);
		RequestBuilder request = MockMvcRequestBuilders
				.get("/api/v1/stations")
				.accept(MediaType.APPLICATION_JSON);		
		MvcResult result = mockMvc.perform(request).andReturn();
		String expected = "[{\"stationId\":\"Nine\",\"name\":\"Jazz\",\"hdEnabled\":true,\"callSign\":\"KUII\"},"
				+ "{\"stationId\":\"Ten\",\"name\":\"90s\",\"hdEnabled\":true,\"callSign\":\"KFYR\"}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	@Test
	public void addStation_Test() throws Exception {
		Mockito.when(stationdao.addStation(Mockito.any(Station.class))).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/stations")
				.accept(MediaType.APPLICATION_JSON)
				.content(mockStation)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		assertEquals("http://localhost/api/v1/stations/Eleven",
				response.getHeader(HttpHeaders.LOCATION));
	}	
	@Test
	public void getStationByHDenabled_Test() throws Exception {
		
		Mockito.when(stationdao.getStationByHDenabled(Mockito.anyString())).thenReturn(mockStations);
		RequestBuilder request = MockMvcRequestBuilders
				.get("/api/v1/stations/hd?enabled=true")
				.accept(MediaType.APPLICATION_JSON);		
		MvcResult result = mockMvc.perform(request).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	@Test
	public void removeStation_Test() throws Exception {	
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/api/v1/stations/Fifteen")
				.accept(MediaType.APPLICATION_JSON);	
	   MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
	   assertEquals(200, mvcResult.getResponse().getStatus());
	   assertEquals(mvcResult.getResponse().getContentAsString(), "Station deleted successfully");
	}
	
}

