package com.practice.ecommerce.product.infra.adapter;

import com.practice.ecommerce.product.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
