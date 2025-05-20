package com.example.demo.Model.Dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.demo.Model.DTO.AccountDTO;
import com.example.demo.Model.intarface.IAccount;

public class AccountDao extends DBConectDao implements IAccount {

	/**
	 * アカウントを追加する
	 * @return
	 */
	public int addAAccount(AccountDTO accountDTO) {
		int id = 0;

		// データベースに接続する（親クラスのメソッド）
		connect();

		// SQL文：カートテーブルにレコード（行）を追加する
		String sql = "INSERT INTO hba2025_3.users(name,age,telephonenumber,address,mailaddress,password) values(?,?,?,?,?,?);";

		// try-with-resources：使い終わったら自動的に閉じてくれる構文
		try (PreparedStatement ps = con.prepareStatement(sql)) {

			// SQL文の「?」に値を代入する
			ps.setString(1, accountDTO.getName()); // 名前
			ps.setInt(2, accountDTO.getAge()); // 年齢
			ps.setString(3, accountDTO.getTelephoneNumber()); // 電話番号
			ps.setString(4, accountDTO.getAddress()); // 住所
			ps.setString(5, accountDTO.getMailaddress()); // メアド
			ps.setString(6, accountDTO.getPass()); // パスワード

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
	public void updateAAccount() {

	}

	/**
	 * アカウント情報を取得
	 * @return
	 */
	public void AccountShow() {

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
