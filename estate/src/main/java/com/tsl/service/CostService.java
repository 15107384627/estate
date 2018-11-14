package com.tsl.service;

import java.util.List;

import com.tsl.pojo.Cost;

public interface CostService {

	public int insert(Cost cost);

	public int update(Cost cost);

	public int deleteById(int id);

	public Cost selectById(int id);

	public List<Cost> selectByLike(String str);

	public List<Cost> selectAll();
	
	public List<Cost> selectAllByOwnerId(int owner_id);
	
	public int selCountState(String state,int owner_id);
}
