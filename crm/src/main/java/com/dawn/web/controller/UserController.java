package com.dawn.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dawn.bean.SysUser;
import com.dawn.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;

	@RequestMapping("/login")
	public String login(@RequestParam("username") String username,String password,HttpSession session) {
		String url = "";
		try {
			SysUser user = userService.login(username, password);
			session.setAttribute("user", user);
			url = "index";
			
		} catch (Exception e) {
			session.setAttribute("msg", e.getMessage());
			url = "login";
		}
		return url;
	}
}
