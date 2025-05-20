package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Model.Dao.ChinnchiroDao;

import jakarta.servlet.http.HttpSession;

@Controller
public class ChinnchiroController {

	//最初に呼ばれるチンチロ
	@PostMapping("/chinnchiroStrat")
	public String chinnchiroStrat(Model model, HttpSession session) {
		Boolean rolled = (Boolean) session.getAttribute("rolled");
		//振ったことがあるか判定
		if (rolled != null && rolled) {
			model.addAttribute("canRoll", false);
		} else {
			model.addAttribute("canRoll", true);
		}
		return "chinnchiro";
	}

	//サイコロを振ったときに呼ばれる
	@PostMapping("/roll")
	public String chinnchiroRoll(Model model, HttpSession session) {

		Boolean rolled = (Boolean) session.getAttribute("rolled");
		//振ったことがあるか判定
		if (rolled != null && rolled) {
			model.addAttribute("error", "すでにサイコロを振っています。");
			model.addAttribute("canRoll", false);
			return "chinnchiro";
		}

		ChinnchiroDao chinnchiro = new ChinnchiroDao();
		int[] dice = chinnchiro.ChinchiroGame();
		session.setAttribute("rolled", true); // フラグをセッションに保存
		model.addAttribute("dice", dice);//出目を取得
		model.addAttribute("canRoll", false);//もう触れないことをHTMLに教える
		model.addAttribute("disount", chinnchiro.settlement_change() * chinnchiro.judgeDice(dice));

		return "chinnchiro";
	}

	//もう一度触れるようにする テスト用
	@PostMapping("/gg")
	public String invalidate(Model model, HttpSession session) {
		session.setAttribute("rolled", false);
		Boolean rolled = (Boolean) session.getAttribute("rolled");
		//振ったことがあるか判定
		if (rolled != null && rolled) {
			model.addAttribute("canRoll", false);
		} else {
			model.addAttribute("canRoll", true);
		}
		return "chinnchiro";
	}

	@PostMapping("/payment")
	public String paymentCompleted(@RequestParam("disount") int totalamount, Model model, HttpSession session) {
		session.setAttribute("rolled", false);
		model.addAttribute("total", totalamount);

		return "thankyou";
	}

}
