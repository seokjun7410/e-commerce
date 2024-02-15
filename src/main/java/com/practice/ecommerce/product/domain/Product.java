package com.practice.ecommerce.product.domain;

import com.practice.ecommerce.BaseEntity;
import com.practice.ecommerce.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_owner_id")
	private User storeOwner;

	@OneToMany(mappedBy = "product")
	private Set<Review> reviews = new HashSet<>();

	public Product(User storeOwner,String name, String explanation, int price, int stock, Category category) {
		this.storeOwner = storeOwner;
		this.name = name;
		this.explanation = explanation;
		this.price = price;
		this.stock = stock;
		this.category = category;
	}

	public static Product create(User storeOwner,String name, String explanation, int price, int stock,
		Category category) {

		return new Product(
			storeOwner,
			name,
			explanation,
			price,
			stock,
			category
		);
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}
}
