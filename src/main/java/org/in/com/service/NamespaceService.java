package org.in.com.service;

import java.util.List;

import org.in.com.dto.AuthDto;
import org.in.com.dto.NamespaceDto;


public interface NamespaceService {

     public  List<NamespaceDto> getAll(AuthDto authDto)throws Exception;
	public NamespaceDto updateNamespace(AuthDto authDto,NamespaceDto namespaceDto) throws Exception;
	 public NamespaceDto get(AuthDto authDto, String code) throws Exception;
	public NamespaceDto getNamespace(AuthDto authDto, NamespaceDto namespaceDto) throws Exception;
	
}
