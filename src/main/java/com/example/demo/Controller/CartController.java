package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Model.Dao.CartDao;
import com.example.demo.Model.intarface.ICart;

import jakarta.servlet.http.HttpSession;

//Spring FrameworkにこのクラスがWebコントローラーであることを知らせるアノテーション
@Controller
public class CartController {

	// カートに関する処理を行うインターフェース
	ICart iCart;

	// コンストラクタ（クラスが作られるときに最初に実行される）
	public CartController() {
		// 実際の処理をするCartDaoクラスをここで使えるようにする
		this.iCart = new CartDao();
	}

	// 「/addProductToCart」にGETリクエストが来たときにこのメソッドが呼ばれる
	@GetMapping("/addProductToCart")
	public String addProductToCart(
			Model model, // 画面（HTMLなど）にデータを渡すための入れ物
			@RequestParam("") int productId, // リクエストパラメータ（商品ID）→ ""の中に名前が必要
			@RequestParam("") int count, // リクエストパラメータ（個数）→ ""の中に名前が必要
			HttpSession session // ユーザー情報を一時的に保存しておくセッション
	) {
		// セッションからユーザーIDを取り出す
		int userId = (int) session.getAttribute("userId");

		// ユーザーIDが0（ログインしていないなど）の場合、失敗として処理
		if (userId == 0) {
			model.addAttribute("isComplete", false); // カート追加が失敗したことを画面に渡す
			return ""; // どの画面を表示するか指定されていない（要修正）
		}

		// カートに商品を追加（データベースに追加）
		iCart.addProductToCart(productId, count, userId);

		// 成功したことを画面に渡す（"isComplete"という名前でtrueを渡す）
		model.addAttribute("isComplete", true);

		return ""; // 表示するビュー名（画面）を返す必要がある（例："cartView"）
	}

	@GetMapping("/removeProductToCart")
	public String removeProductToCart(Model model, @RequestParam("") int cartId, HttpSession session) {
		int userId = (int) session.getAttribute("userId");
		if (userId == 0) {
			model.addAttribute("isComplete", false);
			return "";
		}
		iCart.removeProductToCart(cartId);
		model.addAttribute("isComplete", true);
		return "";
	}

	public String updateCartInCount(int cartId, int count, Model model, HttpSession session) {
		int userId = (int) session.getAttribute("userId");
		if (userId == 0) {
			model.addAttribute("isComplete", false);
			return "";
		}
		iCart.updateCartInCount(cartId, count);
		model.addAttribute("isComplete", true);
		return "";

	}

	public String clearCart(Model model, HttpSession session) {
		int userId = (int) session.getAttribute("userId");
		if (userId == 0) {
			model.addAttribute("isComplete", false);
			return "";
		}
		iCart.clearCart(userId);
		model.addAttribute("isComplete", true);
		return "";
	}

	public String showCart(Model model, HttpSession session) {
		int userId = (int) session.getAttribute("userId");
		if (userId == 0) {
			model.addAttribute("isComplete", false);
			return "";
		}
		model.addAttribute("products", iCart.getCart(userId));
		model.addAttribute("isComplete", true);
		return "";

	}

}
