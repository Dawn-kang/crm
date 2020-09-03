package com.dawn.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawn.bean.extend.Contribution;
import com.dawn.dao.extend.CstCustomerExtendMapper;
import com.dawn.service.IContributionService;
import com.dawn.service.ICustomerService;

@Service
public class ContributionServiceImpl implements IContributionService{
	@Autowired
	private CstCustomerExtendMapper custExtendMapper;
	
	@Autowired
	private ICustomerService customerService;

	@Override
	public List<Contribution> findContribution() {
		//用于存储contribution对象
		List<Contribution> conlist = new ArrayList<>();
		
		//获取每个区域对应的contribution对象
		List<String> list = custExtendMapper.selectRegion();
		for (String region : list) {
			float percent = customerService.getRegionPercent(region);
			Contribution contribution = new Contribution();
			contribution.setName(region);
			contribution.setY(percent);
			conlist.add(contribution);
		}
		return conlist;
	}

	@Override
	public List<Contribution> findContributionByRegion(String region) {
		//用于存储contribution对象
		List<Contribution> conlist = new ArrayList<>();
		
		Contribution contribution = new Contribution();
		contribution.setName(region);
		float y = customerService.getRegionPercent(region);
		contribution.setY(y);
		conlist.add(contribution);
		return conlist;
	}

}
