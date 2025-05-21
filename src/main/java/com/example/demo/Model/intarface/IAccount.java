package com.example.demo.Model.intarface;

import com.example.demo.Model.DTO.AccountAddDTO;
import com.example.demo.Model.DTO.AccountUpdateDTO;

public interface IAccount {

	/**
	 * アカウントを追加する
	 * @return
	 */
	public int addAAccount(AccountAddDTO accountAddDTO);

	/**
	 * アカウントを削除する
	 * @return
	 */
	public void removeAAccount(int userId);

	/**
	 * アカウント情報を更新する
	 * @return
	 */
	public void updateAAccount(int userId, AccountUpdateDTO accountUpdateDTO);

	/**
	 * アカウント情報を取得
	 * @return
	 */
	public void AccountShow();

}
