package com.chat.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.chat.constant.Constants;
import com.chat.dto.ApiResponseDto.ApiResponseDtoBuilder;
import com.chat.dto.MessageDto;
import com.chat.mapper.CustomMapper;
import com.chat.model.Message;
import com.chat.model.User;
import com.chat.repository.MessageRepository;
import com.chat.repository.UserRepository;
import com.chat.service.IMessageService;
import com.chat.utility.Utility;

@Service
public class MessageServiceImpl implements IMessageService {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private CustomMapper customMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	SimpMessagingTemplate template;

	@Override
	public void addMessage(ApiResponseDtoBuilder builder, MessageDto dto) {
		User sessionUser = Utility.getSessionUser(userRepository);
		Message message = customMapper.messageDtoToMessage(dto);
		message.setCreatedAt(new Date());
		message.setSender(sessionUser.getUsername());
		messageRepository.save(message);
//		template.convertAndSend("/topic/message", message);
		template.convertAndSendToUser(message.getSender(),"/topic/message", message);
		builder.withMessage(Constants.MESSAGE_SENT_SUCCESS).withStatus(HttpStatus.OK);
	}

	@Override
	public void getMessages(String username, ApiResponseDtoBuilder builder) {
		User sessionUser = Utility.getSessionUser(userRepository);
		List<Message> listOfMessage = messageRepository.findBySenderAndReceiverOrReceiverAndSender(
				sessionUser.getUsername(), username, username, sessionUser.getUsername());
		builder.withData(listOfMessage).withStatus(HttpStatus.OK).withMessage("success");
	}
}
