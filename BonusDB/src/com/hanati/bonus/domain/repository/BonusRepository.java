package com.hanati.bonus.domain.repository;

import java.util.List;

import com.hanati.bonus.domain.model.Emp;

public interface BonusRepository {

	// EMP의 사람들의 보너스를 BONUS 테이블에 저장 
	public void saveBonus(List<Emp> empList);
}
