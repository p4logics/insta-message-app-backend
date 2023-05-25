package com.chat.controller;

import java.util.HashMap;
import java.util.Map;

import javax.naming.AuthenticationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.config.JwtTokenUtil;
import com.chat.constant.AppConstant;
import com.chat.constant.Constants;
import com.chat.dto.ApiResponseDto;
import com.chat.dto.ApiResponseDto.ApiResponseDtoBuilder;
import com.chat.dto.LoginResponseDto;
import com.chat.dto.LoginUserDto;
import com.chat.mapper.CustomMapper;
import com.chat.model.User;
import com.chat.service.IUserService;

/**
 * 
 * @author manish
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(AppConstant.API_BASE_URL + "/auth")
public class AuthenticationController {
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private IUserService userService;

	@Autowired
	private CustomMapper customMapper;

	@PostMapping("/login")
	public ApiResponseDto login(@RequestBody LoginUserDto loginUser) throws AuthenticationException {
		logger.info("Login attempt....");
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		User userDetails = userService.findByUsername(loginUser.getUsername());
		if (userDetails != null) {
			try {
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
				final UserDetails user = userDetailsService.loadUserByUsername(userDetails.getUsername());
				final String token = jwtTokenUtil.generateToken(user);
				LoginResponseDto loginResponseDto = customMapper.userToLoginResponseDto(userDetails);
				Map<String, Object> response = setTokenDetails(user, token, loginResponseDto);
				apiResponseDtoBuilder.withStatus(HttpStatus.OK).withMessage(AppConstant.LOGIN_SUCESSFULL)
						.withData(response);
				logger.info("Login success!! Username is " + userDetails.getUsername());
			} catch (Exception e) {
				apiResponseDtoBuilder.withStatus(HttpStatus.UNAUTHORIZED)
						.withMessage(Constants.INVALID_USERNAME_OR_PASSWORD);
				logger.error("Login fail!! " + Constants.INVALID_USERNAME_OR_PASSWORD + "!! Username is "
						+ loginUser.getUsername());
			}
		} else {
			apiResponseDtoBuilder.withStatus(HttpStatus.NOT_FOUND).withMessage(Constants.INVALID_USERNAME);
			logger.error("Login fail!! " + Constants.INVALID_USERNAME + "!! Username is " + loginUser.getUsername());
		}
		return apiResponseDtoBuilder.build();
	}

	private Map<String, Object> setTokenDetails(final UserDetails userDetails, final String token,
			final LoginResponseDto loginResponseDto) {
		Map<String, Object> response = new HashMap<>();
		response.put(AppConstant.TOKEN, token);
		response.put(AppConstant.LOGINEDUSER, loginResponseDto);
		return response;
	}
}
