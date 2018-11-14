package com.tsl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tsl.pojo.All;
import com.tsl.pojo.Building;
import com.tsl.pojo.Owner;
import com.tsl.pojo.Parking;
import com.tsl.service.AllService;
import com.tsl.service.OwnerService;
import com.tsl.service.ParkingService;
import com.tsl.utils.State;

@Controller
@RequestMapping("/parking")
public class ParkingContoller {

	@Autowired
	private ParkingService parkingService;
	@Autowired
	private OwnerService ownerService;
	@Autowired
	private AllService allService;
	
	@RequestMapping("/list")
	public String list(String search,Integer pageNum,HttpSession session,HttpServletRequest request) {
		if (pageNum == null||pageNum<=0) {
			pageNum = 1;
		}
		if (search == null||search.equals("null")||search.equals("")) {
			search = null;
		}
		
		// 开始分页
		Page<Object> startPage = PageHelper.startPage(pageNum, 10);
		
		List<All> list = allService.selParkingAndOwner(search);
		
		request.setAttribute("search", search);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageSize", startPage.getPages());
		request.setAttribute("list", list);
		return "parking_list";
	}
	
	@RequestMapping("/updinfo")
	public String updinfo(int parking_id,HttpServletRequest request) {
		Parking parking = parkingService.selectById(parking_id);
		Owner owner = ownerService.selectById(parking.getOwnerId());
		List<String> p_num = allService.selParkingNum();
		request.setAttribute("p_num", p_num);
		request.setAttribute("parking", parking);
		request.setAttribute("owner", owner);
		
		return "parking_upd";
	}
	
	@RequestMapping("/upd")
	@ResponseBody
	public State upd(String old_num,String new_num,int owner_id,HttpServletRequest request) {
		int new_bid = Integer.parseInt(new_num.substring(1));
		int old_bid = Integer.parseInt(old_num.substring(1));
		Parking parking=new Parking();
		parking.setParkingId(new_bid);
		parking.setState("出租");
		parking.setOwnerId(owner_id);
		parkingService.update(parking);
		State state = new State();
		int i = allService.setParkingOwneridForNull(old_bid);
		if (i == 1) {
			state.setIsSuccess(true);
			state.setMessage("修改成功");
			return state;
		} else {
			state.setIsSuccess(false);
			state.setMessage("修改失败");
			return state;
		}
	}
}
