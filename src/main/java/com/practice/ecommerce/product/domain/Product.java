package com.practice.ecommerce.product.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String explanation;
	@Column(nullable = false)
	private int price;
	@Column(nullable = false)
	private int stock;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	public Product(String name, String explanation, int price, int stock, Category category) {
		this.name = name;
		this.explanation = explanation;
		this.price = price;
		this.stock = stock;
		this.category = category;
	}

	public static Product create(String name, String explanation, int price, int stock,
		Category category) {

		return new Product(
			name,
			explanation,
			price,
			stock,
			category
		);
	}
}
