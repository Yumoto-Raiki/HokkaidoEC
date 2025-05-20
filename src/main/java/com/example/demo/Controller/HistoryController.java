package com.example.demo.Controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Model.Dao.HistoryDAO;

import jakarta.servlet.http.HttpSession;

@Controller
public class HistoryController {

	@GetMapping("/historyShow")
	public String historyShow(HttpSession session) {
		HistoryDAO history = new HistoryDAO();
		int userId = (int) session.getAttribute("userId");
		history.historyShow(userId);
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
