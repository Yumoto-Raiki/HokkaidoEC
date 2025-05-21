package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Model.Dao.FavoriteDao;
import com.example.demo.Model.intarface.IFavorite;

import jakarta.servlet.http.HttpSession;

@Controller
public class FavoriteController {
	IFavorite iFavorite;

	public FavoriteController() {
		this.iFavorite = new FavoriteDao();
	}

	@GetMapping("/FavoriteController")
	public String addProductToFavorite(Model model, @RequestParam("") int productId, HttpSession session) {
		int userId = (int) session.getAttribute("userId");

		// ユーザーIDが0（ログインしていないなど）の場合、失敗として処理
		if (userId == 0) {
			model.addAttribute("isComplete", false); // カート追加が失敗したことを画面に渡す
			return "/favorite"; // どの画面を表示するか指定されていない（要修正）
		}

		// カートに商品を追加（データベースに追加）
		iFavorite.addProductToFavorite(productId, userId);

		// 成功したことを画面に渡す（"isComplete"という名前でtrueを渡す）
		model.addAttribute("isComplete", true);

		return "/favorite";
	}

	public String removeProductToFavorite(@RequestParam("") int facvoriteId, HttpSession session, Model model) {
		int userId = (int) session.getAttribute("userId");
		if (userId == 0) {
			model.addAttribute("isComplete", false);
			return "";
		}
		iFavorite.removeProductToFavorite(facvoriteId);
		model.addAttribute("isComplete", true);
		return "/favorite";
	}

	public String clearFavorite(Model model, HttpSession session) {
		int userId = (int) session.getAttribute("userId");
		if (userId == 0) {
			model.addAttribute("isComplete", false);
			return "/favorite";
		}
		model.addAttribute("products", iFavorite.getFavorite(userId));
		model.addAttribute("isComplete", true);
		return "/favorite";

	}

	@GetMapping("/showFavorite")
	public String showFavorite(HttpSession session, Model model) {

		int userId = (int) session.getAttribute("userId");
		if (userId == 0) {
			model.addAttribute("isComplete", false);
			return "favorite";
		}

		model.addAttribute("products", iFavorite.getFavorite(userId));
		model.addAttribute("isComplete", true);
		return "favorite";
	}
}
