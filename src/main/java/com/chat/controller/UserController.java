package com.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.constant.AppConstant;
import com.chat.dto.ApiResponseDto;
import com.chat.dto.ApiResponseDto.ApiResponseDtoBuilder;
import com.chat.dto.UserRequestDto;
import com.chat.service.IUserService;

/**
 * 
 * @author manish
 *
 */
@CrossOrigin(origins = "*", maxAge = 360000000)
@RestController
@RequestMapping(AppConstant.API_BASE_URL)
public class UserController {

	@Autowired
	private IUserService userService;

	@PostMapping(value = "/user/register", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponseDto userRegister(@RequestBody(required = true) UserRequestDto userRequestDto) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		userService.userRegister(userRequestDto, apiResponseDtoBuilder);
		return apiResponseDtoBuilder.build();
	}

	@GetMapping(value = "/users/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponseDto userList() {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		userService.getUserList(apiResponseDtoBuilder);
		return apiResponseDtoBuilder.build();
	}
}
