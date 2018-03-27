package com.wiley.modules.database.service;

import com.wiley.modules.database.vo.City;

import java.util.List;

public interface CityService {

	public int insertCity(City city);
	
	
	public City  findList(Long id);

	public List<City> getAllCity();
}
