package org.in.com.service;

import org.in.com.dto.AuthDto;



public interface AuthService {
	




public AuthDto updateToken(AuthDto authDto,String namespacecode, String username, String password) throws Exception;

public AuthDto authUpdate( AuthDto authDto,String token) throws Exception;




	
}
