package com.hanati.library.domian.repository;

import java.util.List;

import com.hanati.library.domian.model.Member;

public interface MemberRepository {
	
	List<Member> loadMember();
	
	void save(List<Member> member);

	void createMember(Member member);
	
	void readMember();

	void updateMember(int id);

	void deleteMember(int id);
	
	void rollbackMember();

	
}

