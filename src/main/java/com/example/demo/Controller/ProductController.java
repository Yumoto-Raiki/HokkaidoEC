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

	/**
	 * defaultのCategory
	 */
	private Category defaultShowCategory;
	/**
	 * defaultのSort
	 */
	private Sort defaultSort;

	public ProductController() {

		this.defaultShowCategory = Category.Fish;
		this.defaultSort = Sort.PRICE_ASC;

	}

	/**
	 * 条件なく商品一覧を取得しHTMLへ渡す
	 */
	@GetMapping("/productListShow")
	public String productListShow(Model model) {

		IProduct iProduct = new ProductDao();
		model.addAttribute("products", iProduct.searchProduct(
				defaultShowCategory, defaultSort));

		// もどるHTMｌ
		return "";

	}

	public String changeSort(String sort) {

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

	public String productShow(int productId) {

		return "";
	}

}
