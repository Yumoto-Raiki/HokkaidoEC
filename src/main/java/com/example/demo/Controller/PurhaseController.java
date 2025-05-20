package com.example.demo.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Model.DTO.CartDTO;
import com.example.demo.Model.Dao.UserDAO;

import jakarta.servlet.http.HttpSession;

@Controller
public class PurhaseController {

	@GetMapping
	public String showPurhase(HttpSession session, Model model, @RequestParam("") List<CartDTO> cartDTOs) {
		model.addAttribute("cartDTOs", cartDTOs);
		UserDAO user = new UserDAO();
		int userId = (int) session.getAttribute("userId");
		user.getUserInfo(userId);
		return "";

	}

}
