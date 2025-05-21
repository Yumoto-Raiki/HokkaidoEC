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

	private final int showCount = 7;

	public List<ProductDTO> productfavoriteShow() {
		List<ProductDTO> dtos = new ArrayList<>();

		// DB接続を開始
		connect();

		// 商品を取得するSQL
		String sql = "SELECT * FROM items JOIN item_photos ON items.item_id = item_photos.item_id ORDER BY order_amount DESC";

		// try-with-resources で自動的にリソースをクローズ
		try (PreparedStatement ps = con.prepareStatement(sql)) {

			// SQLを実行し、結果をResultSetに格納Li
			ResultSet rs = ps.executeQuery();

			// 次がなくなるまでループ
			for (int i = 0; i < showCount; i++) {

				if (!rs.next()) {

					System.out.println("中身がもうないため戻る");
					break;

				}

				ProductDTO dto = new ProductDTO();
				// 結果を取得
				dto.setProductId(rs.getInt("item_id"));
				dto.setName(rs.getString("item_name"));
				dto.setPrice(rs.getInt("price"));
				dto.setStock(rs.getInt("stock"));
				dto.setProductionarea(rs.getString("production_area"));
				dto.setText(rs.getString("explanatory_text"));
				dto.setCategory(rs.getString("category"));
				dto.setImageURL("/images/products/" + rs.getString("item_photo_url"));
				dto.setVolume(rs.getString("volume"));
				dto.setAddeddate(rs.getString("added_date"));
				dto.setSeller(rs.getString("seller"));
				dto.setOrderamount(rs.getInt("order_amount"));
				// リストに詰める
				dtos.add(dto);
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		// DB切断
		close();

		// 詰め終わったリストを帰す
		return dtos;
	}

	public List<ProductDTO> newproductShow() {
		List<ProductDTO> dtos = new ArrayList<>();

		// DB接続を開始
		connect();

		// 商品を取得するSQL
		String sql = "SELECT * FROM items JOIN item_photos ON items.item_id = item_photos.item_id ORDER BY added_date DESC";

		// try-with-resources で自動的にリソースをクローズ
		try (PreparedStatement ps = con.prepareStatement(sql)) {

			// SQLを実行し、結果をResultSetに格納
			ResultSet rs = ps.executeQuery();

			// 次がなくなるまでループ
			while (rs.next()) {

				ProductDTO dto = new ProductDTO();
				// 結果を取得
				dto.setProductId(rs.getInt("item_id"));
				dto.setName(rs.getString("item_name"));
				dto.setPrice(rs.getInt("price"));
				dto.setStock(rs.getInt("stock"));
				dto.setProductionarea(rs.getString("production_area"));
				dto.setText(rs.getString("explanatory_text"));
				dto.setCategory(rs.getString("category"));
				dto.setImageURL("/images/products/" + rs.getString("item_photo_url"));
				dto.setVolume(rs.getString("volume"));
				dto.setAddeddate(rs.getString("added_date"));
				dto.setSeller(rs.getString("seller"));
				dto.setOrderamount(rs.getInt("order_amount"));
				// リストに詰める
				dtos.add(dto);
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

	public List<ProductDTO> searchProduct(Category category, Sort sort) {

		List<ProductDTO> dtos = new ArrayList<>();

		// DB接続を開始
		connect();

		// 商品を取得するSQL
		String sql = null;

		sql = "SELECT * FROM items JOIN item_photos ON items.item_id = item_photos.item_id WHERE category = ? ORDER BY "
				+ sort.getSqlOrder();

		// try-with-resources で自動的にリソースをクローズ
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			String categoryText = category.getLabel();
			ps.setString(1, categoryText);

			// SQLを実行し、結果をResultSetに格納
			ResultSet rs = ps.executeQuery();

			// 次がなくなるまでループ
			while (rs.next()) {

				ProductDTO dto = new ProductDTO();
				// 結果を取得
				dto.setProductId(rs.getInt("item_id"));
				dto.setName(rs.getString("item_name"));
				dto.setPrice(rs.getInt("price"));
				dto.setStock(rs.getInt("stock"));
				dto.setProductionarea(rs.getString("production_area"));
				dto.setText(rs.getString("explanatory_text"));
				dto.setCategory(rs.getString("category"));
				dto.setImageURL("/images/products/" + rs.getString("item_photo_url"));
				System.out.println("URLセット" + "/images/products/" + rs.getString("item_photo_url"));
				System.out.println("URLセット2" + dto.getImageURL());
				dto.setVolume(rs.getString("volume"));
				dto.setAddeddate(rs.getString("added_date"));
				dto.setSeller(rs.getString("seller"));
				dto.setOrderamount(rs.getInt("order_amount"));
				// リストに詰める
				dtos.add(dto);
			}

		} catch (SQLException e) {
			// SQL例外が発生したらエラー内容を出力
			System.err.println("エラーが発生しました" + e);
		}

		// DB切断
		close();

		System.out.println(dtos);
		// 詰め終わったリストを帰す
		return dtos;

	}

	public List<ProductDTO> searchProduct(String text, Sort sort) {
		// TODO 自動生成されたメソッド・スタブ
		List<ProductDTO> dtos = new ArrayList<>();

		// DB接続を開始
		connect();

		// 商品を取得するSQL
		String sql = null;

		sql = "SELECT * FROM items JOIN item_photos ON items.item_id = item_photos.item_id WHERE items.item_name like  ?  ORDER BY"
				+ sort.getSqlOrder();

		// try-with-resources で自動的にリソースをクローズ
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, "%" + text + "%");

			// SQLを実行し、結果をResultSetに格納
			ResultSet rs = ps.executeQuery();

			// 次がなくなるまでループ
			while (rs.next()) {

				ProductDTO dto = new ProductDTO();
				// 結果を取得
				dto.setProductId(rs.getInt("item_id"));
				dto.setName(rs.getString("item_name"));
				dto.setPrice(rs.getInt("price"));
				dto.setStock(rs.getInt("stock"));
				dto.setProductionarea(rs.getString("production_area"));
				dto.setText(rs.getString("explanatory_text"));
				dto.setCategory(rs.getString("category"));
				dto.setImageURL("/images/products/" + rs.getString("item_photo_url"));
				dto.setVolume(rs.getString("volume"));
				dto.setAddeddate(rs.getString("added_date"));
				dto.setSeller(rs.getString("seller"));
				dto.setOrderamount(rs.getInt("order_amount"));
				// リストに詰める
				dtos.add(dto);
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
	public ProductDTO productDetails(int productid) {
		// TODO 自動生成されたメソッド・スタブ
		ProductDTO dto = new ProductDTO();

		// DB接続
		connect();

		String sql = "SELECT * FROM items JOIN item_photos ON items.item_id = item_photos.item_id WHERE items.item_id = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, productid);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				dto.setProductId(rs.getInt("item_id"));
				dto.setName(rs.getString("item_name"));
				dto.setPrice(rs.getInt("price"));
				dto.setStock(rs.getInt("stock"));
				dto.setProductionarea(rs.getString("production_area"));
				dto.setText(rs.getString("explanatory_text"));
				dto.setCategory(rs.getString("category"));
				dto.setImageURL("/images/products/" + rs.getString("item_photo_url"));
				dto.setVolume(rs.getString("volume"));
				dto.setAddeddate(rs.getString("added_date"));
				dto.setSeller(rs.getString("seller"));
				dto.setOrderamount(rs.getInt("order_amount"));
			}
		} catch (SQLException e) {
			// SQL例外が発生したらエラー内容を出力
			System.err.println("エラーが発生しました" + e);
		}
		// DB切断
		close();

		return dto;
	}

	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
		System.out.println(productDao.searchProduct(Category.MEAT, Sort.DATE_DESC));
		//		System.out.println(productDao.newproductShow());
		//		System.out.println(productDao.searchProduct(Category.肉, Sort.PRICE_ASC));
		//System.out.println(productDao.searchProduct("昆布", Sort.PRICE_ASC));
		//		System.out.println(productDao.productDetails(112));
	}

}
