package com.example.demo.Model.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.Model.intarface.IAuthSystem;

/**
 * 認証処理
 * @author CS
 *
 */
public class AuthSystemDao extends DBConectDao implements IAuthSystem {

	@Override
	public int login(String userName, String pass) {

		int userId = 0;

		// DB接続を開始
		connect();

		// 商品を取得するSQL
		String sql = "SELECT id From hba2025_3.users WHERE name = ? AND password = ?";

		// try-with-resources で自動的にリソースをクローズ
		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, userName);
			ps.setString(2, pass);
			// SQLを実行し、結果をResultSetに格納
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				userId = rs.getInt("id");

			}

		} catch (SQLException e) {
			// SQL例外が発生したらエラー内容を出力
			System.err.println("ログイン処理でエラー" + e);
		}

		// DB切断
		close();

		return userId;

	}

}
