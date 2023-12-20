package org.in.com.dto;

import java.math.BigDecimal;

import org.in.com.dto.enumeration.TransactionTypeId;

import hirondelle.date4j.DateTime;
import lombok.Data;

@Data
public class BankTransactionDto {
	private int id;
	private TransactionTypeId transactionTypeId;
	private String code;
	private NamespaceDto namespaceDto;
	private CustomerDto customerDto;
    private BigDecimal creditAmount;
    private BigDecimal debitAmount;
    private long transactionAmount;
    private long balance;
    private int activeFlag;
    private UserDto updatedBy;
    private DateTime transactionDate;

   
	

}
