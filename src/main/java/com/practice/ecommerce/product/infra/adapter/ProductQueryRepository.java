package com.practice.ecommerce.product.infra.adapter;

import com.practice.ecommerce.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductQueryRepository extends JpaRepository<Product, Long>,
	ProductQueryRepositoryCustom {

}
