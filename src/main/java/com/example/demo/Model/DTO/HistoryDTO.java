package com.example.demo.Model.DTO;

import java.time.LocalDateTime;

public class HistoryDTO {
	private int userId;
	private int productId;
	private int count;
	private LocalDateTime buydate;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public LocalDateTime getBuydate() {
		return buydate;
	}

	public void setBuydate(LocalDateTime buydate) {
		this.buydate = buydate;
	}

}
