package com.hanati.bonus.domain.repository;

import java.util.List;

import com.hanati.bonus.domain.model.Emp;

public interface BonusRepository {

	public void saveBonus(List<Emp> empList);
}
