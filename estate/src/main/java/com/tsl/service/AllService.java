package com.tsl.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tsl.pojo.All;

public interface AllService {

	public List<All> selOwnerAndUser(String str);
	
	public List<All> selBuildingAndOwner(String str);
	
	public List<All> selParkingAndOwner(String str);

	public List<All> selCostAndOwner(String str);
	
	public List<All> selMessageAndOwner(String str);
	
	public List<All> selUserAndOwner(String str);
	
	public List<String> selAddress();
	
	public Integer selBuildingIdByName(String name);
	
	public Integer setBuildOwneridForNull(Integer building_id);
	
	public Integer setParkingOwneridForNull(Integer parking_id);
	
	public List<String> selParkingNum();

}
