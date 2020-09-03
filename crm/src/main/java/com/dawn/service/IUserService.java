package com.dawn.service;

import com.dawn.bean.SysUser;

public interface IUserService {
	public SysUser login(String username,String password) throws Exception; 
}
