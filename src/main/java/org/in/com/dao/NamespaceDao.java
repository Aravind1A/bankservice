package org.in.com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.in.com.dto.AuthDto;
import org.in.com.dto.NamespaceDto;
import org.springframework.stereotype.Repository;

@Repository
public class NamespaceDao {

	public NamespaceDto updateNamespace(AuthDto authDto,NamespaceDto namespaceDto) throws Exception {
		try {
			Connection connection = ConnectionDao.getConnection();
			int pindex = 0;
			CallableStatement callableStatement = connection.prepareCall("{CALL EZEE_SP_NAMESPACE_IUD(?, ?, ?, ?, ?)}");
			callableStatement.setString(++pindex, namespaceDto.getCode());
			callableStatement.setString(++pindex, namespaceDto.getName());
			callableStatement.setString(++pindex, namespaceDto.getAddress());
			callableStatement.setInt(++pindex, namespaceDto.getActiveFlag());
			callableStatement.setInt(++pindex, authDto.getUserDto().getId());
			callableStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return namespaceDto;
	}

	public List<NamespaceDto> getAll() throws Exception {
		Connection connection = ConnectionDao.getConnection();
		String sql = "SELECT code, name, address, active_flag FROM namespace";
		List<NamespaceDto> list = new ArrayList<>();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			NamespaceDto namespaceDto = new NamespaceDto();
			namespaceDto.setCode(resultSet.getString("code"));
			namespaceDto.setName(resultSet.getString("name"));
			namespaceDto.setAddress(resultSet.getString("address"));
			namespaceDto.setActiveFlag(resultSet.getInt("active_flag"));
			list.add(namespaceDto);
		}
		return list;
	}

	public NamespaceDto get(AuthDto authDto,String code) throws Exception {
		NamespaceDto namespaceDto = new NamespaceDto();
		try {
		Connection connection = ConnectionDao.getConnection();
		String sql = "SELECT id, code, name, address, active_flag FROM namespace WHERE code = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, code);
			ResultSet resultSet = preparedStatement.executeQuery();
			{
				if (resultSet.next()) {
					namespaceDto.setId(resultSet.getInt("id"));
					namespaceDto.setCode(resultSet.getString("code"));
					namespaceDto.setName(resultSet.getString("name"));
					namespaceDto.setAddress(resultSet.getString("address"));
					namespaceDto.setActiveFlag(resultSet.getInt("active_flag"));
					authDto.setNamespaceDto(namespaceDto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return namespaceDto;

	}

	
	
	public NamespaceDto getNamespace(AuthDto authDto,NamespaceDto namespaceDto) throws Exception {
		try {
	    Connection connection = ConnectionDao.getConnection();
	     String sql = "SELECT id,code, name, address, active_flag FROM namespace WHERE id = ?";
	      
	       PreparedStatement preparedStatement = connection.prepareStatement(sql);
	    	int id = namespaceDto.getId();
	        preparedStatement.setInt(1, id);
	        ResultSet resultSet = preparedStatement.executeQuery();	        
	        if (resultSet.next())
	        		 {
	        	namespaceDto.setId(resultSet.getInt("id"));
	            namespaceDto.setCode(resultSet.getString("code"));
	            namespaceDto.setName(resultSet.getString("name"));
	            namespaceDto.setAddress(resultSet.getString("address"));
	            namespaceDto.setActiveFlag(resultSet.getInt("active_flag"));
	            authDto.setNamespaceDto(namespaceDto);
	        } 
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }
	    
	    return namespaceDto;
	}

}
