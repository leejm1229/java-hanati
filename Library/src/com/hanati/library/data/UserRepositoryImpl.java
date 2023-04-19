package com.hanati.library.data;

import java.util.List;
import java.util.Scanner;

import com.hanati.library.domain.model.User;
import com.hanati.library.domain.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {
	User user = new User();

	@Override
	public List<User> findAll(User user) {
		return user.getUserList();
	}

	@Override
	public void addUser(User user) {
		UserCsvFileData.addUser(user);
	}

	@Override
	public void updateUser(User user, int id) {
		UserCsvFileData.updateUser(user, id);

	}

	@Override
	public void deleteUser(User user, int id) {
		UserCsvFileData.deleteUser(user, id);
	}

	@Override
	public void rollbackUser(User user) {
		UserCsvFileData.rollbackUser(user);
		
	}

}
