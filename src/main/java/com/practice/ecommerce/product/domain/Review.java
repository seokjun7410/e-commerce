package com.practice.ecommerce.product.domain;

import com.practice.ecommerce.BaseEntity;
import com.practice.ecommerce.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "parent_review_id")
	private Review parentReview;

	@OneToMany(mappedBy = "parentReview")
	private Set<Review> subReview;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private User user;

	@Column(nullable = false)
	private String content;

	@Column(nullable = true)
	private Double star;


	private Review(Product product, User user, Review parentReview, String content, Double star) {
		this.product = product;
		this.user = user;
		this.parentReview = parentReview;
		this.content = content;
		this.star = star;
	}

	public static Review createProductReview(Product product, User user, String content,
		Double star) {
		Review review = new Review(product, user, null, content, star);
		product.addReview(review);
		return review;
	}

	public static Review createSubReview(Review parentReview, User user, String content) {
		Review review = new Review(null, user, parentReview, content, null);
		parentReview.addSubReview(review);
		return review;
	}

	private void addSubReview(Review review) {
		this.subReview.add(review);
	}

	public boolean isOwner(Long accountId) {
		return this.user.getId().equals(accountId);
	}

	public void updateContent(String content) {
		this.content = content;
	}
}
