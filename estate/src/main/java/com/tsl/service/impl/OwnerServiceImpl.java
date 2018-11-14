package com.tsl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.tsl.mapper.OwnerMapper;
import com.tsl.pojo.Owner;
import com.tsl.service.OwnerService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class OwnerServiceImpl implements OwnerService {

	@Autowired
	private OwnerMapper ownerMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int insert(Owner owner){
		return ownerMapper.insertSelective(owner);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int update(Owner owner){
		return ownerMapper.updateByPrimaryKeySelective(owner);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int deleteById(int id){
		return ownerMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Owner selectById(int id){
		return ownerMapper.selectByPrimaryKey(id);
	}


	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Owner> selectByLike(String str){
		Example example = new Example(Owner.class);
		Criteria criteria = example.createCriteria();
		
		if (!StringUtils.isEmptyOrWhitespace(str)) {
			criteria.andLike("oname", "%" + str + "%");
		}
		if (!StringUtils.isEmptyOrWhitespace(str)) {
			criteria.orLike("phone", "%" + str + "%");
		}
		if (!StringUtils.isEmptyOrWhitespace(str)) {
			criteria.orLike("remarks", "%" + str + "%");
		}
		List<Owner> list = ownerMapper.selectByExample(example);
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Owner> selectAll(){
		return ownerMapper.selectAll();
	}
	
	

}
