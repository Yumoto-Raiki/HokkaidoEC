package com.example.demo.Model.intarface;

import com.example.demo.Model.DTO.AccountDTO;

public interface IAccount {

	/**
	 * アカウントを追加する
	 * @return
	 */
	public int addAAccount(AccountDTO accountDTO);

	/**
	 * アカウントを削除する
	 * @return
	 */
	public void removeAAccount(int userId);

	/**
	 * アカウント情報を更新する
	 * @return
	 */
	public void updateAAccount();

	/**
	 * アカウント情報を取得
	 * @return
	 */
	public void AccountShow();

}
