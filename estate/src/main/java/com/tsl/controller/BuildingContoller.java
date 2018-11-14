package com.tsl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tsl.pojo.All;
import com.tsl.pojo.Building;
import com.tsl.pojo.Owner;
import com.tsl.service.AllService;
import com.tsl.service.BuildingService;
import com.tsl.service.OwnerService;
import com.tsl.utils.State;

@Controller
@RequestMapping("/building")
public class BuildingContoller {

	@Autowired
	private BuildingService buildingService;
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
		List<All> list = allService.selBuildingAndOwner(search);
		
		request.setAttribute("search", search);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageSize", startPage.getPages());
		request.setAttribute("list", list);
		return "building_list";
	}

	@RequestMapping("/getAddrName")
	@ResponseBody
	public List<Building> getAddrName(String addr,HttpServletRequest request) {
		List<Building> selByAddrState = buildingService.selAddrByAddrState(addr);
		return selByAddrState;
	}
	
	@RequestMapping("/updinfo")
	public String updinfo(int building_id,HttpServletRequest request) {
		Building building = buildingService.selectById(building_id);
		Owner owner = ownerService.selectById(building.getOwnerId());
		List<String> address = allService.selAddress();
		request.setAttribute("address", address);
		request.setAttribute("building", building);
		request.setAttribute("owner", owner);
		
		return "building_upd";
	}
	
	@RequestMapping("/upd")
	@ResponseBody
	public State upd(int old_bid,int new_bid,int owner_id,HttpServletRequest request) {
		
		Building building=new Building();
		building.setBuildingId(new_bid);
		building.setState("出租");
		building.setOwnerId(owner_id);
		buildingService.update(building);
		State state = new State();
		int i = allService.setBuildOwneridForNull(old_bid);
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
