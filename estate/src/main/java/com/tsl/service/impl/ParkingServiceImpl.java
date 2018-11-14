package com.tsl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.tsl.mapper.ParkingMapper;
import com.tsl.pojo.Parking;
import com.tsl.service.ParkingService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class ParkingServiceImpl implements ParkingService {

	@Autowired
	private ParkingMapper parkingMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int insert(Parking parking){
		return parkingMapper.insertSelective(parking);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int update(Parking parking){
		return parkingMapper.updateByPrimaryKeySelective(parking);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int deleteById(int id){
		return parkingMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Parking selectById(int id){
		return parkingMapper.selectByPrimaryKey(id);
	}


	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Parking> selectByLike(String str){
		Example example = new Example(Parking.class);
		Criteria criteria = example.createCriteria();
		
		if (!StringUtils.isEmptyOrWhitespace(str)) {
			criteria.andLike("number", "%" + str + "%");
		}
		if (!StringUtils.isEmptyOrWhitespace(str)) {
			criteria.orLike("state", "%" + str + "%");
		}
		List<Parking> list = parkingMapper.selectByExample(example);
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Parking> selectAll(){
		return parkingMapper.selectAll();
	}
	
	

}
