package com.tsl.service;

import java.util.List;

import com.tsl.pojo.Parking;

public interface ParkingService {

	public int insert(Parking parking);

	public int update(Parking parking);

	public int deleteById(int id);

	public Parking selectById(int id);

	public List<Parking> selectByLike(String str);

	public List<Parking> selectAll();
}
