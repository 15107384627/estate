package com.tsl.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tsl.pojo.User;
import com.tsl.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String login(HttpSession session,HttpServletRequest request) {
		session = request.getSession();
		if(session!=null) {
			session.removeAttribute("username");
		}
		return "login";
	}
	
	@RequestMapping("/islogin")
	@ResponseBody
	public boolean islogin(HttpSession session,HttpServletRequest request,@RequestBody User user) {
		User userLogin = userService.userLogin(user);
		if(userLogin!=null) {
			session.setAttribute("username", user.getUsername());
			session.setAttribute("usertype", userLogin.getType());
			session.setAttribute("userId", userLogin.getUserId());
			session.setAttribute("ownerId", userLogin.getOwnerId());
			return true;
		}
		return false;
	}
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
}
