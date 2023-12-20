package org.in.com.controller;

import java.util.ArrayList;
import java.util.List;

import org.in.com.dto.AuthDto;
import org.in.com.dto.CustomerDto;
import org.in.com.io.CustomerIo;
import org.in.com.service.AuthService;
import org.in.com.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	@Autowired
	AuthService authService;

	@RequestMapping(value = "/add/{authtoken}", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public CustomerIo updateCustomer(@RequestBody CustomerIo customerIo, @PathVariable String authtoken)throws Exception {
		AuthDto authDto = null;
		authDto = authService.authUpdate(authDto, authtoken);
			CustomerIo customer = new CustomerIo();
			CustomerDto customerDto = new CustomerDto();
			customerDto.setCode(customerIo.getCode());
			customerDto.setCustomerNumber(customerIo.getCustomerNumber());
			customerDto.setAccountNo(customerIo.getAccountNo());
			customerDto.setBranch(customerIo.getBranch());
			customerDto.setIfscCode(customerIo.getIfscCode());
			customerDto.setFirstName(customerIo.getFirstName());
			customerDto.setLastName(customerIo.getLastName());
			customerDto.setMobileNumber(customerIo.getMobileNumber());
			customerDto.setEmailId(customerIo.getEmailId());
			customerDto.setAddress(customerIo.getAddress());
			customerDto.setActiveFlag(customerIo.getActiveFlag());
			CustomerDto customerObj = customerService.updateCustomer(authDto, customerDto);
			customer.setActiveFlag(customerObj.getActiveFlag());
			customer.setCode(customerObj.getCode());
		
		return customerIo;
	}

	@RequestMapping(value = "/getall/{authtoken}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<CustomerIo> getAll(@PathVariable String authtoken) throws Exception {
		AuthDto authDto = null;
		authDto = authService.authUpdate(authDto, authtoken);
		List<CustomerIo> customers = new ArrayList<CustomerIo>();
			List<CustomerDto> customerDtoList = customerService.getAll(authDto);
			for (CustomerDto customerDto : customerDtoList) {
				CustomerIo customer = new CustomerIo();
				customer.setCode(customerDto.getCode());
				customer.setCustomerNumber(customerDto.getCustomerNumber());
				customer.setAccountNo(customerDto.getAccountNo());
				customer.setBranch(customerDto.getBranch());
				customer.setIfscCode(customerDto.getIfscCode());
				customer.setFirstName(customerDto.getFirstName());
				customer.setLastName(customerDto.getLastName());
				customer.setMobileNumber(customerDto.getMobileNumber());
				customer.setEmailId(customerDto.getEmailId());
				customer.setAddress(customerDto.getAddress());
				customer.setActiveFlag(customerDto.getActiveFlag());
				customers.add(customer);
			}

		return customers;
	}

	@RequestMapping(value = "/get/{code}/{authtoken}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public CustomerIo find(@PathVariable String code, @PathVariable String authtoken) throws Exception {
		AuthDto authDto = null;
		authDto = authService.authUpdate(authDto, authtoken);
		CustomerDto customerDto = new CustomerDto();
		CustomerIo customerIo = new CustomerIo();
			customerDto = customerService.get(authDto, code);
			customerIo.setCode(customerDto.getCode());
			customerIo.setCustomerNumber(customerDto.getCustomerNumber());
			customerIo.setAccountNo(customerDto.getAccountNo());
			customerIo.setBranch(customerDto.getBranch());
			customerIo.setIfscCode(customerDto.getIfscCode());
			customerIo.setFirstName(customerDto.getFirstName());
			customerIo.setLastName(customerDto.getLastName());
			customerIo.setMobileNumber(customerDto.getMobileNumber());
			customerIo.setEmailId(customerDto.getEmailId());
			customerIo.setActiveFlag(customerDto.getActiveFlag());
		
		return customerIo;

	}

}
