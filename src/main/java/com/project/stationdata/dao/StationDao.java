package com.project.stationdata.dao;

import java.util.List;

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
	public Station getStation(String id) {
		return mapper.getStation(id);
	}
	
	public int updateStation(Station station) {
		return mapper.updateStation(station);
	}
	public void removeStation(String stationId) {
		mapper.deleteStation(stationId);
	}

	public List<Station> getStationByName(String name) {
		return mapper.getStationByName(name);
	}

	public List<Station> getStationByHDenabled(String enabled) {
		boolean hdenabled=false;
		if(enabled.equals("TRUE")||enabled.equals("true")||enabled.equals("True")){
				hdenabled=true;
	}
		return mapper.getStationByHDenabled(hdenabled);
	}

	public List<Station> getAllStations() {
		return mapper.getAllStations();
	}

}
