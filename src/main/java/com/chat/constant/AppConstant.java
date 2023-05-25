package com.chat.constant;

/**
 * 
 * @author manish
 *
 */
public class AppConstant {
	public static final String API_BASE_URL = "/api/v1";
	public static final String BASE_PACKAGE = "com.chat";
	public static final String APP_NAME = "Chat";

	// Property file config
	public static final String FILE_UPLOAD_DIR = "file.upload-dir";
	public static final String CONFIRM_EMAIL_BODY = "confirm.email.body";
	public static final String NEW_USER_EMAIL_BODY = "new.user.email.body";
	public static final String SUPPORT_EMAIL = "support.email";

	// User roles
	public static final Integer SUPER_ADMIN_ROLE = 0;
	public static final Integer ADMIN_ROLE = 1;
	public static final Integer USER_ROLE = 2;

	// Date formates
	public static final String CUSTOM_DATE_AND_TIME_DESERIALIZE_DATE_FORMAT = "MMM d, yyyy, HH:mm";
	public static final String CUSTOM_DATE_AND_TIME_SERIALIZE_DATE_FORMAT = "MMM d, yyyy, HH:mm:ss";

	// Swagger
	public static final String SWAGGER_API_INFO = APP_NAME + " Api Document";
	public static final String VERSION = "1.0";
	public static final String WEBSITE_URL = "https://domain.com/";
	public static final String CONTACT_EMAIL = "info@domain.com";

	// Authorization
	public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5 * 60 * 60 * 1000;
	public static final String JWT_TOKEN_ISSUER = "http://snapapp.com";
	public static final String SIGNING_KEY = "snap-app";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "authorization";
	public static final String INVALID_USERNAME = "Invalid username";
	public static final String LOGIN_SUCESSFULL = "Login Sucessfull";
	public static final String TOKEN = "token";
	public static final String USER = "user";
	public static final String LOGINEDUSER = "user_details";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";

	// Extensions
	public static final String JPG = "jpg";
	public static final String PNG = "png";

	// Otp
	public static final long OTO_VALIDITY_MINUTES = 30 * 60000; // 30 minutes

	// Web security
	public static final String[] PUBLIC_API_ARRAY = new String[] { "/v2/api-docs",
			"/swagger-resources/configuration/ui", "/swagger-resources", "/swagger-resources/configuration/security",
			"/swagger-ui.html", "/webjars/**", API_BASE_URL + "/auth/login", API_BASE_URL + "/auth/login/verify",
			API_BASE_URL + "/file/image/**", API_BASE_URL + "/user/register", API_BASE_URL + "/user/password/forgot",
			API_BASE_URL + "/otp/send", "/ws/**" };

}
