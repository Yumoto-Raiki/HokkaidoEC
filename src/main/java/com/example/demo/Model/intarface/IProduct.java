package com.example.demo.Model.intarface;

import java.util.List;

import com.example.demo.Model.DTO.ProductDTO;
import com.example.demo.Model.Enum.Category;
import com.example.demo.Model.Enum.Sort;

public interface IProduct {

	/**
	 * 引数を使用し検索する
	 * @param category
	 * @param sort
	 * @return
	 */
	List<ProductDTO> searchProduct(Category category, Sort sort);

}
