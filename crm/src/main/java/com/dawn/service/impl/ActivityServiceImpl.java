package com.dawn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawn.bean.CstActivity;
import com.dawn.bean.CstActivityExample;
import com.dawn.dao.CstActivityMapper;
import com.dawn.service.IActivityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ActivityServiceImpl implements IActivityService{
	@Autowired
	private CstActivityMapper activityMapper;
	
	@Override
	public PageInfo<CstActivity> pageQuery(long id, int curPage, int size) {
		//设置当前页码和每页显示数据数
				PageHelper.startPage(curPage, size);
				
				//根据客户id查询对应交往记录信息
				CstActivityExample activityExample = new CstActivityExample();
				activityExample.createCriteria().andAtvCustIdEqualTo(id);
				List<CstActivity> list = activityMapper.selectByExample(activityExample);
				
				//创建pageinfo对象，将查询结果传递过来
				PageInfo<CstActivity> pageInfo = new PageInfo<>(list);
				return pageInfo;
	}

	@Override
	public void saveOrUpdate(CstActivity activity) {
		if(activity.getAtvId()==null) {
			activityMapper.insertSelective(activity);
		}else {
			activityMapper.updateByPrimaryKeySelective(activity);
		}
		
	}

	@Override
	public void deleteById(long id) throws Exception {
		CstActivity activity = activityMapper.selectByPrimaryKey(id);
		if(activity!=null) {
			activityMapper.deleteByPrimaryKey(id);
		}else {
			throw new Exception("该id记录不存在,请重新输入!");
		}
		
	}

	@Override
	public CstActivity findById(long id) {
		CstActivity activity = activityMapper.selectByPrimaryKey(id);
		return activity;
	}

}
