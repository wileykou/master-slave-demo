package com.wiley.modules.database.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wiley.modules.database.service.CityService;
import com.wiley.modules.database.vo.City;

@RestController
@RequestMapping("/city")
public class CityController {

	private Logger log=LoggerFactory.getLogger(CityController.class);
	
	@Autowired
	private CityService cityServiceImpl;


	@GetMapping
	public Object getAllCity() {
		return cityServiceImpl.getAllCity();
	}

	@GetMapping("/{id}")
	public Object getCity(@PathVariable("id") Long id){
	   return	cityServiceImpl.findList(id);
	}
	@GetMapping(value="insertCity")
	public Object insertCity(City city){
	    log.info(city.toString());
	    return cityServiceImpl.insertCity(city);
	}
}
