package com.example.demo.Model.DTO;

public class ChinnchiroDTO {

	private String messege;
	private int[] dice;
	private int sum;
	private double discount;

	public String getMessege() {
		return messege;
	}

	public void setMessege(String messege) {
		this.messege = messege;
	}

	public int[] getDice() {
		return dice;
	}

	public void setDice(int[] dice) {
		this.dice = dice;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	@Override
	public String toString() {
		return "messege=" + messege + "dice=" + dice[0] + dice[1] + dice[2] + "sum=" + sum + "disount=" + discount;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

}
