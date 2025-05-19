package com.example.demo.Model.Dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Model.intarface.IPoint;

public class PointDAO extends DBConectDao implements IPoint {

	public void addApoint(int userId, @RequestParam("") int addpoint) {
		connect();

		String sql = "update hba2025_3.users set point=point+? where id=?; ";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, addpoint);// 削除するカートのIDを指定
			ps.setInt(2, userId);// 削除するカートのIDを指定
			ps.executeUpdate(); // 実行（削除）
		} catch (SQLException e) {
			System.out.println(e); // エラー表示
		}

	}

	public void showAddpoint(int userId) {
		connect();

		String sql = "select point from users where id=?; ";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, userId);// 削除するカートのIDを指定
			ps.executeQuery(); // 実行（削除）
		} catch (SQLException e) {
			System.out.println(e); // エラー表示
		}

	}

	public static void main(String[] args) {
		PointDAO test = new PointDAO();
		test.showAddpoint(2);
	}
}
