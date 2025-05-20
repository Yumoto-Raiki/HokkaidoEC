package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Model.DTO.AccountDTO;
import com.example.demo.Model.Dao.AccountDao;
import com.example.demo.Model.intarface.IAccount;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {

	IAccount iAccount = new AccountDao();

	/**
	 * アカウントを追加する
	 * @return
	 */
	@GetMapping("/addAAccount")
	public String addAAccount(HttpSession httpSession, @RequestParam("accountDTO") AccountDTO accountDTO) {

		int myId = iAccount.addAAccount(accountDTO);

		httpSession.setAttribute("userId", myId);

		return "home";

	}

	/**
	 * アカウントを削除する
	 * @return
	 */
	@GetMapping("/removeAAccount")
	public String removeAAccount(HttpSession httpSession, @RequestParam("userId") int userId) {

		iAccount.removeAAccount(userId);
		httpSession.removeAttribute("userId");
		return "home";

	}

	/**
	 * アカウント情報を更新する
	 * @return
	 */
	@GetMapping("/updateAAccount")
	public String updateAAccount() {

		return "";
	}

	/**
	 * アカウント情報を取得
	 * @return
	 */
	@GetMapping("/AccountShow")
	public String AccountShow() {

		return "";
	}

}
