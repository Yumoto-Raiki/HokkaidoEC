package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettlementController {

	@GetMapping("/settlement")
	public String showPurhase(Model model/*, @RequestParam("") List<CartDTO> cartDTOs*/) {
		/*model.addAttribute("cartDTOs", cartDTOs);*/
		return "gochumon_kakunin";
	}
}
