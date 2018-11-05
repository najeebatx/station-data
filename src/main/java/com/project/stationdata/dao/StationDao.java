package com.project.stationdata.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.stationdata.dao.mapper.StationMapper;
import com.project.stationdata.model.Station;

@Component
public class StationDao {
	
	@Autowired
	StationMapper mapper;
	
	public boolean addStation(Station station){
		boolean isAdded = mapper.addStation(station) > 0;
		return isAdded;
	}

}
