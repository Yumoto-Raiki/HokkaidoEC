package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Model.Dao.ChinnchiroDao;

import jakarta.servlet.http.HttpSession;

@Controller
public class ChinnchiroController {

	//最初に呼ばれるチンチロ
	@GetMapping("/a")
	public String chinnchiroStrat(Model model, HttpSession session) {
		Boolean rolled = (Boolean) session.getAttribute("rolled");
		if (rolled != null && rolled) {
			model.addAttribute("canRoll", false);
		} else {
			model.addAttribute("canRoll", true);
		}
		return "chinnchiro";
	}

	//サイコロを振ったときに呼ばれる
	@PostMapping("/roll")
	public String chinnchiroShow(Model model, HttpSession session) {

		Boolean rolled = (Boolean) session.getAttribute("rolled");
		if (rolled != null && rolled) {
			model.addAttribute("error", "すでにサイコロを振っています。");
			model.addAttribute("canRoll", false);
			return "chinnchiro";
		}

		ChinnchiroDao chinnchiro = new ChinnchiroDao();
		int[] dice = chinnchiro.ChinchiroGame();
		session.setAttribute("rolled", true); // フラグをセッションに保存
		model.addAttribute("dice", dice);
		model.addAttribute("canRoll", false);
		model.addAttribute("disount", chinnchiro.settlement_change() * chinnchiro.judgeDice(dice));

		return "chinnchiro";
	}

	//もう一度触れるようにする
	@PostMapping("/gg")
	public String invalidate(Model model, HttpSession session) {
		session.setAttribute("rolled", false);
		return "chinnchiro";
	}

}
