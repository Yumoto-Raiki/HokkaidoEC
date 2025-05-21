package com.example.demo.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Model.DTO.HistoryDTO;
import com.example.demo.Model.DTO.ProductDTO;
import com.example.demo.Model.Dao.HistoryDAO;
import com.example.demo.Model.Dao.ProductDao;
import com.example.demo.Model.intarface.IProduct;

import jakarta.servlet.http.HttpSession;

@Controller
public class HistoryController {

	@GetMapping("/historyShow")
	public String historyShow(HttpSession session) {
		HistoryDAO history = new HistoryDAO();
		IProduct iProduct = new ProductDao();
		int userId = (int) session.getAttribute("userId");
		List<HistoryDTO> hDTOs = history.historyShow(userId);
		List<ProductDTO> pDTOs = new ArrayList<>();
		for (HistoryDTO hDTO : hDTOs) {

			pDTOs.add(iProduct.productDetails(hDTO.getProductId()));

		}

		return "";
	}

	@GetMapping("/addHistory")
	public String addHistory(HttpSession session, @RequestParam("") int productId, @RequestParam("") int count,
			LocalDateTime buydate) {
		HistoryDAO history = new HistoryDAO();
		int userId = (int) session.getAttribute("userId");
		history.addHistory(userId, productId, count, buydate);
		return "";

	}
}
