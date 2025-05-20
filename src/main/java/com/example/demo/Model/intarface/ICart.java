package com.example.demo.Model.intarface;

import java.util.List;

import com.example.demo.Model.DTO.CartDTO;

public interface ICart {
	public void addProductToCart(int productId, int count, int userId);

	public void removeProductToCart(int cartId);

	public void updateCartInCount(int cartId, int count);

	public void clearCart(int userId);

	public List<CartDTO> getCart(int userId);

	public int sumPrice();
}
