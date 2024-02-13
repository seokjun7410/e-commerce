package com.practice.ecommerce.product.infra.adapter;

import com.practice.ecommerce.product.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>{

}
