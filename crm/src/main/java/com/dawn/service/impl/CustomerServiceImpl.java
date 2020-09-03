package com.dawn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawn.bean.CstCustomer;
import com.dawn.bean.CstCustomerExample;
import com.dawn.bean.CstCustomerExample.Criteria;
import com.dawn.dao.CstCustomerMapper;
import com.dawn.service.ICustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CustomerServiceImpl implements ICustomerService{
	@Autowired
	private CstCustomerMapper customerMapper;

	@Override
	public PageInfo<CstCustomer> pageQuery(int curPage, int size,String username,String region,String level) {
		//设置当前页码和每页显示数据条数
		PageHelper.startPage(curPage, size);
		//查询所有的customer信息
		CstCustomerExample customerExample = new CstCustomerExample();
		Criteria criteria = customerExample.createCriteria();
		if(username!=null && !"".equals(username)) {
			criteria.andCustNameLike("%"+username+"%");
		}
		if(region!=null && !"".equals(region)) {
			criteria.andCustAddrEqualTo(region);
		}
		if(level!=null && !"".equals(level)) {
			criteria.andCustAddrEqualTo(level);
		}
		List<CstCustomer> custlist = customerMapper.selectByExample(customerExample);
		//将查询到的customer信息传递给分页对象
		 PageInfo<CstCustomer> pageInfo = new PageInfo<>(custlist);
		return pageInfo;
	}

	@Override
	public void deleteById(long id) throws Exception {
		//根据id查询数据库信息，如果有，证明id存在可以删除，如果没有则id不存在
		CstCustomer customer = customerMapper.selectByPrimaryKey(id);
		if(customer!=null) {
			customerMapper.deleteByPrimaryKey(id);
		}else {
			throw new Exception("该客户id不存在，请重新输入！");
		}
		
	}

	@Override
	public void saveOrUpdate(CstCustomer customer) {
		//根据客户Id查找数据库信息，判断是否已存在,存在则为更新，不存在则为新增
		if(customer.getCustId()==null) {
			customerMapper.insertSelective(customer);
		}else {
			customerMapper.updateByPrimaryKeySelective(customer);
		}
	}

	@Override
	public CstCustomer findById(long id) {
		CstCustomer customer = customerMapper.selectByPrimaryKey(id);
		return customer;
	}

	//获取总的消费额
	public float getTotal() {
		float total = 0;
		CstCustomerExample customerExample = new CstCustomerExample();
		List<CstCustomer> list = customerMapper.selectByExample(customerExample);
		for (CstCustomer customer : list) {
			total += customer.getCustTurnover();
		}
		return total;
	}
	
	//获取每个区域的消费额
	public float getRegionTotal(String region) {
		float regionTotal  = 0;
		CstCustomerExample customerExample = new CstCustomerExample();
		customerExample.createCriteria().andCustRegionEqualTo(region);
		List<CstCustomer> list = customerMapper.selectByExample(customerExample);
		for (CstCustomer customer : list) {
			regionTotal  += customer.getCustTurnover();
		}
		return regionTotal ;
	}
	
	//获取每个区域贡献百分比
	@Override
	public float getRegionPercent(String region) {
		float percent = getRegionTotal(region)/getTotal();
		return percent;
	}

}
