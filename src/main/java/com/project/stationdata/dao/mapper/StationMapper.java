package com.project.stationdata.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.stationdata.model.Station;

@Mapper
public interface StationMapper {
	
	@Insert("insert into station(stationid,name,hdenabled,callsign) values (#{stationId},#{name},#{hdEnabled},#{callSign})")
	public int addStation(Station station);
	@Select("select * from station where stationid=#{stationId}")
	public Station getStation(String stationId);
	@Update("update station set stationid=#{stationId},name=#{name},hdenabled=#{hdEnabled},callsign=#{callSign} "
			+ "where stationid=#{stationId}")
	public int updateStation(Station station);
	@Delete("delete from station where stationId=#{stationId}")
	void deleteStation(String stationId);
	@Select("select stationid,name,hdenabled,callsign from station where name=#{name}")
	public List<Station> getStationByName(String name);
	@Select("select * from station where hdenabled=#{hdenabled}")
	public List<Station> getStationByHDenabled(boolean hdenabled);
	@Select("select * from station")
	public List<Station> getAllStations();
}
