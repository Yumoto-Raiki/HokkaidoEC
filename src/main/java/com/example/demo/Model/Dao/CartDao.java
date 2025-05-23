// このクラスが所属するパッケージ（フォルダ構成みたいなもの）
package com.example.demo.Model.Dao;

// データベース関連で使うクラス
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// カートのデータ構造（DTO）を使うためのインポート
import com.example.demo.Model.DTO.CartDTO;
import com.example.demo.Model.DTO.ProductDTO;
// カート関連のインターフェース（仕様）を使う
import com.example.demo.Model.intarface.ICart;

// CartDaoクラスはDBConectDaoクラスを継承し、ICartインターフェースを実装している
public class CartDao extends DBConectDao implements ICart {

	// 商品をカートに追加するメソッド
	public void addProductToCart(int productId, int count, int userId) {
		// データベースに接続する（親クラスのメソッド）
		connect();

		// SQL文：カートテーブルにレコード（行）を追加する
		String sql = "INSERT INTO hba2025_3.carts(user_id,item_id,item_count) values(?,?,?);";

		// try-with-resources：使い終わったら自動的に閉じてくれる構文
		try (PreparedStatement ps = con.prepareStatement(sql)) {

			// SQL文の「?」に値を代入する
			ps.setInt(1, userId); // 1番目の? → ユーザーID
			ps.setInt(2, productId); // 2番目の? → 商品ID
			ps.setInt(3, count); // 3番目の? → 数量

			// SQLを実行する（データ追加）
			ps.executeUpdate();

		} catch (SQLException e) {
			// エラーが起きたときに、内容を表示する
			System.out.println(e);
		}

		// DB切断
		close();
	}

	// カートから商品を1つ削除するメソッド
	public void removeProductToCart(int cartId) {
		connect(); // データベースに接続

		// SQL文：指定したIDのカートアイテムを削除
		String sql = "delete from hba2025_3.carts where id=?;";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, cartId); // 削除するカートのIDを指定
			ps.executeUpdate(); // 実行（削除）
		} catch (SQLException e) {
			System.out.println(e); // エラー表示
		}

		// DB切断
		close();
	}

	public void updateCartInCount(int cartId, int count) {
		connect();
		String sql = "update hba2025_3.carts set item_count=? where id=?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, count);// 削除するカートのIDを指定
			ps.setInt(2, cartId);// 削除するカートのIDを指定
			ps.executeUpdate(); // 実行（削除）
		} catch (SQLException e) {
			System.out.println(e); // エラー表示
		}

		// DB切断
		close();
	}

	// カートをすべて空にする（指定ユーザーのカートを全削除）
	public void clearCart(int userId) {
		connect(); // データベースに接続

		// SQL文：指定したユーザーのカートの中身をすべて削除
		String sql = "delete from hba2025_3.carts where user_id=?;";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, userId); // ユーザーIDを指定
			ps.executeUpdate(); // 実行（削除）
		} catch (SQLException e) {
			System.out.println(e); // エラー表示
		}

		// DB切断
		close();
	}

	// カートの中身を取得するメソッド（未完成）
	public List<CartDTO> getCart(int userId) {
		// カートの中身を格納するリスト（今は空のまま）
		List<CartDTO> dtos = new ArrayList<>();

		connect();
		//TODO: cartsテーブルとitemsテーブルをjoin。whereでuser_idで検索
		String sql = "SELECT * FROM carts JOIN items ON carts.item_id = items.item_id WHERE carts.user_id =?;";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CartDTO dto = new CartDTO();
				dto.setCartId(rs.getInt("id"));
				//カート情報(個数など)をcartDtoにセット
				dto.setCount(rs.getInt("item_count"));
				ProductDTO product = new ProductDTO();
				//商品情報をproductにセット
				product.setName(rs.getString("item_name"));
				product.setPrice(rs.getInt("price"));

				dto.setProductDTO(product);
				dtos.add(dto);

			}
		} catch (SQLException e) {
			// エラーが起きたときに、内容を表示する
			System.out.println(e);
		}

		// DB切断
		close();
		// 本来ここでデータベースから情報を取り出してdtosに追加する処理を書く

		return dtos; // 現時点では空のリストを返すだけ
	}

	public int sumPrice() {
		int sum = 0;

		// DB接続を開始
		connect();

		String sql = "SELECT SUM(items.price * carts.item_count) FROM carts JOIN items ON carts.item_id = items.item_id ";
		try (PreparedStatement ps = con.prepareStatement(sql)) {

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

	// 動作確認用のメインメソッド（プログラムの入り口）
	public static void main(String[] args) {
		// CartDaoのインスタンスを作成
		CartDao test = new CartDao();
		//		System.out.println(test.getCart(1));
		System.out.println(test.getCart(19));

		//		System.out.println(test.sumPrice());
	}
}
