package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	//ここから下が背戸田プラクティスなので消してもOK（起動用getmapping）

	@GetMapping("/account")
	public String bb(Model model, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null || userId == 0) {
			return "redirect:/login";
		}

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

}
