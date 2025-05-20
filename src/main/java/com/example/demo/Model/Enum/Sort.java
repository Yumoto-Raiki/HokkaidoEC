package com.example.demo.Model.Enum;

public enum Sort {

	PRICE_ASC("price ASC"), PRICE_DESC("price DESC"), FAVORITE_DESC("order_amount DESC"), DATE_ASC(
			"added_date ASC"), DATE_DESC("added_date DESC");

	private final String sqlOrder;

	Sort(String sqlOrder) {
		this.sqlOrder = sqlOrder;
	}

	public String getSqlOrder() {
		return this.sqlOrder;
	}

}
