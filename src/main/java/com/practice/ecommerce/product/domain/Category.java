package com.practice.ecommerce.product.domain;

import com.practice.ecommerce.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	String name;

	private Category(String name) {
		this.name = name;
	}

	public static Category create(String name) {
		return new Category(name);
	}

	public void update(String name) {
		this.name = name;
	}
}
