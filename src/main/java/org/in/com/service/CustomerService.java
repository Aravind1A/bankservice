package org.in.com.service;

import java.util.List;

import org.in.com.dto.AuthDto;
import org.in.com.dto.CustomerDto;

public interface CustomerService {
	
    public CustomerDto updateCustomer(AuthDto authDto,CustomerDto customerobj) throws Exception;
    public  List<CustomerDto> getAll(AuthDto authDto)throws Exception;
	 public CustomerDto get( AuthDto authDto,String code) throws Exception;
	 public CustomerDto getCustomer(AuthDto authDto,CustomerDto customer)throws Exception;
	
	
}