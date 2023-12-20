package org.in.com.dto;



import hirondelle.date4j.DateTime;
import lombok.Data;
@Data
public class NamespaceDto {
	
private int id;
private String code;
private String name;
private String address;
private int activeFlag;
private UserDto updatedBy;
private DateTime updatedAt;

}



