package com.tsl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.tsl.mapper.BuildingMapper;
import com.tsl.pojo.Building;
import com.tsl.service.BuildingService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	private BuildingMapper buildingMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int insert(Building building){
		return buildingMapper.insertSelective(building);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int update(Building building){
		return buildingMapper.updateByPrimaryKeySelective(building);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int deleteById(int id){
		return buildingMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Building selectById(int id){
		return buildingMapper.selectByPrimaryKey(id);
	}


	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Building> selectByLike(String str){

		Example example = new Example(Building.class);
		Criteria criteria = example.createCriteria();
		
		if (!StringUtils.isEmptyOrWhitespace(str)) {
			criteria.andLike("bname", "%" + str + "%");
		}
		if (!StringUtils.isEmptyOrWhitespace(str)) {
			criteria.orLike("address", "%" + str + "%");
		}
		if (!StringUtils.isEmptyOrWhitespace(str)) {
			criteria.orLike("state", "%" + str + "%");
		}
		List<Building> list = buildingMapper.selectByExample(example);
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Building> selectAll(){
		return buildingMapper.selectAll();
	}


	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Building> selAddrByAddrState(String address) {
		Example example = new Example(Building.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("address", address);
		criteria.andEqualTo("state", "空闲");
		return buildingMapper.selectByExample(example);
	}
	
	

}
