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
import com.tsl.pojo.Cost;
import com.tsl.service.AllService;
import com.tsl.service.CostService;
import com.tsl.service.OwnerService;
import com.tsl.utils.State;
import com.tsl.utils.TimeUtil;

@Controller
@RequestMapping("/cost")
public class CostContoller {

	@Autowired
	private CostService costService;
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
		if(pageNum>startPage.getPages()) {
			pageNum=startPage.getPages();
		}
		List<All> list = allService.selCostAndOwner(search);
		
		request.setAttribute("search", search);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageSize", startPage.getPages());
		request.setAttribute("list", list);
		return "cost_list";
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
		List<Cost> list = costService.selectAllByOwnerId(id);
		if(pageNum>=startPage.getPages()) {
			pageNum=startPage.getPages();
		}
		request.setAttribute("search", search);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageSize", startPage.getPages());
		request.setAttribute("list", list);
		return "cost_list";
	}
	
	@RequestMapping("/upd")
	@ResponseBody
	public State upd(int cost_id) {
		State state = new State();
		Cost cost=new Cost();
		cost.setCostId(cost_id);
		cost.setState("已交");
		cost.setTime(TimeUtil.getNow());
		int i = costService.update(cost);
		if (i == 1) {
			state.setIsSuccess(true);
			state.setMessage("缴费成功");
			return state;
		} else {
			state.setIsSuccess(false);
			state.setMessage("缴费失败");
			return state;
		}
	}
	
	@RequestMapping("/addinfo")
	public String addinfo() {
		return "cost_add";
	}
	
	@RequestMapping("/add")
	public String add(String mname,String remarks) {
		System.out.println();
		return "cost_add";
	}
}
