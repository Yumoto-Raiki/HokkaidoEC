package com.example.demo.Model.Enum;

public enum Category {

	FISH("魚介"), MEAT("肉"), VEGETABLE("野菜");

	private final String label;

	Category(String label) {
		this.label = label;
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public String getLabel() {
		return label;
	}

}
