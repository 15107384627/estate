package com.tsl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tsl.pojo.All;

public interface AllMapperCustom {
	
	public List<All> selOwnerAndUser(@Param("str")String str);
	
	public List<All> selBuildingAndOwner(@Param("str")String str);
	
	public List<All> selParkingAndOwner(@Param("str")String str);
	
	public List<All> selCostAndOwner(@Param("str")String str);
	
	public List<All> selMessageAndOwner(@Param("str")String str);
	
	public List<All> selUserAndOwner(@Param("str")String str);
	
	public List<String> selAddress();
	
	public Integer selBuildingIdByName(@Param("name")String name);
	
	public Integer setBuildOwneridForNull(@Param("building_id")Integer building_id);
	
	public Integer setParkingOwneridForNull(@Param("parking_id")Integer parking_id);
	
	public List<String> selParkingNum();

}