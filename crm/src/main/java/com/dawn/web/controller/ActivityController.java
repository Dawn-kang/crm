package com.dawn.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dawn.bean.CstActivity;
import com.dawn.service.IActivityService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/activity")
public class ActivityController {
	@Autowired
	private IActivityService activityService;
	
	@RequestMapping("/pageQuery/{page}/{custId}")
	public String pageQuery(@PathVariable("custId") long id,
			@PathVariable("page") int curPage,Model model,HttpSession session) {
		PageInfo<CstActivity> pageInfo = 
				activityService.pageQuery(id, curPage, 5);
		model.addAttribute("activityInfo", pageInfo);
		session.setAttribute("custId", id);
		return "/customer/activities";
	}
	
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public String saveOrUpdate(CstActivity activity,HttpSession session) {
		Long id = (Long) session.getAttribute("custId");
		activity.setAtvCustId(id);
		activityService.saveOrUpdate(activity);
		return "保存成功";
	}
	
	@RequestMapping("/deleteById/{id}")
	@ResponseBody
	public String deleteById(@PathVariable long id) {
		String msg = "";
		try {
			activityService.deleteById(id);
			msg = "删除成功";
		} catch (Exception e) {
			msg = e.getMessage();
		}
		return msg;
	}
	
	@RequestMapping("/findById/{id}")
	@ResponseBody
	public CstActivity findById(@PathVariable long id) {
		 CstActivity activity = activityService.findById(id);
		return activity;
	}
}
