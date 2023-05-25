package com.chat.service;

import org.springframework.stereotype.Service;

import com.chat.dto.ApiResponseDto.ApiResponseDtoBuilder;
import com.chat.dto.MessageDto;

@Service
public interface IMessageService {

	void addMessage(ApiResponseDtoBuilder builder, MessageDto dto);

	void getMessages(String username, ApiResponseDtoBuilder builder);

}
