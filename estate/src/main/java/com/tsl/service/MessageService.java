package com.tsl.service;

import java.util.List;

import com.tsl.pojo.Message;

public interface MessageService {

	public int insert(Message message);

	public int update(Message message);

	public int deleteById(int id);

	public Message selectById(int id);

	public List<Message> selectByLike(String str);

	public List<Message> selectAll();
	
	public List<Message> selectAllByOwnerId(int owner_id);

	public int selCountState(String state,int owner_id);

}
