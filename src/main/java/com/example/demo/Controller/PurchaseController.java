package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Model.Dao.AccountDao;
import com.example.demo.Model.Dao.CartDao;
import com.example.demo.Model.intarface.IAccount;
import com.example.demo.Model.intarface.ICart;

import jakarta.servlet.http.HttpSession;

@Controller
public class PurchaseController {

	@GetMapping("/showPurchase")
	public String showPurchase(HttpSession session, Model model) {

		IAccount iAccount = new AccountDao();
		ICart iCart = new CartDao();
		int userId = (int) session.getAttribute("userId");
		model.addAttribute("acountShowDTO", iAccount.getAccountInfo(userId));
		model.addAttribute("products", iCart.getCart(userId));
		return "purchase";

	}

}