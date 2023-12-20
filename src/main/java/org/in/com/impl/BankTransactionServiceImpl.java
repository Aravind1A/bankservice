package org.in.com.impl;

import java.util.List;

import org.in.com.dao.BankTransactionDao;
import org.in.com.dto.AuthDto;
import org.in.com.dto.BankTransactionDto;
import org.in.com.dto.CustomerDto;
import org.in.com.service.BankTransactionService;
import org.in.com.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankTransactionServiceImpl implements BankTransactionService {
	@Autowired
	private BankTransactionDao bankTransaction;

	@Autowired
	CustomerService customerService;
	@Override
	public BankTransactionDto updateTransaction( AuthDto authDto,BankTransactionDto banktransactionDto) throws Exception {
		
		try {
			CustomerDto customer=new CustomerDto();
			String customers=banktransactionDto.getCustomerDto().getCode();
			customer.setCode(customers);
			customer=customerService.getCustomer(authDto,customer);
			banktransactionDto.setCustomerDto(customer);
			banktransactionDto=bankTransaction.updateTransaction(authDto,banktransactionDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
			return 	banktransactionDto;


	}


	@Override
	public BankTransactionDto get(AuthDto authDto,String code) throws Exception {
		
		
		return bankTransaction.get(authDto,code);
	}

	@Override
	public List<BankTransactionDto> getDate(AuthDto authDto,String from, String to) throws Exception {

		
		return bankTransaction.getdate(authDto,from, to);
	}

}
