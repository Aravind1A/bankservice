package org.in.com.service;

import java.util.List;

import org.in.com.dto.AuthDto;
import org.in.com.dto.UserDto;


public interface UserService {
	
	public UserDto updateUser(AuthDto authDto,UserDto userDto) throws Exception;
    public  List<UserDto> getAll(AuthDto authDto)throws Exception;
	 public UserDto get(AuthDto authDto,String code) throws Exception;
	 public UserDto getUser(AuthDto authDto,String username)throws Exception;
	public UserDto getToken(AuthDto authDto, String username, String password, String token) throws Exception;
	UserDto validToken(AuthDto authDto, String token) throws Exception;


	}
	


