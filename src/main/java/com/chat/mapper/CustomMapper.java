package com.chat.mapper;

import org.mapstruct.Mapper;

import com.chat.dto.LoginResponseDto;
import com.chat.dto.MessageDto;
import com.chat.dto.UserDetailResponseDto;
import com.chat.dto.UserRequestDto;
import com.chat.model.Message;
import com.chat.model.User;

/**
 * 
 * @author manish
 *
 */
@Mapper(componentModel = "spring")
public interface CustomMapper {

	User userRequestDtoToUser(UserRequestDto userRequestDto);

	LoginResponseDto userToLoginResponseDto(User userDetails);

	Message messageDtoToMessage(MessageDto dto);

	UserDetailResponseDto userToUserDetailResponseDto(User user);


}