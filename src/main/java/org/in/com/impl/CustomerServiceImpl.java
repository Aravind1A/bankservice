package org.in.com.impl;

import java.util.List;

import org.in.com.dao.CustomerDao;
import org.in.com.dto.AuthDto;
import org.in.com.dto.CustomerDto;
import org.in.com.redisCache.CustomerRedis;
import org.in.com.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private CustomerRedis customerRedis;

	@Override
	public CustomerDto updateCustomer(AuthDto authDto,CustomerDto customerDto) throws Exception {

		return	customerDao.updatecustomer(authDto,customerDto);
	}

	@Override
	public List<CustomerDto> getAll(AuthDto authDto) throws Exception {
		
		return customerDao.getAll(authDto);
	}

	@Override
	public CustomerDto get(AuthDto authDto,String code) throws Exception {
		CustomerDto customerdto = customerRedis.getCustomerData(authDto,code);
		if (customerdto == null)
			try {
				customerdto = customerDao.get(authDto,code);
			} catch (Exception e) {
				e.printStackTrace();
			}
		customerRedis.putCustomerData(code, customerdto);
		System.out.println("data stored in redis cache");

		return customerdto;
	}

	@Override
	public CustomerDto getCustomer(AuthDto authDto, CustomerDto customer) throws Exception {
		
		return customerDao.getCustomer(authDto, customer);
	}
	

}
