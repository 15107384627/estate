package com.tsl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.tsl.mapper.CostMapper;
import com.tsl.pojo.Cost;
import com.tsl.service.CostService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class CostServiceImpl implements CostService {

	@Autowired
	private CostMapper costMapper;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int insert(Cost cost){
		return costMapper.insertSelective(cost);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int update(Cost cost){
		return costMapper.updateByPrimaryKeySelective(cost);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int deleteById(int id){
		return costMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Cost selectById(int id){
		return costMapper.selectByPrimaryKey(id);
	}


	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Cost> selectByLike(String str){
		Example example = new Example(Cost.class);
		Criteria criteria = example.createCriteria();
		
		if (!StringUtils.isEmptyOrWhitespace(str)) {
			criteria.andLike("cname", "%" + str + "%");
		}
		if (!StringUtils.isEmptyOrWhitespace(str)) {
			criteria.orLike("time", "%" + str + "%");
		}
		if (!StringUtils.isEmptyOrWhitespace(str)) {
			criteria.orLike("state", "%" + str + "%");
		}
		example.orderBy("time").desc();
		List<Cost> list = costMapper.selectByExample(example);
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Cost> selectAll(){
        Example example = new Example(Cost.class);
		Criteria criteria = example.createCriteria();
		
		example.orderBy("time").desc();
        
		return costMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public int selCountState(String state,int owner_id) {
		Example example = new Example(Cost.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("state", state);
		criteria.andEqualTo("ownerId", owner_id);
		return costMapper.selectCountByExample(example);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Cost> selectAllByOwnerId(int owner_id) {
		Example example = new Example(Cost.class);
		Criteria criteria = example.createCriteria();
		example.orderBy("time").desc();
		criteria.andEqualTo("ownerId", owner_id);
        
		return costMapper.selectByExample(example);
	}

}
