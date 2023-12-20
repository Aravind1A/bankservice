package org.in.com.controller;

import java.util.ArrayList;
import java.util.List;

import org.in.com.dto.AuthDto;
import org.in.com.dto.NamespaceDto;
import org.in.com.io.NamespaceIo;
import org.in.com.service.AuthService;
import org.in.com.service.NamespaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/namespace")
public class NamespaceController {

	@Autowired
	private NamespaceService namespaceService;

	@Autowired
	private AuthService authService;

	@RequestMapping(value = "/{authtoken}/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public NamespaceIo updateNamespace(@PathVariable String authtoken, @RequestBody NamespaceIo namespace)
			throws Exception {
		AuthDto authDto =new AuthDto();
		authDto = authService.authUpdate(authDto, authtoken);
			NamespaceIo namespaceIO = new NamespaceIo();
			NamespaceDto namespaceDto = new NamespaceDto();
			namespaceDto.setCode(namespace.getCode());
			namespaceDto.setName(namespace.getName());
			namespaceDto.setAddress(namespace.getAddress());
			namespaceDto.setActiveFlag(namespace.getActiveFlag());
			NamespaceDto namespaceDTO = namespaceService.updateNamespace(authDto, namespaceDto);
			namespaceIO.setActiveFlag(namespaceDTO.getActiveFlag());
			namespaceIO.setCode(namespaceDTO.getCode());
			authDto.setNamespaceDto(namespaceDTO);
	
		return namespace;
	}

	@RequestMapping(value = "/{authtoken}/getAll", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<NamespaceIo> getAll(@PathVariable("authtoken") String authtoken) throws Exception {
		AuthDto authDto = new AuthDto();
		authDto = authService.authUpdate(authDto, authtoken);
		List<NamespaceIo> namespacesIO = new ArrayList<NamespaceIo>();
		List<NamespaceDto> namespacesDtoList = namespaceService.getAll(authDto);
		for (NamespaceDto namespaceDto : namespacesDtoList) {
			NamespaceIo namespace = new NamespaceIo();
			namespace.setCode(namespaceDto.getCode());
			namespace.setName(namespaceDto.getName());
			namespace.setAddress(namespaceDto.getAddress());
			namespace.setActiveFlag(namespaceDto.getActiveFlag());
			namespacesIO.add(namespace);

		}

		return namespacesIO;
	}

	@RequestMapping(value = "/{authtoken}/get/{code}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public NamespaceIo find(@PathVariable String authtoken, @PathVariable String code) throws Exception {
		AuthDto authDto = new AuthDto();
		authDto = authService.authUpdate(authDto, authtoken);
		NamespaceDto namespaceDto = new NamespaceDto();
		NamespaceIo namespaceIo = new NamespaceIo();
			namespaceDto = namespaceService.get(authDto, code);
			namespaceIo.setCode(namespaceDto.getCode());
			namespaceIo.setName(namespaceDto.getName());
			namespaceIo.setAddress(namespaceDto.getAddress());
			namespaceIo.setActiveFlag(namespaceDto.getActiveFlag());
		
		return namespaceIo;
	}

}
