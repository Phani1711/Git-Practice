package com.productlist;

import org.testng.annotations.Test;

import com.product.Base;
import com.product.SearchProduct;

public class ProductList extends Base {
	SearchProduct searchProduct = new SearchProduct();
	
	@Test
	public void enterProduct() throws InterruptedException {
		searchProduct.search_Products();
		searchProduct.list_searchproducts();
	}
}
