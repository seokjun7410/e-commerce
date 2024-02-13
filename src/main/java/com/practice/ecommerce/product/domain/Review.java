package com.practice.ecommerce.product.domain;

import com.practice.ecommerce.user.domain.User;
import jakarta.persistence.Entity;
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
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "subReview_id")
	private Review subReview;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private User user;

	private String content;

	private Double star;


	private Review(Product product, User user, Review subReview, String content, Double star) {
		this.product = product;
		this.user = user;
		this.subReview = subReview;
		this.content = content;
		this.star = star;
	}

	public static Review createProductReview(Product product, User user, String content,
		Double star) {
		return new Review(product, user, null, content, star);
	}

	public static Review createSubReview(Review parentReview, User user, String content) {
		return new Review(null, user, parentReview, content, null);
	}

	public boolean isOwner(Long accountId) {
		return this.user.getId().equals(accountId);
	}

	public void updateContent(String content) {
		this.content = content;
	}
}
