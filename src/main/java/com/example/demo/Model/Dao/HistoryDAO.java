package com.example.demo.Model.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.Model.DTO.HistoryDTO;

public class HistoryDAO extends DBConectDao {
	public List<HistoryDTO> historyShow(int userId) {
		List<HistoryDTO> dtos = new ArrayList<>();

		connect();

		String sql = "select item_id,item_count,selled_date from historys where user_id=?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				HistoryDTO dto = new HistoryDTO();
				dto.setProductId(rs.getInt("item_id"));
				dto.setCount(rs.getInt("item_count"));
				dto.setBuydate(rs.getObject("selled_date", LocalDateTime.class));

				dtos.add(dto);
			}
		} catch (SQLException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		return dtos;
	}

	public void addHistory(int userId, int productId, int count, LocalDateTime buydate) {
		connect();
		String sql = "insert into hba2025_3.historys(user_id,item_id,item_count,selled_date) values(?,?,?,?);";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, userId);
			ps.setInt(2, productId);
			ps.setInt(3, count);
			ps.setObject(4, buydate);

			ps.executeUpdate();
		} catch (SQLException e) {
			// エラーが起きたときに、内容を表示する
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		HistoryDAO test = new HistoryDAO();
		List<HistoryDTO> histories = test.historyShow(2);
		System.out.println(histories.get(0).getBuydate());

	}
}
