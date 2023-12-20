package org.in.com.dto;



import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class UserDto {
	private int id;
	private String code;
	private NamespaceDto namespaceDto;
	private String username;
	private String password;
	private  String authToken;
	private  String firstName;
	private String lastName;
	private long mobileNumber;
	private String emailId;
	private int activeFlag;
	
	
}

