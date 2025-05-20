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

				ProductDTO dao = new ProductDTO();
				// 結果を取得
				dao.setProductId(rs.getInt("item_id"));
				dao.setName(rs.getString("item_name"));
				dao.setPrice(rs.getInt("price"));
				dao.setStock(rs.getInt("stock"));
				dao.setProductionarea(rs.getString("production_area"));
				dao.setText(rs.getString("explanatory_text"));
				dao.setCategory(rs.getString("category"));
				dao.setImageURL("/images/products/" + "melon.jpg");//rs.getString("item_photo_url")
				dao.setVolume(rs.getString("volume"));
				dao.setAddeddate(rs.getString("added_date"));
				dao.setSeller(rs.getString("seller"));
				dao.setOrderamount(rs.getInt("order_amount"));
				System.out.println(dao);
				// リストに詰める
				dtos.add(dao);
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
				System.out.println("DTO生成");
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

				// DBから取得したURLをGoogle Driveのダウンロード形式に変換
				String rawUrl = rs.getString("item_photo_url");
				String fileId = "";
				if (rawUrl.contains("/d/")) {

					// ファイルIDを抽出
					int start = rawUrl.indexOf("/d/") + 3;
					int end = rawUrl.indexOf("/", start);
					if (end == -1)
						end = rawUrl.indexOf("?", start); // /view? のケース
					if (end == -1)
						end = rawUrl.length();
					fileId = rawUrl.substring(start, end);

				} else if (rawUrl.contains("id=")) {
					int start = rawUrl.indexOf("id=") + 3;
					int end = rawUrl.indexOf("&", start);
					if (end == -1)
						end = rawUrl.length();
					fileId = rawUrl.substring(start, end);
				}
				// ダウンロード用URLに変換
				String directUrl = "https://drive.google.com/uc?export=view&id=" + fileId;
				// 以下、あなたのコードのまま続けてOK
				//URL url = new URL(directUrl);
				//InputStream input = url.openStream();
				//BufferedImage image = ImageIO.read(input);

				dao.setImageURL(directUrl);
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
		productDao.productfavoriteShow();
		//		System.out.println(productDao.newproductShow());
		//		System.out.println(productDao.searchProduct(Category.肉, Sort.PRICE_ASC));
		//System.out.println(productDao.searchProduct("昆布", Sort.PRICE_ASC));
		//		System.out.println(productDao.productDetails(112));
	}

}
