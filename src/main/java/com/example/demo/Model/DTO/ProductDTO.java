package com.example.demo.Model.DTO;

public class ProductDTO {

	private int productId;
	private String name;
	private int price;
	private int stock;
	private String productionarea;
	private String text;
	private String category;
	private int photo_id;
	private String imageURL;

	private String volume;
	private String addeddate;
	private String seller;
	private int orderamount;

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
	public String getCategory() {
		return category;
	}

	/**
	 * @param category セットする category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getAddeddate() {
		return addeddate;
	}

	public void setAddeddate(String addeddate) {
		this.addeddate = addeddate;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public int getOrderamount() {
		return orderamount;
	}

	public void setOrderamount(int orderamount) {
		this.orderamount = orderamount;
	}

	@Override
	public String toString() {
		return "Product[id=" + productId + ",nama=" + name + ",price=" + price + ",stock=" + stock + ",area="
				+ productionarea + ",text=" + text + ",category="
				+ category + ",volume=" + volume + ",date=" + addeddate + ",seller=" + seller + ",amount="
				+ orderamount + "\n";
	}

	public int getPhoto_id() {
		return photo_id;
	}

	public void setPhoto_id(int photo_id) {
		this.photo_id = photo_id;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
}
