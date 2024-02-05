package com.practice.ecommerce.product.infra.adapter;

import com.practice.ecommerce.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
