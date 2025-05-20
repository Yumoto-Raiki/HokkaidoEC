package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Model.Dao.ProductDao;
import com.example.demo.Model.Enum.Category;
import com.example.demo.Model.Enum.Sort;
import com.example.demo.Model.intarface.IProduct;

@Controller
public class ProductController {

	IProduct iProduct;

	/**
	 * defaultのCategory
	 */
	private Category defaultShowCategory;
	/**
	 * defaultのSort
	 */
	private Sort defaultSort;

	public ProductController() {

		this.defaultShowCategory = Category.魚介;
		this.defaultSort = Sort.PRICE_ASC;
		iProduct = new ProductDao();

	}

	/*private bool enumChange() {
		
	}*/

	/**
	 * 条件なく商品一覧を取得しHTMLへ渡す
	 */
	@GetMapping("/productListShow")
	public String productListShow(Model model) {

		// 人気順の商品リスト
		model.addAttribute("productsFavorite", iProduct.productfavoriteShow());
		System.out.println("人気リスト");
		// 新着順の商品リスト
		model.addAttribute("productsNewdate", iProduct.newproductShow());
		System.out.println("新着リスト");

		// もどるHTMｌ
		return "home.html";

	}

	@GetMapping("/serchProduct")
	public String serchProductListShow(Category category, Sort sort, Model model) {
		model.addAttribute("products", iProduct.searchProduct(category, sort));
		return "";
	}

	@GetMapping("/changeSort")
	public String changeSort(String sort, Model model) {

		try {
			// Enumのなかみと一致するときEnumに変換
			Sort day = Sort.valueOf(sort);
		} catch (IllegalArgumentException e) {
			System.out.println("指定された文字列は有効なDayではありません: " + sort);
			return "";
		}
		// 取得したEnumで検索

		return "";
	}

	@GetMapping("/productShow")
	public String productShow(int productId, Model model) {
		iProduct.productDetails(productId);
		return "";
	}

}
