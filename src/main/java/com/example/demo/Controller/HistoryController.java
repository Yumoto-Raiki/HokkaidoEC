package com.example.demo.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Model.DTO.HistoryDTO;
import com.example.demo.Model.Dao.HistoryDAO;
import com.example.demo.Model.Dao.ProductDao;
import com.example.demo.Model.intarface.IProduct;

import jakarta.servlet.http.HttpSession;

@Controller
public class HistoryController {

	@GetMapping("/historyShow")
	public String historyShow(HttpSession session, Model model) {
		HistoryDAO history = new HistoryDAO();
		IProduct iProduct = new ProductDao();
		int userId = (int) session.getAttribute("userId");
		List<HistoryDTO> hDTOs = history.historyShow(userId);
		for (HistoryDTO hDTO : hDTOs) {
			hDTO.setProduct(iProduct.productDetails(hDTO.getProductId()));
		}

		model.addAttribute("histories", hDTOs);

		return "order_history";
	}

	@GetMapping("/addHistory")
	public String addHistory(HttpSession session, @RequestParam("productId") int productId,
			@RequestParam("count") int count) {
		HistoryDAO history = new HistoryDAO();
		int userId = (int) session.getAttribute("userId");
		history.addHistory(userId, productId, count, LocalDateTime.now());
		return "gochumon_kakunin";

	}
}
