package com.backend.service;

import java.util.List;

import com.backend.payload.UserDto;



public interface UserService {

	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);

	UserDto registerNewUser(UserDto userDto);
}
