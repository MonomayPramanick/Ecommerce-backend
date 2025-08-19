package com.backend.serviceImpl;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.config.AppConstraints;
import com.backend.exception.ResourceNotFoundException;
import com.backend.model.Role;
import com.backend.model.User;
import com.backend.payload.UserDto;
import com.backend.repositories.RoleRepo;
import com.backend.repositories.UserRepo;
import com.backend.service.UserService;


import org.modelmapper.ModelMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepo roleRepo;
	
	@Override
	public UserDto createUser(UserDto userdto) {
		User user=this.modelMapper.map(userdto,User.class);
		User saved=this.userRepo.save(user);
		return this.modelMapper.map(saved, UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto userdto, Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User"," UserId ",userId));
		user.setEmail(userdto.getEmail());
		user.setPhone(userdto.getPhone());
		user.setUsername(userdto.getUsername());
		user.setPassword(userdto.getPassword());
		User saved=this.userRepo.save(user);
		return this.modelMapper.map(saved, UserDto.class);
	}

	@Override
	public UserDto getUserById(Integer userId) {
			User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User"," UserId ",userId));
			return this.modelMapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User>alluser=this.userRepo.findAll();
		List<UserDto>alluserdto=alluser.stream().map((user)->this.modelMapper.map(user, UserDto.class)).toList();
		return alluserdto;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User"," UserId ",userId));
		this.userRepo.delete(user);

	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto, User.class);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		Role role=this.roleRepo.findById(AppConstraints.NORMAL_USER).get();
		user.getRoles().add(role);
		User newuser=this.userRepo.save(user);
		return this.modelMapper.map(newuser, UserDto.class);
	
	}
	
	

}
