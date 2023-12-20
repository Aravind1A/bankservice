package org.in.com.dto;


import lombok.Data;

@Data
public class AuthDto {
	private String authtoken;
	private UserDto userDto;
	private NamespaceDto namespaceDto;

}
