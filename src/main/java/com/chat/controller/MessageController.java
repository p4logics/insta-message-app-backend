package com.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chat.constant.AppConstant;
import com.chat.dto.ApiResponseDto;
import com.chat.dto.ApiResponseDto.ApiResponseDtoBuilder;
import com.chat.dto.MessageDto;
import com.chat.service.IMessageService;

@CrossOrigin(origins = "*", maxAge = 360000000)
@RestController
@RequestMapping(AppConstant.API_BASE_URL)
public class MessageController {

	@Autowired 
	private IMessageService service;
	
	@PostMapping(value = "/message/add")
	public ApiResponseDto addMessage(@RequestBody MessageDto dto) {
		ApiResponseDtoBuilder builder =new ApiResponseDtoBuilder();
		service.addMessage(builder,dto);
		return builder.build();
	}
	
	@GetMapping(value = "/messages/get")
	public ApiResponseDto getMessage(@RequestParam(required = true) String username) {
		ApiResponseDtoBuilder builder=new ApiResponseDtoBuilder();
		service.getMessages(username,builder);
		return builder.build();
	}
}
