package com.wiley.modules.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiley.modules.database.dao.CityMapper;
import com.wiley.modules.database.service.CityService;
import com.wiley.modules.database.vo.City;

import java.util.List;

@Service
public class CityServiceImpl  implements  CityService {

	@Autowired
	private CityMapper cityMapper;
	@Override
	public int insertCity(City city) {
		return cityMapper.insert(city);
		
	}

	@Override
	public City findList(Long id) {
		// TODO Auto-generated method stub
		return cityMapper.findCity(id);
	}

	@Override
	public List<City> getAllCity() {
		// TODO Auto-generated method stub
		return cityMapper.getAllCity();
	}
}
