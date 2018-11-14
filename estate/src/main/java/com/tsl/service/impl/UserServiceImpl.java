package com.tsl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.tsl.mapper.UserMapper;
import com.tsl.pojo.User;
import com.tsl.service.UserService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int insert(User user){
		return userMapper.insertSelective(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int update(User user){
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int deleteById(int id){
		return userMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public User selectById(int id){
		return userMapper.selectByPrimaryKey(id);
	}


	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<User> selectByLike(String str){
		Example example = new Example(User.class);
		Criteria criteria = example.createCriteria();
		
		if (!StringUtils.isEmptyOrWhitespace(str)) {
			criteria.andLike("username", "%" + str + "%");
		}
		if (!StringUtils.isEmptyOrWhitespace(str)) {
			criteria.orLike("type", "%" + str + "%");
		}
		List<User> list = userMapper.selectByExample(example);
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<User> selectAll(){
		return userMapper.selectAll();
	}

	@Override
	public User userLogin(User user) {
		Example example = new Example(User.class);
		Criteria criteria = example.createCriteria();
		if(user.getUsername()!=null&&user.getUserpwd()!=null) {
			criteria.andEqualTo(user);
			return userMapper.selectOneByExample(example);
		}
		return null;
	}
	
	

}
