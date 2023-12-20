package org.in.com.controller;

import org.in.com.dto.AuthDto;
import org.in.com.service.AuthService;
import org.in.com.service.NamespaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/auth")
@Controller
public class AuthController {

	@Autowired
	AuthService authService;

	@Autowired
	NamespaceService namespaceService;

	@RequestMapping(value = "/{namespacecode}/get/{username}/{password}", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public AuthDto updateToken(@PathVariable String namespacecode, @PathVariable String username,
			@PathVariable String password) throws Exception {
		AuthDto authDto = new AuthDto();
		authDto = authService.updateToken(authDto, namespacecode, username, password);
		return authDto;
	}

	@RequestMapping(value = "/find/{token}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public AuthDto authUpdate(@PathVariable String token) throws Exception {
		AuthDto authDto = new AuthDto();
		authDto = authService.authUpdate(authDto, token);
		return authDto;

	}

}
