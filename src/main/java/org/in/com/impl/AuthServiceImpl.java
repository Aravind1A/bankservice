package org.in.com.impl;

import org.in.com.dto.AuthDto;

import org.in.com.dto.NamespaceDto;
import org.in.com.dto.UserDto;
import org.in.com.randomString.RandomString;
import org.in.com.service.AuthService;
import org.in.com.service.NamespaceService;
import org.in.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AuthServiceImpl implements AuthService{
	@Autowired
	UserService user;
	
	@Autowired
	NamespaceService namespace;

	@Autowired
	private RandomString randomString;

		@Override
		public AuthDto updateToken(AuthDto authDto,String namespacecode,String username, String password) throws Exception {
			UserDto userDto = new UserDto();
			NamespaceDto namespaceDto=new NamespaceDto();
			namespaceDto.setCode(namespacecode);
			try {
				 namespaceDto=namespace.get(authDto,namespacecode);
				authDto.setNamespaceDto(namespaceDto);
				userDto = user.getUser(authDto,username);			
				if (username.equals(userDto.getUsername())&& password.equals(userDto.getPassword())) {
					String token = randomString.generateRandomString(15);
					userDto.setAuthToken(token);
					user.getToken(authDto,username,password,token);
				} else {
					throw new RuntimeException("Invalid username And password");
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			return authDto;
		}
	
	
		
		@Override
		public AuthDto authUpdate(AuthDto authDto,String token) throws Exception {
		    NamespaceDto namespaceDto = new NamespaceDto();
            try {
		    UserDto userDto = user.validToken(authDto,token);
		    if (userDto != null && userDto.getAuthToken() != null) {
		        authDto.setUserDto(userDto);
		        namespaceDto = namespace.getNamespace(authDto,userDto.getNamespaceDto());  
		        authDto.setNamespaceDto(namespaceDto);
		    } 
            }catch(Exception e)
            {
            	e.printStackTrace();
            }
		return authDto;	
	}






}




	

