// パッケージの指定（このクラスがどこにあるかを示しています）
package com.example.demo.Model.DTO;

// カートに入っている商品の情報をまとめるクラス
public class CartDTO {

	// カートのID（どのカートかを識別するための番号）
	private int cartId;

	// カートに入っている商品の情報を表すオブジェクト（ProductDTOという別のクラス）
	private ProductDTO productDTO;

	// その商品が何個カートに入っているか（個数）
	private int count;

	// cartIdの値を取得するメソッド（ゲッター）
	public int getCartId() {
		return cartId;
	}

	// cartIdに値を設定するメソッド（セッター）
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	// 商品情報（ProductDTO）の値を取得するメソッド（ゲッター）
	public ProductDTO getProductDTO() {
		return productDTO;
	}

	// 商品情報に値を設定するメソッド（セッター）
	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}

	// 商品の個数（count）を取得するメソッド（ゲッター）
	public int getCount() {
		return count;
	}

	// 商品の個数に値を設定するメソッド（セッター）
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "cartId=" + cartId + "count=" + count + "productDTO=" + productDTO;
	}
}
