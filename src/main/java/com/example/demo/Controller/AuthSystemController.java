package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Model.DTO.UserDTO;
import com.example.demo.Model.Dao.AuthSystemDao;
import com.example.demo.Model.intarface.IAuthSystem;

import jakarta.servlet.http.HttpSession;

/**
 * 
 * 認証の操作
 *
 */
@Controller
public class AuthSystemController {

	@PostMapping("/login")
	public String login(@RequestParam("userName") String userName, @RequestParam("pass") String pass,
			HttpSession session) {
		IAuthSystem iAuthSystem = new AuthSystemDao();

		if (iAuthSystem.login(userName, pass) != 0) {
			session.setAttribute("userId", iAuthSystem.login(userName, pass));
			return "home";
		} else {
			return "login";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("userId");
		return "home";
	}

	@GetMapping("/login")
	public String aa() {
		return "login";
	}

	@GetMapping("/account")
	public String bb(Model model) {
		model.addAttribute("user.name", " ゆうと");

		return "account";

	}

	@GetMapping("/home")
	public String home() {
		return "home";
	}

	@GetMapping("/account/delete")
	public String delete() {
		return "account-delete";
	}

	//	@PostMapping("/logout")
	//	public String logout() {
	//		return "home";
	//
	//	}

	@GetMapping("/account_touroku")
	public String showRegistrationForm() {
		return "account_touroku"; // templates/account_touroku.html
	}

	@GetMapping("/humanfile")
	public String showUserInfo(Model model) {
		UserDTO user = new UserDTO();
		user.setName("テスト太郎");
		user.setMail("test@example.com");
		user.setAge(25);
		user.setPhoneNumber(1234567890);
		user.setAddress("東京都渋谷区");
		user.setPoint(100);

		model.addAttribute("user", user);
		return "humanfile"; // → user_info.html など
	}
}
