package org.in.com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.in.com.dto.AuthDto;
import org.in.com.dto.CustomerDto;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {

	public CustomerDto updatecustomer(AuthDto authDto,CustomerDto customerDto) throws Exception {
		try {
			Connection connection = ConnectionDao.getConnection();
			int pindex = 0;
			CallableStatement callableStatement = connection.prepareCall("{CALL EZEE_SP_CUSTOMER_IUD( ?, ?, ?, ?, ?, ?,?,?,?,?, ?,?,?)}");
			callableStatement.setString(++pindex, customerDto.getCode());
			callableStatement.setInt(++pindex, authDto.getNamespaceDto().getId());
			callableStatement.setString(++pindex, customerDto.getCustomerNumber());
			callableStatement.setString(++pindex, customerDto.getAccountNo());
			callableStatement.setString(++pindex, customerDto.getBranch());
			callableStatement.setString(++pindex, customerDto.getIfscCode());
			callableStatement.setString(++pindex, customerDto.getFirstName());
			callableStatement.setString(++pindex, customerDto.getLastName());
			callableStatement.setLong(++pindex, customerDto.getMobileNumber());
			callableStatement.setString(++pindex, customerDto.getEmailId());
			callableStatement.setString(++pindex, customerDto.getAddress());
			callableStatement.setInt(++pindex, customerDto.getActiveFlag());
			callableStatement.setInt(++pindex, authDto.getUserDto().getId());
			callableStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerDto;
	}

	public CustomerDto get(AuthDto authDto,String code) throws Exception {
		CustomerDto customerDto = new CustomerDto();
		try {
		Connection connection = ConnectionDao.getConnection();
		String sql = "SELECT id,code, namespace_id, customer_number, account_no, branch, ifsc_code, first_name, last_name, mobile_number, email_id, active_flag FROM customer WHERE code = ? AND namespace_id = ?";

		    PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, code);
			preparedStatement.setInt(2, authDto.getNamespaceDto().getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			{
				if (resultSet.next()) {
					int customerId = resultSet.getInt("id");
					customerDto.setId(customerId);
					customerDto.setCode(resultSet.getString("code"));
					customerDto.setId(resultSet.getInt("namespace_id"));
					customerDto.setCustomerNumber(resultSet.getString("customer_number"));
					customerDto.setAccountNo(resultSet.getString("account_no"));
					customerDto.setBranch(resultSet.getString("branch"));
					customerDto.setIfscCode(resultSet.getString("ifsc_code"));
					customerDto.setFirstName(resultSet.getString("first_name"));
					customerDto.setLastName(resultSet.getString("last_name"));
					customerDto.setMobileNumber(resultSet.getLong("mobile_number"));
					customerDto.setEmailId(resultSet.getString("email_id"));
					customerDto.setActiveFlag(resultSet.getInt("active_flag"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerDto;

	}

	public List<CustomerDto> getAll(AuthDto authDto) throws Exception {
		Connection connection = ConnectionDao.getConnection();
		String sql = "SELECT code, customer_number, account_no, branch, ifsc_code, first_name, last_name, mobile_number, email_id, active_flag FROM customer";
		List<CustomerDto> customerList = new ArrayList<>();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			CustomerDto customerDto = new CustomerDto();
			customerDto.setCode(resultSet.getString("code"));
			customerDto.setCustomerNumber(resultSet.getString("customer_number"));
			customerDto.setAccountNo(resultSet.getString("account_no"));
			customerDto.setBranch(resultSet.getString("branch"));
			customerDto.setIfscCode(resultSet.getString("ifsc_code"));
			customerDto.setFirstName(resultSet.getString("first_name"));
			customerDto.setLastName(resultSet.getString("last_name"));
			customerDto.setMobileNumber(resultSet.getLong("mobile_number"));
			customerDto.setEmailId(resultSet.getString("email_id"));
			customerDto.setActiveFlag(resultSet.getInt("active_flag"));
			customerList.add(customerDto);
		}
		return customerList;
	}
	public CustomerDto getCustomer(AuthDto authDto,CustomerDto customer)throws Exception{
		CustomerDto customerDto = new CustomerDto();
		try {
		Connection connection = ConnectionDao.getConnection();
		String sql = "SELECT id, code, namespace_id, customer_number, account_no, branch, ifsc_code, first_name, last_name, mobile_number, email_id, active_flag FROM customer WHERE code = ? AND namespace_id = ?";

		    PreparedStatement preparedStatement = connection.prepareStatement(sql); 
			preparedStatement.setString(1, customer.getCode());
			preparedStatement.setInt(2, authDto.getNamespaceDto().getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			{
				if (resultSet.next()) {
					customerDto.setId(resultSet.getInt("id"));
					customerDto.setCode(resultSet.getString("code"));
					customerDto.setId(resultSet.getInt("namespace_id"));
					customerDto.setCustomerNumber(resultSet.getString("customer_number"));
					customerDto.setAccountNo(resultSet.getString("account_no"));
					customerDto.setBranch(resultSet.getString("branch"));
					customerDto.setIfscCode(resultSet.getString("ifsc_code"));
					customerDto.setFirstName(resultSet.getString("first_name"));
					customerDto.setLastName(resultSet.getString("last_name"));
					customerDto.setMobileNumber(resultSet.getLong("mobile_number"));
					customerDto.setEmailId(resultSet.getString("email_id"));
					customerDto.setActiveFlag(resultSet.getInt("active_flag"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerDto;
		
	}

}
