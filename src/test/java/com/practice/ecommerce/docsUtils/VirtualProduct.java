package com.practice.ecommerce.docsUtils;

public class VirtualProduct {

	private static String name = "카고바지";
	private static String explanation = "이 카고바지는 ...";
	private static Integer price = 53000;
	private static Integer stock = 10;
	private static Long categoryId = 1L;

	public static void clear() {
		name = "카고바지";
		explanation = "이 카고바지는 ...";
		price = 53000;
		stock = 10;
		categoryId = 1L;
	}

	public static String getName() {
		return name;
	}

	public static String getExplanation() {
		return explanation;
	}

	public static Integer getPrice() {
		return price;
	}

	public static Integer getStock() {
		return stock;
	}

	public static Long getCategoryId() {
		return categoryId;
	}

	public static void setName(String name) {
		VirtualProduct.name = name;
	}

	public static void setExplanation(String explanation) {
		VirtualProduct.explanation = explanation;
	}

	public static void setPrice(Integer price) {
		VirtualProduct.price = price;
	}

	public static void setStock(Integer stock) {
		VirtualProduct.stock = stock;
	}

	public static void setCategoryId(Long categoryId) {
		VirtualProduct.categoryId = categoryId;
	}
}
