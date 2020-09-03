package com.dawn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawn.bean.SysUser;
import com.dawn.bean.SysUserExample;
import com.dawn.dao.SysUserMapper;
import com.dawn.service.IUserService;

@Service
public class UserServicImpl implements IUserService{
	@Autowired
	private SysUserMapper userMapper;

	@Override
	public SysUser login(String username, String password) throws Exception {
		//判断用户名是否正确,根据用户名查询数据库中是否存在用户信息
		SysUserExample userExample = new SysUserExample();
		userExample.createCriteria().andUsrNameEqualTo(username);
		List<SysUser> userlist = userMapper.selectByExample(userExample);
		if(userlist.size()>0) {
			SysUser user = userlist.get(0);
			//判断密码是否正确
			if((user.getUsrPassword()).equals(password)) {
				return user;
			}else {
				throw new Exception("用户名密码错误，请重新输入！");
			}
			
		}else {
			throw new Exception("用户名不存在，请重新输入！");
		}
	}

}
