package com.practice.ecommerce;

import com.practice.ecommerce.docsUtils.VirtualCategory;
import com.practice.ecommerce.docsUtils.VirtualProduct;
import com.practice.ecommerce.docsUtils.VirtualReview;
import com.practice.ecommerce.docsUtils.VirtualStoreOwner;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CleanUp {

	@Autowired
	private EntityManager entityManager;
	private List<String> tableNames= List.of(
		"product",
		"category",
		"member",
		"review"
	);


	@Transactional
	public void tearDown() {
		entityManager.flush();
		entityManager.clear();

		entityManager.createNativeQuery("SET foreign_key_checks = 0").executeUpdate();

		for (String tableName : tableNames) {
			entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
		}

		entityManager.createNativeQuery("SET foreign_key_checks = 1").executeUpdate();
		VirtualStoreOwner.clear();
		VirtualProduct.clear();
		VirtualCategory.clear();
		VirtualReview.clear();
	}
}
