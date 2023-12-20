package org.in.com.impl;

import java.util.List;
import org.in.com.dao.UserDao;
import org.in.com.dto.AuthDto;
import org.in.com.dto.UserDto;
import org.in.com.ehcache.EhcacheManager;
import org.in.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.ehcache.Element;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public UserDto updateUser( AuthDto authDto,UserDto userDto) throws Exception {
		
		return userDao.updateUser(authDto,userDto);

	}

	@Override
	public List<UserDto> getAll(AuthDto authDto) throws Exception {
		
		return userDao.getAll(authDto);
	}

	@Override
	public UserDto get(AuthDto authDto ,String code) throws Exception {
		Element element = EhcacheManager.getuserCache().get(code);
		UserDto userdto = null;
		if (element != null) {
			userdto = (UserDto) element.getObjectValue();
		} else {
			try {
				userdto = userDao.get(authDto,code);
			} catch (Exception e) {
				e.printStackTrace();
			}
			element = new Element(code, userdto);
			EhcacheManager.getuserCache().put(element);
		}
		System.out.println("user data from ehcache");
		return userdto;
	}

	@Override
	public UserDto getUser(AuthDto authDto,String username) throws Exception {
		
		return userDao.getUser(authDto,username);
	}

	@Override
	public UserDto getToken(AuthDto authDto ,String username, String password, String token) throws Exception {
		
		return userDao.getToken(authDto,username, password, token);

	}

	@Override
	public UserDto validToken( AuthDto authDto,String token) throws Exception {
		
		return userDao.validToken(authDto ,token);
	}

}




