package org.in.com.controller;


//import java.text.Format;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.in.com.dto.AuthDto;
import org.in.com.dto.BankTransactionDto;
import org.in.com.dto.CustomerDto;
import org.in.com.io.BankTransactionIo;
import org.in.com.service.AuthService;
import org.in.com.service.BankTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hirondelle.date4j.DateTime;

@Controller
@RequestMapping("/bankTransaction")
public class BankTransactionController {
	@Autowired
	BankTransactionService bankTranctionService;
	
	@Autowired
	AuthService authService;

	@RequestMapping(value = "/add/{authtoken}", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public BankTransactionIo updateTransaction(@RequestBody BankTransactionIo bankTransaction,@PathVariable String authtoken) throws Exception {
		
		AuthDto authDto = null;
		authDto=authService.authUpdate(authDto, authtoken);
		BankTransactionIo bankTransactionIo = new BankTransactionIo();
		BankTransactionDto bankTransactionDto = new BankTransactionDto();
		bankTransactionDto.setCode(bankTransaction.getCode());
		String customers=bankTransaction.getCustomer().getCode();
		CustomerDto customer=new CustomerDto();
		customer.setCode(customers);
		bankTransactionDto.setCustomerDto(customer);
		bankTransactionDto.setTransactionTypeId(bankTransaction.getTransactionTypeId());
		bankTransactionDto.setCreditAmount(bankTransaction.getCreditAmount());
		bankTransactionDto.setDebitAmount(bankTransaction.getDebitAmount());
		bankTransactionDto.setTransactionAmount(bankTransaction.getTransactionAmount());
		bankTransactionDto.setBalance(bankTransaction.getBalance());
		bankTransactionDto.setActiveFlag(bankTransaction.getActiveFlag());
		String transactionDate=bankTransaction.getTransactionDate();
		DateTime date= new DateTime(transactionDate);
		bankTransactionDto.setTransactionDate(date);
		bankTranctionService.updateTransaction(authDto,bankTransactionDto);
		bankTransactionIo.setCode(bankTransactionDto.getCode());
		bankTransactionIo.setActiveFlag(bankTransactionDto.getActiveFlag());
		
		return bankTransaction;

	}

	@RequestMapping(value = "/{authtoken}/getDate/{from}/{to}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<BankTransactionIo> getdate(@PathVariable String authtoken ,@PathVariable String from, @PathVariable String to) throws Exception {
		List<BankTransactionIo> bankTransactionIo = new ArrayList<BankTransactionIo>();
		List<BankTransactionDto> bankTransactionDtoList = new ArrayList<BankTransactionDto>();
		AuthDto authDto=null;
		 authDto = authService.authUpdate(authDto, authtoken);
		bankTransactionDtoList = bankTranctionService.getDate(authDto, from, to);
			for (BankTransactionDto bankTransactionDto : bankTransactionDtoList) {
				BankTransactionIo bankTransaction = new BankTransactionIo();
				bankTransactionDto.setCode(bankTransactionDto.getCode());
				bankTransaction.setTransactionTypeId(bankTransactionDto.getTransactionTypeId());
				bankTransaction.setCreditAmount(bankTransactionDto.getCreditAmount());
				bankTransaction.setDebitAmount(bankTransactionDto.getDebitAmount());
				bankTransaction.setTransactionAmount(bankTransactionDto.getTransactionAmount());
				bankTransaction.setBalance(bankTransactionDto.getBalance());
				bankTransaction.setActiveFlag(bankTransactionDto.getActiveFlag());
				DateTime bankTransactionDate = bankTransactionDto.getTransactionDate();
				String transactionDate = bankTransactionDate.format("YYYY-MM-DD");
				bankTransaction.setTransactionDate(transactionDate);
				bankTransactionIo.add(bankTransaction);
			}

			
		return bankTransactionIo;
	}

	@RequestMapping(value = "/get/{code}/{authtoken}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public BankTransactionIo get(@PathVariable String code,@PathVariable String authtoken) throws Exception {
		BankTransactionDto bankTransactionDto = new BankTransactionDto();
		BankTransactionIo bankTransactionIo = new BankTransactionIo();
		AuthDto authDto = null;
		authDto = authService.authUpdate(authDto, authtoken);
		bankTransactionDto = bankTranctionService.get(authDto,code);
		bankTransactionIo.setCode(bankTransactionDto.getCode());
		bankTransactionIo.setTransactionTypeId(bankTransactionDto.getTransactionTypeId());
		bankTransactionIo.setCreditAmount(bankTransactionDto.getCreditAmount());
		bankTransactionIo.setDebitAmount(bankTransactionDto.getDebitAmount());
		bankTransactionIo.setTransactionAmount(bankTransactionDto.getTransactionAmount());
		bankTransactionIo.setBalance(bankTransactionDto.getBalance());
		bankTransactionIo.setActiveFlag(bankTransactionDto.getActiveFlag());
		bankTransactionIo.setActiveFlag(bankTransactionDto.getActiveFlag());
		DateTime bankTransactionDate = bankTransactionDto.getTransactionDate();
		String transactionDate = bankTransactionDate.format("YYYY-MM-DD");
		bankTransactionIo.setTransactionDate(transactionDate);
		return bankTransactionIo;

	}
	

}
