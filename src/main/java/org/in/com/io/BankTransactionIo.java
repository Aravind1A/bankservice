package org.in.com.io;

import java.math.BigDecimal;

import org.in.com.dto.enumeration.TransactionTypeId;


import lombok.Data;

@Data
public class BankTransactionIo {
	
	private String code;
	private CustomerIo customer;
    private TransactionTypeId transactionTypeId;
    private BigDecimal creditAmount;
    private BigDecimal debitAmount;
    private long transactionAmount;
    private long balance;
    private int activeFlag;
    private String transactionDate;
}
