package com.dawn.service;

import com.dawn.bean.CstActivity;
import com.github.pagehelper.PageInfo;

public interface IActivityService {
	public PageInfo<CstActivity> pageQuery(long id,int curPage,int size);
	
	public void saveOrUpdate(CstActivity activity);
	
	public void deleteById(long id) throws Exception;
	
	public CstActivity findById(long id);
}
