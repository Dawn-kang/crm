package com.dawn.service;

import com.dawn.bean.CstLinkman;
import com.github.pagehelper.PageInfo;

public interface ILinkManService {
	public PageInfo<CstLinkman> pageQuery(long id,int curPage,int size);
	
	public void saveOrUpdate(CstLinkman linkMan);
	
	public void deleteById(long id) throws Exception;
	
	public CstLinkman findById(long id);
}
