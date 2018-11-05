package com.project.stationdata.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.project.stationdata.model.Station;

@Mapper
public interface StationMapper {
	
	@Insert("insert into station(stationid,name,hdenabled,callsign) values (#{stationId},#{name},#{hdEnabled},#{callSign})")
	public int addStation(Station station);
}
