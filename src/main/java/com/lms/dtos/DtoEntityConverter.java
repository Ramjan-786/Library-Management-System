package com.lms.dtos;

import org.springframework.stereotype.Component;

import com.lms.entities.User;

@Component
public class DtoEntityConverter {
	public UserDto toUserDto(User entity) {
		UserDto dto = new UserDto();
		dto.setId(entity.getId());
		dto.setFirst_name(entity.getFirst_name());
		dto.setLast_name(entity.getLast_name());
		dto.setEmail(entity.getEmail());
		return dto;
		
	}
	
	public User toUserEntity(UserDto dto) {
		User entity = new User();
		entity.setId(dto.getId());
		entity.setFirst_name(dto.getFirst_name());
		entity.setLast_name(dto.getLast_name());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		entity.setAddress(dto.getAddress());
		entity.setBranch(dto.getBranch());
		entity.setContact_no(dto.getContact_no());
		
		return entity;		
	}

}
