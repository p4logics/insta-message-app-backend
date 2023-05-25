package com.chat.service;

import org.springframework.stereotype.Service;

import com.chat.dto.ApiResponseDto.ApiResponseDtoBuilder;
import com.chat.dto.UserRequestDto;
import com.chat.model.User;

/**
 * 
 * @author manish
 *
 */
@Service
public interface IUserService {

	void getUserById(Long id, ApiResponseDtoBuilder apiResponseDtoBuilder);

	User findById(Long id);

	User getSessionUser();

	void userRegister(UserRequestDto userRequestDto, ApiResponseDtoBuilder apiResponseDtoBuilder);

	void getUserList(ApiResponseDtoBuilder apiResponseDtoBuilder);

	User findByUsername(String username);



}
