package org.in.com.dto;



import hirondelle.date4j.DateTime;
import lombok.Data;
@Data
public class CustomerDto {
	private int id;
	private String code;
	private String customerNumber;
	private String accountNo;
	private String branch;
	private String ifscCode;
	private String firstName;
	private String lastName;
	private long mobileNumber;
	private String emailId;
	private String address;
	private int activeFlag;
	private UserDto updatedBy;
	private DateTime updatedAt;
		
	}


	

