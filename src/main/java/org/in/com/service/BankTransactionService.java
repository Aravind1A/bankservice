package org.in.com.service;

import java.util.List;

import org.in.com.dto.AuthDto;
import org.in.com.dto.BankTransactionDto;

public interface BankTransactionService {


	public BankTransactionDto updateTransaction(AuthDto authDto,BankTransactionDto banktransactionDto) throws Exception;	
	 public BankTransactionDto get(AuthDto authDto,String code) throws Exception;
	// public  List<BankTransactionDto> getAll(AuthDto authDto)throws Exception;
	public List<BankTransactionDto> getDate(AuthDto authDto,String s, String t) throws Exception;
	

}