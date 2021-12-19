package com.example.service;

import java.util.Optional;

import com.example.entity.User;

public interface IUserService {

	Integer saveUser(User user);
	Optional<User> findUserByEmail(String email);
}