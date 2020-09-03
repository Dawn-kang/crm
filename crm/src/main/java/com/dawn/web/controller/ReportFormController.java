package com.dawn.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dawn.bean.extend.Contribution;
import com.dawn.service.IConstituteService;
import com.dawn.service.IContributionService;

@Controller
@RequestMapping("/reportForm")
public class ReportFormController {
	@Autowired
	private IContributionService contributionService;
	
	@Autowired
	private IConstituteService constituteService;
	
	@RequestMapping("/toContribution")
	public String toContribution() {
		return "/reportForm/customerContribution";
	}
	
	@RequestMapping("/getContribution")
	@ResponseBody
	public List<Contribution> getContribution() {
		List<Contribution> conlist = contributionService.findContribution();
		return conlist;
	}
	
	@RequestMapping("/getContributionByRegion")
	@ResponseBody
	public List<Contribution> getContributionByRegion(String region) {
		List<Contribution> conlist = contributionService.findContributionByRegion(region);
		return conlist;
	}
	
	@RequestMapping("/toConstitute")
	public String toConstitute() {
		return "/reportForm/customerConstitute";
	}
	
	@RequestMapping("/getConstitute")
	@ResponseBody
	public List<Contribution> getConstitute(int condition) {
		List<Contribution> conlist = constituteService.findConstitute(condition);
		return conlist;
	}
}
