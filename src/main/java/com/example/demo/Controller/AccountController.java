package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Model.DTO.AccountAddDTO;
import com.example.demo.Model.DTO.AccountShowDTO;
import com.example.demo.Model.Dao.AccountDao;
import com.example.demo.Model.intarface.IAccount;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {

	IAccount iAccount = new AccountDao();

	@GetMapping("/addAccountShow")
	public String addAccountShow(Model model) {
		model.addAttribute("accountDTO", new AccountAddDTO());
		return "account_touroku";

	}

	/**
	 * アカウントを追加する
	 * @return
	 */
	@PostMapping("/addAAccount")
	public String addAAccount(HttpSession httpSession, @ModelAttribute AccountAddDTO accountDTO) {

		int myId = iAccount.addAAccount(accountDTO);

		httpSession.setAttribute("userId", myId);

		return "home";

	}

	/**
	 * アカウントを削除する
	 * @return
	 */
	@PostMapping("/removeAAccount")
	public String removeAAccount(HttpSession httpSession) {
		int userId = (int) httpSession.getAttribute("userId");
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
	public AccountShowDTO AccountShow() {
		//TODO:エラーが出ないように暫定的に値なしのインスタンスを返している
		return new AccountShowDTO();
	}

}
