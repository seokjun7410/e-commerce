package com.practice.ecommerce;

import com.practice.ecommerce.docsUtils.VirtualCategory;
import com.practice.ecommerce.docsUtils.VirtualProduct;
import com.practice.ecommerce.docsUtils.VirtualStoreOwner;
import com.practice.ecommerce.product.infra.adapter.CategoryRepository;
import com.practice.ecommerce.product.infra.adapter.ProductRepository;
import com.practice.ecommerce.user.infra.adapter.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CleanUp {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;

	@AfterEach
	public void tearDown() {
		productRepository.deleteAll();
		categoryRepository.deleteAll();
		userRepository.deleteAll();
		VirtualStoreOwner.clear();
		VirtualProduct.clear();
		VirtualCategory.clear();
	}
}
