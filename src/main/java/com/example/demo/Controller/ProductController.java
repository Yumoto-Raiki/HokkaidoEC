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

		model.addAttribute("productsfavorite", iProduct.productfavoriteShow());
		model.addAttribute("productsnewdate", iProduct.newproductShow());

		// もどるHTMｌ
		return "";

	}

	//指定したカテゴリーの一覧を取得してHTMLに渡す
	@GetMapping("/serchProduct")
	public String serchProductListShow(Category category, Sort sort, Model model) {
		model.addAttribute("products", iProduct.searchProduct(category, sort));
		return "";
	}

	//商品の並び替えをしてHTMLに渡す
	//カテゴリー別も検索もSortがあるからいらないかも？
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

	//商品を検索して取得したデータをHTMLに渡す
	@GetMapping("/serchProductText")
	public String serchProduxtText(String text, Sort sort, Model model) {
		model.addAttribute("productstext", iProduct.searchProduct(text, sort));
		return "";
	}

	//商品詳細を一件取得してHTMLに渡す
	@GetMapping("/productShow")
	public String productShow(int productId, Model model) {
		model.addAttribute("puroduct", iProduct.productDetails(productId));
		return "";
	}

}
