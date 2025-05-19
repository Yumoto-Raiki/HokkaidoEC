package com.example.demo.Model.intarface;

import java.util.List;

import com.example.demo.Model.DTO.FavoriteDTO;

public interface IFavorite {
	public void addProductToFavorite(int productId, int userId);

	public void removeProductToFavorite(int favoriteId);

	public void clearFavorite(int userId);

	public List<FavoriteDTO> getFavorite(int userId);
}
