package com.example.demo.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Model.DTO.CartDTO;

@Controller
public class PurhaseController {

	@GetMapping
	public String showPurhase(Model model, @RequestParam("") List<CartDTO> cartDTOs) {
		model.addAttribute("cartDTOs", cartDTOs);
		return "";
	}

}
