package com.dawn.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dawn.bean.CstLinkman;
import com.dawn.service.ILinkManService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/linkman")
public class LinkManController {
	@Autowired
	private ILinkManService linkManService;
	
	@RequestMapping("/pageQuery/{page}/{custId}")
	public String pageQuery(@PathVariable("page") int curPage,
			@PathVariable("custId") long id,Model model,HttpSession session) {
		PageInfo<CstLinkman> pageInfo = 
				linkManService.pageQuery(id, curPage, 5);
		model.addAttribute("linkmanInfo",pageInfo);
		session.setAttribute("custId", id);
		
		return "/customer/linkman";
	}
	
	@PostMapping("/saveOrUpdate")
	@ResponseBody
	public String saveOrUpdate(CstLinkman linkMan,HttpSession session) {
		Long custId = (Long) session.getAttribute("custId");
		linkMan.setLkmCustId(custId);
		linkManService.saveOrUpdate(linkMan);
		return "保存成功";
	}
	
	@RequestMapping("/deleteById/{id}")
	@ResponseBody
	public String deleteById(@PathVariable long id) {
		String msg = "";
		try {
			linkManService.deleteById(id);
			msg = "删除成功";
		} catch (Exception e) {
			msg = e.getMessage();
		}
		return msg;
	}
	
	@RequestMapping("/findById/{id}")
	@ResponseBody
	public CstLinkman findById(@PathVariable long id) {
		CstLinkman linkman = linkManService.findById(id);
		return linkman;
	}
}
