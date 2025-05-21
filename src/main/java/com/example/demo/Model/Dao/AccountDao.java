package com.example.demo.Model.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.Model.DTO.AccountAddDTO;
import com.example.demo.Model.DTO.AccountShowDTO;
import com.example.demo.Model.DTO.AccountUpdateDTO;
import com.example.demo.Model.intarface.IAccount;

public class AccountDao extends DBConectDao implements IAccount {

	/**
	 * アカウントを追加する
	 * @return
	 */
	public int addAAccount(AccountAddDTO accountAddDTO) {
		int id = 0;

		// データベースに接続する（親クラスのメソッド）
		connect();

		// SQL文：カートテーブルにレコード（行）を追加する
		String sql = "INSERT INTO hba2025_3.users(name,age,telephonenumber,address,mailaddress,password) values(?,?,?,?,?,?);";

		// try-with-resources：使い終わったら自動的に閉じてくれる構文
		try (PreparedStatement ps = con.prepareStatement(sql)) {

			// SQL文の「?」に値を代入する
			ps.setString(1, accountAddDTO.getName()); // 名前
			ps.setInt(2, accountAddDTO.getAge()); // 年齢
			ps.setString(3, accountAddDTO.getTelephoneNumber()); // 電話番号
			ps.setString(4, accountAddDTO.getAddress()); // 住所
			ps.setString(5, accountAddDTO.getMailaddress()); // メアド
			ps.setString(6, accountAddDTO.getPass()); // パスワード

			// SQLを実行する（データ追加）
			ps.executeUpdate();

		} catch (SQLException e) {
			// エラーが起きたときに、内容を表示する
			System.out.println(e);
		}

		return id;

	}

	/**
	 * アカウントを削除する
	 * @return
	 */
	public void removeAAccount(int userId) {

		connect(); // データベースに接続

		// SQL文：指定したIDのカートアイテムを削除
		String sql = "delete from hba2025_3.users where id=?;";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, userId); // 削除するカートのIDを指定
			ps.executeUpdate(); // 実行（削除）
		} catch (SQLException e) {
			System.out.println(e); // エラー表示
		}

	}

	/**
	 * アカウント情報を更新する
	 * @return
	 */
	public void updateAAccount(int userId, AccountUpdateDTO accountUpdateDTO) {
		//		connect();
		//		String sql = "update hba2025_3.user set name=? age=? address=?  where id=?";
		//		try (PreparedStatement ps = con.prepareStatement(sql)) {
		//			ps.setInt(1, accountDTO.);// 削除するカートのIDを指定
		//			ps.setInt(2, cartId);// 削除するカートのIDを指定
		//			ps.executeUpdate(); // 実行（削除）
		//		} catch (SQLException e) {
		//			System.out.println(e); // エラー表示
		//		}
		//
		//		// DB切断
		//		close();
	}

	/**
	 * アカウント情報を取得
	 * @return
	 */
	public void AccountShow() {

	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public AccountShowDTO getAccountInfo(int userId) {
		AccountShowDTO user = new AccountShowDTO();
		connect();

		String sql = "SELECT id, name, age, telephonenumber, address, poin FROM users WHERE id = ?;";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();

			user.setName(rs.getString("name"));
			user.setAge(rs.getInt("age"));
			user.setTelephoneNumber(rs.getString("telephonenumber"));
			user.setAddress(rs.getString("address"));
			user.setMailaddress(rs.getString("mailaddress"));
			user.setPoint(rs.getInt("point"));

		} catch (SQLException e) {
			// エラーが起きたときに、内容を表示する
			System.out.println(e);
		}
		return user;
	}

	public static void main(String[] args) {

		AccountDao accountDao = new AccountDao();
		//		AccountDTO dto = new AccountDTO();
		//		dto.setName("A");
		//		dto.setAge(56);
		//		dto.setTelephoneNumber("000-000-000");
		//		dto.setAddress("AA");
		//		dto.setMailaddress("ytuytu.gmail.com");
		//		dto.setPass("A1B2C3");
		//		accountDao.addAAccount(dto);
		accountDao.removeAAccount(16);

	}

}
