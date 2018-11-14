package com.tsl.service;

import java.util.List;

import com.tsl.pojo.Building;

public interface BuildingService {

	public int insert(Building building);

	public int update(Building building);

	public int deleteById(int id);

	public Building selectById(int id);

	public List<Building> selectByLike(String str);

	public List<Building> selectAll();
	
	public List<Building> selAddrByAddrState(String address);

}
