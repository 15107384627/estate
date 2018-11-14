package com.tsl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tsl.pojo.All;
import com.tsl.pojo.Building;
import com.tsl.pojo.Owner;
import com.tsl.pojo.User;
import com.tsl.service.AllService;
import com.tsl.service.BuildingService;
import com.tsl.service.OwnerService;
import com.tsl.service.UserService;
import com.tsl.utils.State;

@Controller
@RequestMapping("/owner")
public class OwnerContoller {

	@Autowired
	private OwnerService ownerService;
	@Autowired
	private UserService userService;
	@Autowired
	private BuildingService buildingService;
	@Autowired
	private AllService allService;

	@RequestMapping("/list")
	public String list(String search, Integer pageNum, HttpSession session, HttpServletRequest request) {
		if (pageNum == null || pageNum <= 0) {
			pageNum = 1;
		}
		if (search == null || search.equals("null") || search.equals("")) {
			search = null;
		}

		// 开始分页
		Page<Object> startPage = PageHelper.startPage(pageNum, 10);
		if(pageNum>startPage.getPages()) {
			pageNum=startPage.getPages();
		}
		List<All> list = allService.selOwnerAndUser(search);

		request.setAttribute("search", search);
		request.setAttribute("pageNum", startPage.getPageNum());
		request.setAttribute("pageSize", startPage.getPages());
		request.setAttribute("list", list);

		return "owner_list";
	}

	@RequestMapping("/del")
	@ResponseBody
	public State del(int id, HttpServletRequest request) {
		State state = new State();
		int i = ownerService.deleteById(id);
		if (i == 1) {
			state.setIsSuccess(true);
			state.setMessage("删除成功");
			return state;
		} else {
			state.setIsSuccess(false);
			state.setMessage("删除失败");
			return state;
		}
	}

	@RequestMapping("/updinfo")
	public String updinfo(int id,String username,HttpServletRequest request) {
		Owner owner = ownerService.selectById(id);
		
		request.setAttribute("username", username);
		request.setAttribute("owner", owner);
		
		return "owner_upd";
	}
	
	@RequestMapping("/upd")
	@ResponseBody
	public State upd(@RequestBody Owner owner,HttpServletRequest request) {
		
		State state = new State();
		int i = ownerService.update(owner);
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

	@RequestMapping("/addinfo")
	public String addinfo(HttpServletRequest request) {

		List<String> address = allService.selAddress();
		request.setAttribute("address", address);
		return "owner_add";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public State add(@RequestBody All all) {
		State state = new State();
		
		User user=new User();
		user.setUsername(all.getPhone());
		user.setUserpwd(all.getPhone().substring(7, 11));
		int i3=userService.insert(user);
		Owner owner=new Owner();
		owner.setOname(all.getOname());
		owner.setPhone(all.getPhone());
		owner.setRemarks(all.getRemarks());
		int i = ownerService.insert(owner);
		int owner_id = ownerService.selectByLike(all.getPhone()).get(0).getOwnerId();
		Building building=new Building();
		building.setBuildingId(all.getBuilding_id());
		building.setState(all.getState());
		building.setOwnerId(owner_id);
		int i2 = buildingService.update(building);
		if (i == 1&& i2==1) {
			state.setIsSuccess(true);
			state.setMessage("添加成功");
			return state;
		} else {
			state.setIsSuccess(false);
			state.setMessage("添加失败");
			return state;
		}
	}
}
