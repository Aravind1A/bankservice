package org.in.com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.in.com.dto.AuthDto;
import org.in.com.dto.BankTransactionDto;
import org.in.com.dto.enumeration.TransactionTypeId;
import org.springframework.stereotype.Repository;

import hirondelle.date4j.DateTime;

@Repository
public class BankTransactionDao {

	public BankTransactionDto updateTransaction(AuthDto authDto,BankTransactionDto bankTransactionDto) throws Exception {
		try {
			Connection connection = ConnectionDao.getConnection();
			int pindex = 0;
			CallableStatement callableStatement = connection.prepareCall("{CALL EZEE_SP_TRANSACTION_IUD(?, ?, ?, ?, ?,  ?,?,?,?,?, ?)}");
			callableStatement.setString(++pindex, bankTransactionDto.getCode());
			callableStatement.setInt(++pindex,authDto.getNamespaceDto().getId() );
			callableStatement.setInt(++pindex,bankTransactionDto.getCustomerDto().getId());
			callableStatement.setString(++pindex, bankTransactionDto.getTransactionTypeId().name());
			callableStatement.setBigDecimal(++pindex, bankTransactionDto.getCreditAmount());
			callableStatement.setBigDecimal(++pindex, bankTransactionDto.getDebitAmount());
			callableStatement.setLong(++pindex, bankTransactionDto.getTransactionAmount());
			callableStatement.setLong(++pindex, bankTransactionDto.getBalance());
			callableStatement.setLong(++pindex, bankTransactionDto.getActiveFlag());
			callableStatement.setLong(++pindex, authDto.getUserDto().getId());		
			DateTime bankTransactionDate = bankTransactionDto.getTransactionDate();
			String transactionDate = bankTransactionDate.format("YYYY-MM-DD");
			callableStatement.setString(++pindex, transactionDate);
			callableStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return bankTransactionDto;
	}


	public BankTransactionDto get( AuthDto authDto,String code) throws Exception {
		BankTransactionDto bankTransactionDto = new BankTransactionDto();
		try {
		Connection connection = ConnectionDao.getConnection();
		String sql = "SELECT code,transaction_type_id, credit_amount, debit_amount, transaction_amount, balance, active_flag, updated_by, transaction_Date FROM bank_transaction WHERE code = ? AND namespace_id = ?";
         
		 PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, code);
			prepareStatement.setInt(2, authDto.getNamespaceDto().getId());
			
			ResultSet resultSet = prepareStatement.executeQuery();
			{
				if (resultSet.next()) {

					TransactionTypeId transaction = TransactionTypeId.valueOf(resultSet.getString("transaction_type_id"));
					bankTransactionDto.setTransactionTypeId(transaction);
					bankTransactionDto.setCode(resultSet.getString("code"));
					bankTransactionDto.setCreditAmount(resultSet.getBigDecimal("credit_amount"));
					bankTransactionDto.setDebitAmount(resultSet.getBigDecimal("debit_amount"));
					bankTransactionDto.setTransactionAmount(resultSet.getLong("transaction_amount"));
					bankTransactionDto.setBalance(resultSet.getLong("balance"));
					bankTransactionDto.setActiveFlag(resultSet.getInt("active_flag"));
					String transactionDate=resultSet.getString("transaction_Date");
					DateTime date=new DateTime(transactionDate);
					bankTransactionDto.setTransactionDate(date);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bankTransactionDto;

	}

	public List<BankTransactionDto> getdate(AuthDto authDto,String from, String to) throws Exception {

		Connection connection = ConnectionDao.getConnection();
		String sql = "SELECT code, transaction_type_id, credit_amount, debit_amount, transaction_amount, balance, active_flag ,transaction_Date  FROM bank_transaction   WHERE  transaction_Date   BETWEEN ? AND ?";
		List<BankTransactionDto> list = new ArrayList<>();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, from);
			preparedStatement.setString(2, to);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				BankTransactionDto bankTransactionDto = new BankTransactionDto();

				TransactionTypeId transaction = TransactionTypeId.valueOf(resultSet.getString("transaction_type_id"));
				bankTransactionDto.setTransactionTypeId(transaction);
				bankTransactionDto.setCode(resultSet.getString("code"));
				bankTransactionDto.setCreditAmount(resultSet.getBigDecimal("credit_amount"));
				bankTransactionDto.setDebitAmount(resultSet.getBigDecimal("debit_amount"));
				bankTransactionDto.setTransactionAmount(resultSet.getLong("transaction_amount"));
				bankTransactionDto.setBalance(resultSet.getLong("balance"));
				bankTransactionDto.setActiveFlag(resultSet.getInt("active_flag"));
				String transactionDate=resultSet.getString("transaction_Date");
				DateTime date = new DateTime(transactionDate);
				bankTransactionDto.setTransactionDate(date);
				list.add(bankTransactionDto);
			}
			return list;
		}
	}

