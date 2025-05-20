package com.example.demo.Model.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.Model.DTO.UserDTO;

public class UserDAO extends DBConectDao {
	public UserDTO getUserInfo(int userId) {
		UserDTO user = new UserDTO();
		connect();

		String sql = "SELECT id, name, age, telephonenumber, address, poin FROM users WHERE id = ?;";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();

			user.setName(rs.getString("name"));
			user.setAge(rs.getInt("age"));
			user.setPhoneNumber(rs.getInt("telephonenumber"));
			user.setAddress(rs.getString("address"));
			user.setMail(rs.getString("mailaddress"));
			user.setPoint(rs.getInt("point"));

		} catch (SQLException e) {
			// エラーが起きたときに、内容を表示する
			System.out.println(e);
		}
		return user;
	}
}
