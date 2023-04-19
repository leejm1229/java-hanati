package com.hanati.library.domain.repository;

import java.util.List;

import com.hanati.library.domain.model.User;

public interface UserRepository {
	
	List<User> findAll(User user);

	void addUser(User user);

	void updateUser(User user, int id);

	void deleteUser(User user, int id);
	
	void rollbackUser(User user);

	

}
