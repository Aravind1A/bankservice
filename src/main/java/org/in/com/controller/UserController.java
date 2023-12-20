package org.in.com.controller;

import java.util.ArrayList;
import java.util.List;

import org.in.com.dto.AuthDto;
import org.in.com.dto.UserDto;
import org.in.com.io.UserIo;
import org.in.com.service.AuthService;
import org.in.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	AuthService authService;

	@RequestMapping(value = "/{authtoken}/add", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public UserIo updateUser(@PathVariable String authtoken,@RequestBody UserIo user) throws Exception {
		AuthDto authDto=new AuthDto();
		authDto=authService.authUpdate(authDto,authtoken);
		UserIo userIO = new UserIo();
		UserDto userDto = new UserDto();
		userDto.setCode(user.getCode());
		userDto.setUsername(user.getUsername());
		userDto.setPassword(user.getPassword());
		userDto.setAuthToken(user.getAuthToken());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setMobileNumber(user.getMobileNumber());
		userDto.setEmailId(user.getEmailId());
		userDto.setActiveFlag(user.getActiveFlag());
		UserDto userObj = userService.updateUser(authDto,userDto);
		userIO.setActiveFlag(userObj.getActiveFlag());
		userIO.setCode(userObj.getCode());
		authDto.setUserDto(userDto);
		
		return user;
	}

	@RequestMapping(value = "/getall/{authtoken}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<UserIo> getAll(@PathVariable String authtoken) throws Exception {
		List<UserIo> userIo = new ArrayList<UserIo>();
		AuthDto authDto = new AuthDto();
		authDto=authService.authUpdate(authDto, authtoken);
			List<UserDto> userDtoList = userService.getAll(authDto);
            for (UserDto userDto : userDtoList) {
			UserIo user = new UserIo();
			user.setCode(userDto.getCode());
			user.setUsername(userDto.getUsername());
			user.setPassword(userDto.getPassword());
			user.setAuthToken(userDto.getAuthToken());
			user.setFirstName(userDto.getFirstName());
			user.setLastName(userDto.getLastName());
			user.setMobileNumber(userDto.getMobileNumber());
			user.setEmailId(userDto.getEmailId());
			user.setActiveFlag(userDto.getActiveFlag());
			userIo.add(user);
		}
		
		
		return userIo;

	}

	@RequestMapping(value = "/get/{code}/{authtoken}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public UserIo find(@PathVariable String code,@PathVariable String authtoken) throws Exception {
		UserDto userDto = new UserDto();
		UserIo userIo = new UserIo();
		AuthDto authDto = new AuthDto();
		authDto=authService.authUpdate(authDto, authtoken);
		userDto = userService.get(authDto,code);
		userIo.setCode(userDto.getCode());
		userIo.setUsername(userDto.getUsername());
		userIo.setPassword(userDto.getPassword());
		userIo.setAuthToken(userDto.getAuthToken());
		userIo.setFirstName(userDto.getFirstName());
		userIo.setLastName(userDto.getLastName());
		userIo.setMobileNumber(userDto.getMobileNumber());
		userIo.setEmailId(userDto.getEmailId());
		userIo.setActiveFlag(userDto.getActiveFlag());
		return userIo;
	
	}

}
