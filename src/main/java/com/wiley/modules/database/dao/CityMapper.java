package com.wiley.modules.database.dao;

import com.wiley.modules.database.vo.City;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CityMapper {

	public City findCity(Long id);
	
	public int insert(City city);

	public List<City> getAllCity();
}
