package com.tsl.service;

import java.util.List;

import com.tsl.pojo.Owner;

public interface OwnerService {

	public int insert(Owner owner);

	public int update(Owner owner);

	public int deleteById(int id);

	public Owner selectById(int id);

	public List<Owner> selectByLike(String str);

	public List<Owner> selectAll();
}
