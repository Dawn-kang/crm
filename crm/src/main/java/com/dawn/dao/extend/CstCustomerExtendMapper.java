package com.dawn.dao.extend;

import java.util.List;

public interface CstCustomerExtendMapper {
	public List<String> selectRegion();
	public List<String> selectLevel();
	public List<Integer> selectCredit();
	public List<Integer> selectSatisfy();
}
