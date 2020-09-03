package com.dawn.service;

import com.dawn.bean.CstCustomer;
import com.github.pagehelper.PageInfo;

public interface ICustomerService {
	public PageInfo<CstCustomer> pageQuery(int curPage,int size,String username,String region,String level);
	
	public void deleteById(long id) throws Exception;
	
	public void saveOrUpdate(CstCustomer customer);
	
	public CstCustomer findById(long id);
	
	public float getRegionPercent(String region);
}
