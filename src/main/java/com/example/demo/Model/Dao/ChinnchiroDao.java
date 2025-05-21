package com.example.demo.Model.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import com.example.demo.Model.DTO.ChinnchiroDTO;

public class ChinnchiroDao extends DBConectDao {
	public ChinnchiroDTO ChinchiroGame(int userId) {

		Random random = new Random();

		ChinnchiroDTO chinnchiro = new ChinnchiroDTO();
		//サイコロ3つの出目を決める
		int[] dice = new int[3];
		for (int i = 0; i < 3; i++) {
			dice[i] = random.nextInt(6) + 1;
		}
		dice[0] = 1;
		dice[1] = 2;
		dice[2] = 3;

		System.out.println("出た目: [" + dice[0] + ", " + dice[1] + ", " + dice[2] + "]");
		/*String result = judgeDice(dice);
		System.out.println("結果: " + result);*/

		double discount = 1;
		String messe = "役なし";

		// ソートして比較しやすくする
		java.util.Arrays.sort(dice);

		// ピンゾロ（1,1,1）
		if (dice[0] == 1 && dice[1] == 1 && dice[2] == 1) {
			discount = 0.5;
			messe = "ピンゾロ！(最強)";
		}

		// ゾロ目
		else if (dice[0] == dice[1] && dice[1] == dice[2]) {
			discount = 0.7;
			messe = "ゾロ目！(" + dice[0] + "のゾロ)";
		}

		// 目が全部異なる && 1つが真ん中
		else if (dice[0] == 1 && dice[1] == 2 && dice[2] == 3) {
			discount = 2;
			messe = "ヒフミ！";
		}

		//
		else if (dice[0] == 4 && dice[1] == 5 && dice[2] == 6) {
			discount = 0.8;
			messe = "シゴロ";
		}

		// 役なしの出目（例えば 1,3,5など）
		/*if (dice[0] != dice[1] && dice[1] != dice[2] && dice[0] != dice[2]) {
		}*/

		// 1つだけ違う場合：2つが同じ → 目を返す
		/*if (dice[0] == dice[1]) {
			return dice[2] + "の目";
		} else if (dice[1] == dice[2]) {
			return dice[0] + "の目";
		} else {
			return "判定不能";
		}*/

		chinnchiro.setDice(dice);
		chinnchiro.setDiscount(discount);
		chinnchiro.setMessege(messe);
		chinnchiro.setSum(settlement_change(userId));

		return chinnchiro;
	}

	//出目から役を判断する

	public int settlement_change(int userId) {
		int sum = 0;

		// DB接続を開始
		connect();

		String sql = "SELECT SUM(items.price * carts.item_count) FROM carts JOIN items ON carts.item_id = items.item_id WHERE user_id = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, userId);
			// SQLを実行し、結果をResultSetに格納
			ResultSet rs = ps.executeQuery();

			// カーソルを1レコード目に移動
			rs.next();

			// 金額の合計の結果を取得
			sum = rs.getInt("SUM(items.price * carts.item_count)");

		} catch (SQLException e) {
			// SQL例外が発生したらエラー内容を出力
			System.err.println(e);
		}

		// DB切断
		close();
		return sum;
	}

	public static void main(String[] args) {
		ChinnchiroDao chinnchiro = new ChinnchiroDao();
		//		System.out.println(chinchiro.ChinchiroGame());
		System.out.println(chinnchiro.ChinchiroGame(2));
	}
}
