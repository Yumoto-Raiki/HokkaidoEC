package com.example.demo.Model.DTO;

import java.awt.Image;

import com.example.demo.Model.Enum.Category;

public class ProductDTO {

	private int productId;
	private String name;
	private int price;
	private int stock;
	private Image image;
	private String productionarea;
	private String text;
	private Category category;

	/**
	 * @return productId
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * @param productId セットする productId
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price セットする price
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock セットする stock
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * @return image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image セットする image
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * @return productionarea
	 */
	public String getProductionarea() {
		return productionarea;
	}

	/**
	 * @param productionarea セットする productionarea
	 */
	public void setProductionarea(String productionarea) {
		this.productionarea = productionarea;
	}

	/**
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text セットする text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category セットする category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

}
