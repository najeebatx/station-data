package com.project.stationdata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.project")
@MapperScan(basePackages = "com.project.stationdata.dao.mapper")
public class StationDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(StationDataApplication.class, args);
	}
}
