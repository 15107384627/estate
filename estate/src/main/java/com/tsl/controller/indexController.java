package com.tsl.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tsl.pojo.Building;
import com.tsl.pojo.Parking;
import com.tsl.pojo.User;
import com.tsl.service.BuildingService;
import com.tsl.service.CostService;
import com.tsl.service.MessageService;
import com.tsl.service.ParkingService;
import com.tsl.service.UserService;
import com.tsl.utils.State;

@Controller
@RequestMapping("/index")
public class indexController {

	@Autowired
	private UserService userService;
	@Autowired
	private BuildingService buildingService;
	@Autowired
	private ParkingService parkingService;
	@Autowired
	private CostService costService;
	@Autowired
	private MessageService messageService;

	@RequestMapping("/rePwdInfo")
	public String rePwdInfo() {
		return "revise_password";
	}

	@RequestMapping("/revisePwd")
	@ResponseBody
	public State revisePwd(String pwd,HttpSession session) {

		int id = (int) session.getAttribute("userId");
		User user=new User();
		user.setUserId(id);
		user.setUserpwd(pwd);
		
		int i = userService.update(user);

		State state = new State();
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

	@RequestMapping("/top")
	public String top() {
		return "top";
	}

	@RequestMapping("/menu")
	public String menu() {
		return "menu";
	}

	@RequestMapping("/bar")
	public String bar() {
		return "bar";
	}

	@RequestMapping("/main")
	public String main(HttpSession session,HttpServletRequest request) {
		int id = (int) session.getAttribute("userId");
		Building building = buildingService.selectById(id);
		Parking parking = parkingService.selectById(id);
		int cost_yes = costService.selCountState("已交",id);
		int cost_no = costService.selCountState("未交",id);
		int msg_yes = messageService.selCountState("已完成",id);
		int msg_no = messageService.selCountState("未完成",id);
		request.setAttribute("building", building);
		request.setAttribute("parking", parking);
		request.setAttribute("cost_yes", cost_yes);
		request.setAttribute("cost_no", cost_no);
		request.setAttribute("msg_yes", msg_yes);
		request.setAttribute("msg_no", msg_no);
		return "main";
	}
}
