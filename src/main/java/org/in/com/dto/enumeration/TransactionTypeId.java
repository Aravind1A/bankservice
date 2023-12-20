package org.in.com.dto.enumeration;

public enum TransactionTypeId {

	
		 credit("optional"),
		 debit("optional");


	public final String optional;

	private TransactionTypeId(String optional)
	{
		this.optional=optional;
	}
	}

