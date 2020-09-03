package com.dawn.service;

import java.util.List;

import com.dawn.bean.extend.Contribution;

public interface IConstituteService {

	public List<Contribution> findConstitute(int condition);
}
