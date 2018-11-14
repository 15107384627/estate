package com.tsl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.tsl.mapper.MessageMapper;
import com.tsl.pojo.Message;
import com.tsl.service.MessageService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageMapper messageMapper;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int insert(Message message){
		return messageMapper.insertSelective(message);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int update(Message message){
		return messageMapper.updateByPrimaryKeySelective(message);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int deleteById(int id){
		return messageMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Message selectById(int id){
		return messageMapper.selectByPrimaryKey(id);
	}


	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Message> selectByLike(String str){

		Example example = new Example(Message.class);
		Criteria criteria = example.createCriteria();
		
		if (!StringUtils.isEmptyOrWhitespace(str)) {
			criteria.andLike("mname", "%" + str + "%");
		}
		if (!StringUtils.isEmptyOrWhitespace(str)) {
			criteria.orLike("time", "%" + str + "%");
		}
		if (!StringUtils.isEmptyOrWhitespace(str)) {
			criteria.orLike("state", "%" + str + "%");
		}
		if (!StringUtils.isEmptyOrWhitespace(str)) {
			criteria.orLike("remarks", "%" + str + "%");
		}
		example.orderBy("time").desc();
		List<Message> list = messageMapper.selectByExample(example);
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Message> selectAll(){
        Example example = new Example(Message.class);
		Criteria criteria = example.createCriteria();
		
		example.orderBy("time").desc();
        
		return messageMapper.selectByExample(example);
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public int selCountState(String state,int owner_id) {
		Example example = new Example(Message.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("state", state);
		criteria.andEqualTo("ownerId", owner_id);
		return messageMapper.selectCountByExample(example);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Message> selectAllByOwnerId(int owner_id) {
		Example example = new Example(Message.class);
		Criteria criteria = example.createCriteria();
		
		example.orderBy("time").desc();
		criteria.andEqualTo("ownerId", owner_id);
        
		return messageMapper.selectByExample(example);
	}

}
