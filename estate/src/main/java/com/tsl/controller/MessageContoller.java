package com.tsl.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
import com.tsl.pojo.Cost;
import com.tsl.pojo.Message;
import com.tsl.service.AllService;
import com.tsl.service.MessageService;
import com.tsl.service.OwnerService;
import com.tsl.utils.State;
import com.tsl.utils.TimeUtil;

@Controller
@RequestMapping("/message")
public class MessageContoller {

	@Autowired
	private MessageService messageService;
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
		List<All> list = allService.selMessageAndOwner(search);
		if(pageNum>=startPage.getPages()) {
			pageNum=startPage.getPages();
		}
		request.setAttribute("search", search);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageSize", startPage.getPages());
		request.setAttribute("list", list);
		return "message_list";
	}
	
	@RequestMapping("/normal_list")
	public String normal_list(String search,Integer pageNum,HttpSession session,HttpServletRequest request) {
		if (pageNum == null||pageNum<=0) {
			pageNum = 1;
		}
		if (search == null||search.equals("null")||search.equals("")) {
			search = null;
		}
		int id = (int) session.getAttribute("ownerId");
		// 开始分页
		Page<Object> startPage = PageHelper.startPage(pageNum, 10);
		if(pageNum>=startPage.getPages()) {
			pageNum=startPage.getPages();
		}
		
		List<Message> list = messageService.selectAllByOwnerId(id);
		
		
		request.setAttribute("search", search);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageSize", startPage.getPages());
		request.setAttribute("list", list);
		return "message_list";
	}
	
	@RequestMapping("/upd")
	@ResponseBody
	public State upd(int message_id, HttpServletRequest request) {
		State state = new State();
		Message message=new Message();
		message.setMessageId(message_id);
		Date time=TimeUtil.getNow();
		message.setTime(time);
		message.setState("已完成");
		int i = messageService.update(message);
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
	public String addinfo() {
		return "message_add";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public State add(String mname,String remarks,HttpSession session) {
		int id = (int) session.getAttribute("ownerId");
		State state = new State();
		Message message=new Message();
		message.setMname(mname);
		message.setState("未完成");
		message.setTime(TimeUtil.getNow());
		message.setOwnerId(id);
		int i = messageService.insert(message);
		if (i == 1) {
			state.setIsSuccess(true);
			state.setMessage("报修成功");
			return state;
		} else {
			state.setIsSuccess(false);
			state.setMessage("报修失败");
			return state;
		}
	}
}
