package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/login")
	public String login(@RequestParam("") String userName, String pass, HttpSession session) {
		IAuthSystem iAuthSystem = new AuthSystemDao();
		session.setAttribute("userId", iAuthSystem.login(userName, pass));
		return "";
	}

	@GetMapping("/loguot")
	public String logout(HttpSession session) {
		session.removeAttribute("userId");
		return "";
	}

}
