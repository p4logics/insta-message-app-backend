package com.chat.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.chat.constant.AppConstant;
import com.chat.constant.Constants;
import com.chat.dto.ApiResponseDto.ApiResponseDtoBuilder;
import com.chat.dto.UserDetailResponseDto;
import com.chat.dto.UserRequestDto;
import com.chat.mapper.CustomMapper;
import com.chat.model.User;
import com.chat.repository.UserRepository;
import com.chat.service.IUserService;
import com.chat.utility.Utility;

/**
 * 
 * @author manish
 *
 */
@Service("userService")
public class UserServiceImpl implements IUserService, UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CustomMapper customMapper;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(Constants.INVALID_USERNAME);
		}
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority(AppConstant.ROLE_ADMIN));
	}

	@Override
	public void getUserById(Long id, ApiResponseDtoBuilder apiResponseDtoBuilder) {
		User user = findById(id);
		if (user == null) {
			apiResponseDtoBuilder.withMessage(Constants.NO_USER_EXISTS).withStatus(HttpStatus.NOT_FOUND);
			logger.info("Get User By id!! " + Constants.NO_USER_EXISTS + "!! User id is " + id);
			return;
		}
		UserDetailResponseDto userDetailResponseDto = customMapper.userToUserDetailResponseDto(user);
		apiResponseDtoBuilder.withMessage(Constants.USER_INFO).withStatus(HttpStatus.OK)
				.withData(userDetailResponseDto);
		logger.info("Get User By id!! " + Constants.USER_INFO + "!! User id is " + id);
	}


	@Override
	public User getSessionUser() {
		return Utility.getSessionUser(userRepository);
	}

	@Override
	public User findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.isPresent() ? user.get() : null;
	}
	@Override
	public void userRegister(UserRequestDto userRequestDto, ApiResponseDtoBuilder apiResponseDtoBuilder) {
		if (userRepository.existsByUsername(userRequestDto.getUsername())) {
			apiResponseDtoBuilder.withMessage(Constants.ALREADY_REGISTERED).withStatus(HttpStatus.ALREADY_REPORTED);
			return;
		} else {
			User userRequestDtoToUser = customMapper.userRequestDtoToUser(userRequestDto);
			String newPasswordEncodedString = bcryptEncoder.encode(userRequestDto.getPassword());
			userRequestDtoToUser.setPassword(newPasswordEncodedString);
			userRepository.save(userRequestDtoToUser);
			apiResponseDtoBuilder.withMessage(Constants.USER_ADD_SUCCESSS).withStatus(HttpStatus.OK)
					.withData(userRequestDtoToUser);
		}
	}

	@Override
	public void getUserList(ApiResponseDtoBuilder apiResponseDtoBuilder) {
		User sessionUser = Utility.getSessionUser(userRepository);
		List<User> userList = userRepository.findByUsernameNot(sessionUser.getUsername());
		apiResponseDtoBuilder.withMessage("success").withStatus(HttpStatus.OK).withData(userList);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
