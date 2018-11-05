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
}
