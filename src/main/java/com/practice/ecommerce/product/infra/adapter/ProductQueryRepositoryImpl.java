package com.practice.ecommerce.product.infra.adapter;

import static com.practice.ecommerce.product.domain.QProduct.product;

import com.practice.ecommerce.product.infra.web.dto.CategoryDTO.SortStatus;
import com.practice.ecommerce.product.infra.web.dto.ProductResponse;
import com.practice.ecommerce.product.infra.web.dto.ProductSearchRequest;
import com.practice.ecommerce.product.infra.web.dto.QProductResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public class ProductQueryRepositoryImpl implements ProductQueryRepositoryCustom{

	private final JPAQueryFactory queryFactory;
	public ProductQueryRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}
	private final QProductResponse select = new QProductResponse(
		product.id,
		product.name,
		product.price,
		product.category.name
	);
	@Override
	public List<ProductResponse> getProducts(ProductSearchRequest request) {

		SortStatus sortStatus = request.sortStatus();
		return queryFactory
			.select(select)
			.from(product)
			.leftJoin(product.category)
			.where(
				productNameEquals(request.productName()),
				storeOwnerEquals(request.storeOwnerName()),
				categoryIdEquals(request.categoryId())
			)
			.orderBy(sortStatus.getOrderSpecifier())
			.fetch();

	}

	private BooleanExpression categoryIdEquals(Long categoryId) {
		if(ObjectUtils.isEmpty(categoryId))
			return null;
		return product.category.id.eq(categoryId);
	}

	private Predicate productNameEquals(String productName) {
		if(StringUtils.isEmpty(productName))
			return null;
		return product.name.contains(productName);
	}

	private static BooleanBuilder storeOwnerEquals(String storeOwnerName) {
		if(StringUtils.isEmpty(storeOwnerName))
			return null;
		BooleanBuilder builder = new BooleanBuilder();
		return builder.or(product.storeOwner.name.contains(storeOwnerName));
	}
}
