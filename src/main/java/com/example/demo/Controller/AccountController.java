package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Model.DTO.UserDTO;

@Controller
public class AccountController {

	/**
	 * アカウントを追加する
	 * @return
	 */
	@GetMapping("/addAAccount")
	public String addAAccount() {

		return "";

	}

	/**
	 * アカウントを削除する
	 * @return
	 */
	@GetMapping("/removeAAccount")
	public String removeAAccount() {

		return "";
	}

	/**
	 * アカウント情報を更新する
	 * @return
	 */
	@GetMapping("/updateAAccount")
	public String updateAAccount() {

		return "";
	}

	/**
	 * アカウント情報を取得
	 * @return
	 */
	@GetMapping("/AccountShow")
	public UserDTO AccountShow() {
		//TODO:エラーが出ないように暫定的に値なしのインスタンスを返している
		return new UserDTO();
	}

}
