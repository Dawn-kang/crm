package com.dawn.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawn.bean.CstCustomer;
import com.dawn.bean.CstCustomerExample;
import com.dawn.bean.extend.Contribution;
import com.dawn.dao.CstCustomerMapper;
import com.dawn.dao.extend.CstCustomerExtendMapper;
import com.dawn.service.IConstituteService;

@Service
public class ConstituteServiceImpl implements IConstituteService{
	@Autowired
	private CstCustomerExtendMapper custExtendMapper;
	
	@Autowired
	private CstCustomerMapper customerMapper;

	@Override
	public List<Contribution> findConstitute(int condition) {
		//存储contribution对象
		List<Contribution> conlist = new ArrayList<>();
		//获取用户总数
		CstCustomerExample customerExample = new CstCustomerExample();
		int count = customerMapper.selectByExample(customerExample).size();
		
		//按等级查询
		if(condition==0) {
			List<String> levlist = custExtendMapper.selectLevel();
			for (String level : levlist) {
				//查询每个等级对应customer总数
				customerExample.createCriteria().andCustLevelLabelEqualTo(level);
				int size = customerMapper.selectByExample(customerExample).size();
				//创建contribution对象并为属性赋值
				Contribution contribution = new Contribution();
				contribution.setName(level);
				float y = (float)size/count;
				contribution.setY(y);
				//将contribution对象存储到list集合中
				conlist.add(contribution);
			}
		}
		//按信用度查询
		if(condition==1) {
			//获取不同的信用度
			List<Integer> creditlist = custExtendMapper.selectCredit();
			for(Integer credit:creditlist) {
				//根据不同信誉度查询customer
				CstCustomerExample example = new CstCustomerExample();
				example.createCriteria().andCustCreditEqualTo(credit);
				List<CstCustomer> custlist = customerMapper.selectByExample(example);
				//获取每个信誉度的人数
				int size = custlist.size();
				float y = (float)size/count;
				Contribution con = new Contribution();
				con.setName(credit+"");
				con.setY(y);
				conlist.add(con);
			}
		}
		//按满意度查询
		if(condition==2) {
			//获取所有的满意度
			List<Integer> satisfylist = custExtendMapper.selectSatisfy();
			for(Integer satisfy:satisfylist) {
				//按照满意度查询Customer
				CstCustomerExample example = new CstCustomerExample();
				example.createCriteria().andCustSatisfyEqualTo(satisfy);
				List<CstCustomer> custlist = customerMapper.selectByExample(example);
				int size = custlist.size();
				float y = (float)size/count;
				Contribution con = new Contribution();
				con.setName(satisfy+"");
				con.setY(y);
				conlist.add(con);
			}
		}
		return conlist;
	}

}
