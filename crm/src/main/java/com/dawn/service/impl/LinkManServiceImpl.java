package com.dawn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawn.bean.CstLinkman;
import com.dawn.bean.CstLinkmanExample;
import com.dawn.dao.CstLinkmanMapper;
import com.dawn.service.ILinkManService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class LinkManServiceImpl implements ILinkManService{
	@Autowired
	private CstLinkmanMapper linkManMapper;

	@Override
	public PageInfo<CstLinkman> pageQuery(long id, int curPage,int size) {
		//设置当前页码和每页显示数据数
		PageHelper.startPage(curPage, size);
		
		//根据客户id查询对应联系人信息
		CstLinkmanExample linkmanExample = new CstLinkmanExample();
		linkmanExample.createCriteria().andLkmCustIdEqualTo(id);
		List<CstLinkman> list = linkManMapper.selectByExample(linkmanExample);
		
		//创建pageinfo对象，将查询结果传递过来
		PageInfo<CstLinkman> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public void saveOrUpdate(CstLinkman linkMan) {
		//判断linkman的id是否为空，为空证明不存在则插入保存数据，不为空证明已存在进行数据更新
		if(linkMan.getLkmId()==null) {
			linkManMapper.insertSelective(linkMan);
		}else {
			linkManMapper.updateByPrimaryKeySelective(linkMan);
		}
	}
	

	@Override
	public void deleteById(long id) throws Exception {
		//根据id查找对应联系人信息
		CstLinkman linkman = linkManMapper.selectByPrimaryKey(id);
		//如果查找到联系人不为空，则删除对应联系人信息，如果为空证明联系人不存在
		if(linkman!=null) {
			linkManMapper.deleteByPrimaryKey(id);
		}else {
			throw new Exception("该联系人不存在，请重新输入！");
		}
	}

	@Override
	public CstLinkman findById(long id) {
		CstLinkman linkman = linkManMapper.selectByPrimaryKey(id);
		return linkman;
	}

}
