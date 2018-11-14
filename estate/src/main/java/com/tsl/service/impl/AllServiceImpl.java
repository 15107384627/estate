package com.tsl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.tsl.mapper.AllMapperCustom;
import com.tsl.mapper.BuildingMapper;
import com.tsl.mapper.CostMapper;
import com.tsl.mapper.MessageMapper;
import com.tsl.mapper.OwnerMapper;
import com.tsl.mapper.ParkingMapper;
import com.tsl.mapper.UserMapper;
import com.tsl.pojo.All;
import com.tsl.service.AllService;

@Service
public class AllServiceImpl implements AllService {

	@Autowired
	private BuildingMapper buildingMapper;
	@Autowired
	private CostMapper costMapper;
	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private OwnerMapper ownerMapper;
	@Autowired
	private ParkingMapper parkingMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private AllMapperCustom allMapperCustom;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<All> selOwnerAndUser(String str) {
		return allMapperCustom.selOwnerAndUser(str);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<All> selBuildingAndOwner(String str) {
		return allMapperCustom.selBuildingAndOwner(str);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<All> selParkingAndOwner(String str) {
		return allMapperCustom.selParkingAndOwner(str);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<All> selCostAndOwner(String str) {
		return allMapperCustom.selCostAndOwner(str);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<All> selMessageAndOwner(String str) {
		return allMapperCustom.selMessageAndOwner(str);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<All> selUserAndOwner(String str) {
		return allMapperCustom.selUserAndOwner(str);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<String> selAddress() {
		return allMapperCustom.selAddress();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Integer selBuildingIdByName(String name) {
		return allMapperCustom.selBuildingIdByName(name);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Integer setBuildOwneridForNull(Integer building_id) {
		return allMapperCustom.setBuildOwneridForNull(building_id);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Integer setParkingOwneridForNull(Integer parking_id) {
		return allMapperCustom.setParkingOwneridForNull(parking_id);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<String> selParkingNum() {
		return allMapperCustom.selParkingNum();
	}

}
