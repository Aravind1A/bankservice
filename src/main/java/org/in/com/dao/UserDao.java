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
import org.in.com.dto.UserDto;
import org.springframework.stereotype.Repository;

import lombok.Cleanup;

@Repository

public class UserDao {

	public UserDto updateUser(AuthDto authDto, UserDto userDto) throws Exception {
		try {
			Connection connection = ConnectionDao.getConnection();
			int pindex = 0;
			CallableStatement callableStatement = connection
					.prepareCall("{CALL EZEE_SP_user_IUD(?, ?, ?, ?, ?,  ?,?,?,?,?, ?)}");
			callableStatement.setString(++pindex, userDto.getCode());
			callableStatement.setInt(++pindex, authDto.getNamespaceDto().getId());
			callableStatement.setString(++pindex, userDto.getUsername());
			callableStatement.setString(++pindex, userDto.getPassword());
			callableStatement.setString(++pindex, userDto.getAuthToken());
			callableStatement.setString(++pindex, userDto.getFirstName());
			callableStatement.setString(++pindex, userDto.getLastName());
			callableStatement.setLong(++pindex, userDto.getMobileNumber());
			callableStatement.setString(++pindex, userDto.getEmailId());
			callableStatement.setInt(++pindex, userDto.getActiveFlag());
			callableStatement.setInt(++pindex, authDto.getUserDto().getId());
			callableStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userDto;

	}

	public List<UserDto> getAll(AuthDto authDto) throws Exception {
		Connection connection = ConnectionDao.getConnection();
		String sql = "SELECT code, username, password, auth_token, first_name, last_name, mobile_number, email_id, active_flag, updated_by FROM user WHERE namespace_id = ?";
		List<UserDto> list = new ArrayList<>();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, authDto.getNamespaceDto().getId());
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			UserDto userDto = new UserDto();
			userDto.setCode(resultSet.getString("code"));
			userDto.setUsername(resultSet.getString("username"));
			userDto.setPassword(resultSet.getString("password"));
			userDto.setAuthToken(resultSet.getString("auth_token"));
			userDto.setFirstName(resultSet.getString("first_name"));
			userDto.setLastName(resultSet.getString("last_name"));
			userDto.setMobileNumber(resultSet.getLong("mobile_number"));
			userDto.setEmailId(resultSet.getString("email_id"));
			userDto.setActiveFlag(resultSet.getInt("active_flag"));
			list.add(userDto);
		}
		return list;
	}

	public UserDto get(AuthDto authDto, String code) throws Exception {
		UserDto userDto = new UserDto();
		try {
			Connection connection = ConnectionDao.getConnection();
			String sql = "SELECT code, username, password, auth_token, first_name, last_name, mobile_number, email_id, active_flag FROM user WHERE code = ? And namespace_id = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, code);
			preparedStatement.setInt(2, authDto.getNamespaceDto().getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				userDto.setCode(resultSet.getString("code"));
				userDto.setUsername(resultSet.getString("username"));
				userDto.setPassword(resultSet.getString("password"));
				userDto.setAuthToken(resultSet.getString("auth_token"));
				userDto.setFirstName(resultSet.getString("first_name"));
				userDto.setLastName(resultSet.getString("last_name"));
				userDto.setMobileNumber(resultSet.getLong("mobile_number"));
				userDto.setEmailId(resultSet.getString("email_id"));
				userDto.setActiveFlag(resultSet.getInt("active_flag"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userDto;
	}

	public UserDto getToken(AuthDto authDto, String username, String password, String token) throws Exception {
		UserDto userDto = new UserDto();

		try {

			Connection connection = ConnectionDao.getConnection();
			String sql = "UPDATE  user SET auth_token = ?  WHERE username = ? AND password = ? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, token);
			preparedStatement.setString(2, username);
			preparedStatement.setString(3, password);
			int values = preparedStatement.executeUpdate();

			if (values > 0)

			{
				userDto.setAuthToken(token);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userDto;
	}

	public UserDto getUser(AuthDto authDto, String username) throws Exception {
		UserDto userDto = new UserDto();
		try {
			Connection connection = ConnectionDao.getConnection();
			String sql = "SELECT username, namespace_id, password, first_name, last_name, mobile_number, email_id, active_flag FROM user WHERE username = ? AND namespace_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setInt(2, authDto.getNamespaceDto().getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			{
				if (resultSet.next()) {
					NamespaceDto namespace = new NamespaceDto();
					userDto.setUsername(resultSet.getString("username"));
					int id = resultSet.getInt("namespace_id");
					namespace.setId(id);
					userDto.setNamespaceDto(namespace);
					userDto.setPassword(resultSet.getString("password"));
					userDto.setFirstName(resultSet.getString("first_name"));
					userDto.setLastName(resultSet.getString("last_name"));
					userDto.setMobileNumber(resultSet.getLong("mobile_number"));
					userDto.setEmailId(resultSet.getString("email_id"));
					userDto.setActiveFlag(resultSet.getInt("active_flag"));
					authDto.setUserDto(userDto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userDto;

	}

	public UserDto validToken(AuthDto authDto, String token) throws Exception {
		UserDto userDto = new UserDto();
		try {
			@Cleanup
			Connection connection = ConnectionDao.getConnection();
			String sql = "SELECT id, code, namespace_id, username, password, auth_token, first_name, last_name, mobile_number, email_id, active_flag FROM user WHERE auth_token = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, token);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				NamespaceDto namespace = new NamespaceDto();
				userDto.setId(resultSet.getInt("id"));
				userDto.setCode(resultSet.getString("code"));
				int id = resultSet.getInt("namespace_id");
				namespace.setId(id);
				userDto.setNamespaceDto(namespace);
				userDto.setUsername(resultSet.getString("username"));
				userDto.setPassword(resultSet.getString("password"));
				userDto.setAuthToken(resultSet.getString("auth_token"));
				userDto.setFirstName(resultSet.getString("first_name"));
				userDto.setLastName(resultSet.getString("last_name"));
				userDto.setMobileNumber(resultSet.getLong("mobile_number"));
				userDto.setEmailId(resultSet.getString("email_id"));
				userDto.setActiveFlag(resultSet.getInt("active_flag"));
				authDto.setUserDto(userDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userDto;
	}

}
