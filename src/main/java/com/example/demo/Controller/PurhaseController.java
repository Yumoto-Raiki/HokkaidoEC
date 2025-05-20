package com.example.demo.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Model.DTO.CartDTO;
import com.example.demo.Model.Dao.AccountDao;

import jakarta.servlet.http.HttpSession;

@Controller
public class PurhaseController {

	@GetMapping
	public String showPurhase(HttpSession session, Model model, @RequestParam("") List<CartDTO> cartDTOs) {
		model.addAttribute("cartDTOs", cartDTOs);
		AccountDao acountDTO = new AccountDao();
		int userId = (int) session.getAttribute("userId");
		model.addAttribute("acountDTO", acountDTO.getAccountInfo(userId));
		model.addAttribute("cartDTOs", cartDTOs);
		return "";

	}

}
