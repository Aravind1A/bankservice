package org.in.com.impl;

import java.util.List;

import org.in.com.dao.NamespaceDao;
import org.in.com.dto.AuthDto;
import org.in.com.dto.NamespaceDto;

import org.in.com.service.NamespaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NamespaceServiceImpl implements NamespaceService {

	@Autowired
	private NamespaceDao namespaceDao;

	public NamespaceDto updateNamespace(AuthDto authDto,NamespaceDto namespaceDto) throws Exception {

		return	namespaceDao.updateNamespace(authDto,namespaceDto);
		
	}

	@Override
	public List<NamespaceDto> getAll(AuthDto authDto) throws Exception {
		
		return namespaceDao.getAll();
	}

	@Override
	public NamespaceDto get(AuthDto authDto,String code) throws Exception {
		
		return namespaceDao.get(authDto ,code);
	}

	@Override
	public NamespaceDto getNamespace(AuthDto authDto,NamespaceDto namespaceDto) throws Exception {
		
		return namespaceDao.getNamespace(authDto, namespaceDto );
	}

}
