package com.project.stationdata.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.stationdata.dao.StationDao;
import com.project.stationdata.model.Station;

@RestController
public class StationController {
	
	@Autowired
	StationDao stationDao;
	
	@RequestMapping(method=RequestMethod.POST, path="/stations",consumes="application/json")
	public ResponseEntity<String> addStation(@RequestBody Station station){
		boolean isAdded=stationDao.addStation(station);
		
		if(isAdded){
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(station.getStationId()).toUri();
			return ResponseEntity.created(location).build();
		}
		
		return ResponseEntity.noContent().build();
	}
	@GetMapping(path="/stations/{id}",produces="application/json")
	public ResponseEntity<Station>  getStation(@PathVariable("id") String id){	
		return new ResponseEntity<Station>
		(stationDao.getStation(id),HttpStatus.OK);
		
	}
	@GetMapping(path="/stations",produces="application/json")
	public ResponseEntity<List<Station>>  getAllStations(){	
		List<Station> stationList=stationDao.getAllStations();
		return new ResponseEntity<List<Station>>
		(stationList,HttpStatus.OK);
		
	}
	@GetMapping(path="/stations/name/{name}",produces="application/json")
	public ResponseEntity<List<Station>> getStationByName(@PathVariable("name") String name){
		return new ResponseEntity<List<Station>>
		(stationDao.getStationByName(name),HttpStatus.OK);
		
	}
	@GetMapping(path="/stations/hd",produces="application/json")
	public ResponseEntity<List<Station>> getStationByHDenabled(@RequestParam("enabled") String enabled){
		return new ResponseEntity<List<Station>>
		(stationDao.getStationByHDenabled(enabled),HttpStatus.OK);
		
	}

	
	@PatchMapping(path="/stations/{id}",consumes="application/json")
	  public ResponseEntity<String> patchStation(@PathVariable("id") String id, @RequestBody Station partialStation) {
		 Station oldStation = stationDao.getStation(id);
		
	     if(partialStation.getHdEnabled()!=null) {
	    	 oldStation.setHdEnabled(partialStation.getHdEnabled());
	     }
	     if(partialStation.getName()!=null) {
	    	 oldStation.setName(partialStation.getName());
	     }
	     if(partialStation.getCallSign()!=null) {
	    	 oldStation.setCallSign(partialStation.getCallSign());
	     }
	     int update=stationDao.updateStation(oldStation);
	 	if(update>0){
	 		return new ResponseEntity<String>("Station update successfully",HttpStatus.OK);
		}
		
		return ResponseEntity.noContent().build();
	     
	     
	}
		
	@DeleteMapping("/stations/{id}")
	public ResponseEntity<String> removeStation(@PathVariable String id){
		stationDao.removeStation(id);
		return new ResponseEntity<String>
		("Station deleted successfully", HttpStatus.OK);
		
	}
}
