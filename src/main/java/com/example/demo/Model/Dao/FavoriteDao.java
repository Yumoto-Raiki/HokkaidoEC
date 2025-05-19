package com.example.demo.Model.Dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.Model.DTO.FavoriteDTO;
import com.example.demo.Model.intarface.IFavorite;

public class FavoriteDao extends DBConectDao implements IFavorite {

	public void addProductToFavorite(int productId, int userId) {
		connect();

		// SQL文：カートテーブルにレコード（行）を追加する
		String sql = "INSERT INTO hba2025_3.favorites(user_id,item_id) values(?,?);";

		// try-with-resources：使い終わったら自動的に閉じてくれる構文
		try (PreparedStatement ps = con.prepareStatement(sql)) {

			// SQL文の「?」に値を代入する
			ps.setInt(1, userId); // 1番目の? → ユーザーID
			ps.setInt(2, productId); // 2番目の? → 商品ID

			// SQLを実行する（データ追加）
			ps.executeUpdate();

		} catch (SQLException e) {
			// エラーが起きたときに、内容を表示する
			System.out.println(e);
		}

	}

	public void removeProductToFavorite(int facvoriteId) {
		connect(); // データベースに接続

		// SQL文：指定したIDのカートアイテムを削除
		String sql = "delete from hba2025_3.favorites where id=?;";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, facvoriteId); // 削除するカートのIDを指定
			ps.executeUpdate(); // 実行（削除）
		} catch (SQLException e) {
			System.out.println(e); // エラー表示
		}
	}

	public void clearFavorite(int userId) {
		connect(); // データベースに接続

		// SQL文：指定したユーザーのカートの中身をすべて削除
		String sql = "delete from hba2025_3.favorites where user_id=?;";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, userId); // ユーザーIDを指定
			ps.executeUpdate(); // 実行（削除）
		} catch (SQLException e) {
			System.out.println(e); // エラー表示
		}
	}

	public List<FavoriteDTO> getFavorite(int userId) {
		// カートの中身を格納するリスト（今は空のまま）
		List<FavoriteDTO> dtos = new ArrayList<>();

		// 本来ここでデータベースから情報を取り出してdtosに追加する処理を書く

		return dtos; // 現時点では空のリストを返すだけ
	}

	public static void main(String[] args) {
		FavoriteDao test = new FavoriteDao();
		test.clearFavorite(65);
	}
}
