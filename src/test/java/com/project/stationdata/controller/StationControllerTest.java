package com.project.stationdata.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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

	String mockStation = "{\"stationId\":\"11\",\"name\":\"Jazz\",\"hdEnabled\":true,\"callSign\":\"KMNR\"}";
	@Test
	public void addStation_Test() throws Exception {
		Mockito.when(stationdao.addStation(Mockito.any(Station.class))).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/stations")
				.accept(MediaType.APPLICATION_JSON)
				.content(mockStation)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		assertEquals("http://localhost/stations/11",
				response.getHeader(HttpHeaders.LOCATION));
	}
	
	
}


