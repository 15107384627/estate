package com.tsl.service;

import java.util.List;

import com.tsl.pojo.User;


public interface UserService {

	public int insert(User user);

	public int update(User user);

	public int deleteById(int id);

	public User selectById(int id);

	public List<User> selectByLike(String str);

	public List<User> selectAll();
	
	public User userLogin(User user);

	
}
