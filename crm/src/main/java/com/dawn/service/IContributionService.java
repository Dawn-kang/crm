package com.dawn.service;

import java.util.List;

import com.dawn.bean.extend.Contribution;

public interface IContributionService {
	public List<Contribution> findContribution();
	
	public List<Contribution> findContributionByRegion(String region);
}
