package com.example.demo.Model.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.Model.DTO.ProductDTO;
import com.example.demo.Model.Enum.Category;
import com.example.demo.Model.Enum.Sort;
import com.example.demo.Model.intarface.IProduct;

public class ProductDao extends DBConectDao implements IProduct {

	public List<ProductDTO> searchProduct(Category category, Sort sort) {

		List<ProductDTO> dtos = new ArrayList<>();

		// DB接続を開始
		connect();

		// 商品を取得するSQL
		String sql = "";

		// try-with-resources で自動的にリソースをクローズ
		try (PreparedStatement ps = con.prepareStatement(sql)) {

			// SQLを実行し、結果をResultSetに格納
			ResultSet rs = ps.executeQuery();

			// 次がなくなるまでループ
			while (rs.next()) {

				ProductDTO dao = new ProductDTO();
				// 結果を取得
				dao.setProductId(rs.getInt("productId"));
				dao.setName(rs.getString("name"));
				// リストに詰める
				dtos.add(dao);

			}

		} catch (SQLException e) {
			// SQL例外が発生したらエラー内容を出力
			System.err.println("エラーが発生しました" + e);
		}

		// DB切断
		close();

		// 詰め終わったリストを帰す
		return dtos;

	}

	@Override
	public List<ProductDTO> searchProduct(String text, Category category) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public ProductDTO productDetails(int productid) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
