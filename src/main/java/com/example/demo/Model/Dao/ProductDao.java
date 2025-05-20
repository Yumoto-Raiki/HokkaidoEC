package com.example.demo.Model.Dao;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.example.demo.Model.DTO.ProductDTO;
import com.example.demo.Model.Enum.Category;
import com.example.demo.Model.Enum.Sort;
import com.example.demo.Model.intarface.IProduct;

public class ProductDao extends DBConectDao implements IProduct {

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
			while (rs.next()) {

				ProductDTO dao = new ProductDTO();
				// 結果を取得
				dao.setProductId(rs.getInt("item_id"));
				dao.setName(rs.getString("item_name"));
				dao.setPrice(rs.getInt("price"));
				dao.setStock(rs.getInt("stock"));
				dao.setProductionarea(rs.getString("production_area"));
				dao.setText(rs.getString("explanatory_text"));
				dao.setCategory(rs.getString("category"));

				//DBのURL取得
				URL url = null;
				try {
					url = new URL(rs.getString("item_photo_url"));
				} catch (MalformedURLException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
				//URLの中身を確認している
				InputStream input = null;
				try {
					input = url.openStream();
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				//取得したURLを画像に変換
				BufferedImage image = null;
				try {
					image = ImageIO.read(input);
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				dao.setImage(image);

				dao.setVolume(rs.getString("volume"));
				dao.setAddeddate(rs.getString("added_date"));
				dao.setSeller(rs.getString("seller"));
				dao.setOrderamount(rs.getInt("order_amount"));
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

				ProductDTO dao = new ProductDTO();
				// 結果を取得
				dao.setProductId(rs.getInt("item_id"));
				dao.setName(rs.getString("item_name"));
				dao.setPrice(rs.getInt("price"));
				dao.setStock(rs.getInt("stock"));
				dao.setProductionarea(rs.getString("production_area"));
				dao.setText(rs.getString("explanatory_text"));
				dao.setCategory(rs.getString("category"));

				dao.setVolume(rs.getString("volume"));
				dao.setAddeddate(rs.getString("added_date"));
				dao.setSeller(rs.getString("seller"));
				dao.setOrderamount(rs.getInt("order_amount"));
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

	public List<ProductDTO> searchProduct(Category category, Sort sort) {

		List<ProductDTO> dtos = new ArrayList<>();

		// DB接続を開始
		connect();

		// 商品を取得するSQL
		String sql = null;

		sql = "SELECT * FROM items JOIN item_photos ON items.item_id = item_photos.item_id WHERE category = ? ORDER BY"
				+ sort.getSqlOrder();

		/*switch (sort) {
		case PRICE_ASC:
			sql = "SELECT * FROM items JOIN item_photos ON items.item_id = item_photos.item_id WHERE category = ? ORDER BY price ASC";
			break;
		case PRICE_DESC:
			sql = "SELECT * FROM items JOIN item_photos ON items.item_id = item_photos.item_id WHERE category = ? ORDER BY price DESC";
			break;
		case FAVORITE_DESC:
			sql = "SELECT * FROM items JOIN item_photos ON items.item_id = item_photos.item_id WHERE category = ? ORDER BY order_amount ASC";
			break;
		case DATE_ASC:
			sql = "SELECT * FROM items JOIN item_photos ON items.item_id = item_photos.item_id WHERE category = ? ORDER BY added_date ASC";
			break;
		case DATE_DESC:
			sql = "SELECT * FROM items JOIN item_photos ON items.item_id = item_photos.item_id WHERE category = ? ORDER BY added_date DESC";
			break;
		
		}*/

		// try-with-resources で自動的にリソースをクローズ
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			String categoryText = String.valueOf(category);
			ps.setString(1, categoryText);

			// SQLを実行し、結果をResultSetに格納
			ResultSet rs = ps.executeQuery();

			// 次がなくなるまでループ
			while (rs.next()) {

				ProductDTO dao = new ProductDTO();
				// 結果を取得
				dao.setProductId(rs.getInt("item_id"));
				dao.setName(rs.getString("item_name"));
				dao.setPrice(rs.getInt("price"));
				dao.setStock(rs.getInt("stock"));
				dao.setProductionarea(rs.getString("production_area"));
				dao.setText(rs.getString("explanatory_text"));
				dao.setCategory(rs.getString("category"));

				dao.setVolume(rs.getString("volume"));
				dao.setAddeddate(rs.getString("added_date"));
				dao.setSeller(rs.getString("seller"));
				dao.setOrderamount(rs.getInt("order_amount"));
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

	public List<ProductDTO> searchProduct(String text, Sort sort) {
		// TODO 自動生成されたメソッド・スタブ
		List<ProductDTO> dtos = new ArrayList<>();

		// DB接続を開始
		connect();

		// 商品を取得するSQL
		String sql = null;

		sql = "SELECT * FROM items JOIN item_photos ON items.item_id = item_photos.item_id WHERE items.item_name like  ?  ORDER BY"
				+ sort.getSqlOrder();

		/*switch (sort) {
		case PRICE_ASC:
			sql = "SELECT * FROM items WHERE item_name like  ?  ORDER BY price ASC";
			break;
		case PRICE_DESC:
			sql = "SELECT * FROM items WHERE item_name like  ?  ORDER BY price DESC";
			break;
		case FAVORITE_DESC:
			sql = "SELECT * FROM items WHERE item_name like  ?  ORDER BY order_amount ASC";
			break;
		case DATE_ASC:
			sql = "SELECT * FROM items WHERE item_name like  ?  ORDER BY added_date ASC";
			break;
		case DATE_DESC:
			sql = "SELECT * FROM items WHERE item_name like  ?  ORDER BY added_date DESC";
			break;
		
		}*/
		// try-with-resources で自動的にリソースをクローズ
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, "%" + text + "%");

			// SQLを実行し、結果をResultSetに格納
			ResultSet rs = ps.executeQuery();

			// 次がなくなるまでループ
			while (rs.next()) {

				ProductDTO dao = new ProductDTO();
				// 結果を取得
				dao.setProductId(rs.getInt("item_id"));
				dao.setName(rs.getString("item_name"));
				dao.setPrice(rs.getInt("price"));
				dao.setStock(rs.getInt("stock"));
				dao.setProductionarea(rs.getString("production_area"));
				dao.setText(rs.getString("explanatory_text"));
				dao.setCategory(rs.getString("category"));

				dao.setVolume(rs.getString("volume"));
				dao.setAddeddate(rs.getString("added_date"));
				dao.setSeller(rs.getString("seller"));
				dao.setOrderamount(rs.getInt("order_amount"));
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
	public ProductDTO productDetails(int productid) {
		// TODO 自動生成されたメソッド・スタブ
		ProductDTO dao = new ProductDTO();

		// DB接続
		connect();

		String sql = "SELECT * FROM items JOIN item_photos ON items.item_id = item_photos.item_id WHERE items.item_id = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, productid);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				dao.setProductId(rs.getInt("item_id"));
				dao.setName(rs.getString("item_name"));
				dao.setPrice(rs.getInt("price"));
				dao.setStock(rs.getInt("stock"));
				dao.setProductionarea(rs.getString("production_area"));
				dao.setText(rs.getString("explanatory_text"));
				dao.setCategory(rs.getString("category"));

				//DBのURL取得
				URL url = null;
				try {
					url = new URL(rs.getString("item_photo_url"));
				} catch (MalformedURLException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
				//URLの中身を確認している
				InputStream input = null;
				try {
					input = url.openStream();
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				//取得したURLを画像に変換
				BufferedImage image = null;
				try {
					image = ImageIO.read(input);
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				dao.setImage(image);

				dao.setVolume(rs.getString("volume"));
				dao.setAddeddate(rs.getString("added_date"));
				dao.setSeller(rs.getString("seller"));
				dao.setOrderamount(rs.getInt("order_amount"));
			}
		} catch (SQLException e) {
			// SQL例外が発生したらエラー内容を出力
			System.err.println("エラーが発生しました" + e);
		}
		// DB切断
		close();

		return dao;
	}

	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
		//		System.out.println(productDao.productfavoriteShow());
		//		System.out.println(productDao.newproductShow());
		//		System.out.println(productDao.searchProduct(Category.肉, Sort.PRICE_ASC));
		System.out.println(productDao.searchProduct("昆布", Sort.PRICE_ASC));
		//		System.out.println(productDao.productDetails(112));
	}

}
