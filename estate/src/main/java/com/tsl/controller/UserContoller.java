package com.tsl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tsl.pojo.All;
import com.tsl.pojo.Owner;
import com.tsl.pojo.User;
import com.tsl.service.AllService;
import com.tsl.service.OwnerService;
import com.tsl.service.UserService;
import com.tsl.utils.State;

@Controller
@RequestMapping("/user")
public class UserContoller {

	@Autowired
	private UserService userService;
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
		
		List<All> list = allService.selUserAndOwner(search);
		request.setAttribute("search", search);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageSize", startPage.getPages());
		request.setAttribute("list", list);
		return "user_list";
	}
	
	@RequestMapping("/updinfo")
	public String updinfo(int owner_id,int user_id,HttpServletRequest request) {
		Owner owner=null;
		if(owner_id>0) {
			owner = ownerService.selectById(owner_id);
			request.setAttribute("owner", owner);
		}
		User user = userService.selectById(user_id);
		
		request.setAttribute("user", user);
		
		return "user_upd";
	}
	
	@RequestMapping("/upd")
	@ResponseBody
	public State upd(int owner_id,int user_id,String oname,String type,String phone,String remarks,HttpServletRequest request) {
		State state = new State();
		
		Owner owner=new Owner();
		owner.setOwnerId(owner_id);
		owner.setOname(oname);
		owner.setPhone(phone);
		owner.setRemarks(remarks);
		
		User user=new User();
		user.setUserId(user_id);
		user.setType(type);
		int i =0;
		int j =0;
		if(owner.getOwnerId()>0&&user.getUserId()>0) {
			i = ownerService.update(owner);
			j = userService.update(user);
		}
		if (i==1&&j==1) {
			state.setIsSuccess(true);
			state.setMessage("修改成功");
			return state;
		} else {
			state.setIsSuccess(false);
			state.setMessage("修改失败");
			return state;
		}
	}
	
	@RequestMapping("/add")
	public String add() {
		
		return "user_add";
	}
}
