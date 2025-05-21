package com.example.demo.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Model.DTO.ProductDTO;
import com.example.demo.Model.Dao.ProductDao;
import com.example.demo.Model.Enum.Category;
import com.example.demo.Model.Enum.Sort;
import com.example.demo.Model.intarface.IProduct;

@Controller
public class ProductController {

	IProduct iProduct;

	public ProductController() {

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
		//FIXME: なぜかビューでcategory.labelでエラーになってしまう
		// model.addAttribute("categories", Category.values());

		// もどるHTMｌ
		return "home";

	}

	//指定したカテゴリーの一覧を取得してHTMLに渡す
	@GetMapping("/searchProduct")
	public String serchProductListShow(@RequestParam("category") Category category, @RequestParam("sort") Sort sort,
			Model model) {
		List<ProductDTO> products = iProduct.searchProduct(category, sort);
		model.addAttribute("products", products);
		model.addAttribute("category", category);
		return "search";
	}

	//	//商品の並び替えをしてHTMLに渡す
	//	//カテゴリー別も検索もSortがあるからいらないかも？
	//	@GetMapping("/changeSort")
	//	public String changeSort(@RequestParam("productDTO") List<ProductDTO> productDTO, @RequestParam("sort") Sort sort,
	//			Model model) {
	//
	//		// 取得したEnumで検索
	//
	//		return "";
	//	}

	//商品を検索して取得したデータをHTMLに渡す
	@GetMapping("/serchProductText")
	public String serchProduxtText(String text, Sort sort, Model model) {
		model.addAttribute("productstext", iProduct.searchProduct(text, sort));
		return "product"
				+ "";
	}

	//商品詳細を一件取得してHTMLに渡す
	@GetMapping("/productShow")
	public String productShow(int productId, Model model) {
		model.addAttribute("product", iProduct.productDetails(productId));
		return "product";
	}

}
