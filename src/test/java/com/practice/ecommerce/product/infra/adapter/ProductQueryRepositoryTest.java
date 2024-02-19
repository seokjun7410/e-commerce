package com.practice.ecommerce.product.infra.adapter;


import static org.assertj.core.api.Assertions.assertThat;

import com.practice.ecommerce.RepositoryTest;
import com.practice.ecommerce.docsUtils.VirtualCategory;
import com.practice.ecommerce.docsUtils.VirtualProduct;
import com.practice.ecommerce.docsUtils.VirtualStoreOwner;
import com.practice.ecommerce.product.domain.Category;
import com.practice.ecommerce.product.domain.Product;
import com.practice.ecommerce.product.infra.web.dto.SortStatus;
import com.practice.ecommerce.product.infra.web.dto.ProductResponse;
import com.practice.ecommerce.product.infra.web.dto.ProductSearchRequest;
import com.practice.ecommerce.user.domain.User;
import com.practice.ecommerce.user.domain.vo.Address;
import com.practice.ecommerce.user.domain.vo.LoginId;
import com.practice.ecommerce.user.domain.vo.Password;
import com.practice.ecommerce.user.infra.adapter.UserRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("상품 쿼리 레포지토리 커스텀 테스트")
public class ProductQueryRepositoryTest extends RepositoryTest {

	@Autowired
	private ProductQueryRepository productQueryRepository;

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private UserRepository userRepository;

	@Test
	void 상품목록_조회_오래된순으로_정렬할_수_있다() {
		//given
		VirtualProduct.setName("첫번째"); createProduct();
		VirtualProduct.setName("두번째"); createProduct();
		VirtualProduct.setName("세번째"); createProduct();
		SortStatus sortCondition = SortStatus.OLDEST;
		ProductSearchRequest request = createSortingRequest(sortCondition);

		//when
		List<ProductResponse> products = productQueryRepository.getProducts(request);

		//then
		assertThat(products.get(0).name()).isEqualTo("첫번째");
		assertThat(products.get(1).name()).isEqualTo("두번째");
		assertThat(products.get(2).name()).isEqualTo("세번째");
	}

	@Test
	void 상품목록_조회_최신순으로_정렬할_수_있다() {
		//given
		VirtualProduct.setName("첫번째"); createProduct();
		VirtualProduct.setName("두번째"); createProduct();
		VirtualProduct.setName("세번째"); createProduct();
		SortStatus sortCondition = SortStatus.NEWEST;
		ProductSearchRequest request = createSortingRequest(sortCondition);

		//when
		List<ProductResponse> products = productQueryRepository.getProducts(request);

		//then
		assertThat(products.get(0).name()).isEqualTo("세번째");
		assertThat(products.get(1).name()).isEqualTo("두번째");
		assertThat(products.get(2).name()).isEqualTo("첫번째");
	}

	@Test
	void 상품목록_조회_가격낮은순으로_정렬할_수_있다() {
		//given
		VirtualProduct.setPrice(1000); createProduct();
		VirtualProduct.setPrice(2000); createProduct();
		VirtualProduct.setPrice(3000); createProduct();
		SortStatus sortCondition = SortStatus.PRICE_LOW;
		ProductSearchRequest request = createSortingRequest(sortCondition);

		//when
		List<ProductResponse> products = productQueryRepository.getProducts(request);

		//then
		assertThat(products.get(0).price()).isEqualTo(1000);
		assertThat(products.get(1).price()).isEqualTo(2000);
		assertThat(products.get(2).price()).isEqualTo(3000);
	}

	@Test
	void 상품목록_조회_가격높은순으로_정렬할_수_있다() {
		//given
		VirtualProduct.setPrice(1000); createProduct();
		VirtualProduct.setPrice(2000); createProduct();
		VirtualProduct.setPrice(3000); createProduct();
		SortStatus sortCondition = SortStatus.PRICE_HIGH;
		ProductSearchRequest request = createSortingRequest(sortCondition);

		//when
		List<ProductResponse> products = productQueryRepository.getProducts(request);

		//then
		assertThat(products.get(0).price()).isEqualTo(3000);
		assertThat(products.get(1).price()).isEqualTo(2000);
		assertThat(products.get(2).price()).isEqualTo(1000);
	}

	@Test
	void 상품목록_조회_storeOwner_이름으로_검색할_수_있다() {
		//given
		VirtualStoreOwner.setNickName("첫번째"); createProduct();
		VirtualStoreOwner.setNickName("두번째%"); createProduct();
		VirtualStoreOwner.setNickName("세번째"); createProduct();
		ProductSearchRequest request = createStoreOwnerNameRequest("두번째");

		//when
		List<ProductResponse> products = productQueryRepository.getProducts(request);

		//then
		assertThat(products).hasSize(1);
	}

	@Test
	void 상품목록_조회_CategoryId로_검색할_수_있다() {
		//given
		VirtualStoreOwner.setNickName("첫번째"); createProduct();
		VirtualStoreOwner.setNickName("두번째"); createProduct();
		VirtualStoreOwner.setNickName("세번째"); createProduct();
		long categoryId = 2L;
		ProductSearchRequest request = createCategoryIdRequest(categoryId);

		//when
		List<ProductResponse> products = productQueryRepository.getProducts(request);

		//then
		assertThat(products).hasSize(1);
	}

	@Test
	void 상품목록_조회_ProductName_으로_검색할_수_있다() {
		//given
		VirtualProduct.setName("첫번째"); createProduct();
		VirtualProduct.setName("두번째%"); createProduct();
		VirtualProduct.setName("세번째"); createProduct();
		ProductSearchRequest request = createProductNameRequest("두번째");

		//when
		List<ProductResponse> products = productQueryRepository.getProducts(request);

		//then
		assertThat(products).hasSize(1);
	}

	private ProductSearchRequest createStoreOwnerNameRequest(String storeOwnerName) {
		return new ProductSearchRequest(
			null,
			null,
			storeOwnerName,
			SortStatus.NEWEST
		);
	}

	private ProductSearchRequest createCategoryIdRequest(Long categoryId) {
		return new ProductSearchRequest(
			null,
			categoryId,
			null,
			SortStatus.NEWEST
		);
	}

	private ProductSearchRequest createProductNameRequest(String productName) {
		return new ProductSearchRequest(
			productName,
			null,
			null,
			SortStatus.NEWEST
		);
	}

	private ProductSearchRequest createSortingRequest(SortStatus sortCondition) {
		return new ProductSearchRequest(
			null,
			null,
			null,
			sortCondition
		);
	}

	private void createProduct() {
		User storeOwner = User.createStoreOwner(
			LoginId.create(VirtualStoreOwner.getLoginId()),
			Password.create(VirtualStoreOwner.getPassword()),
			Address.create(VirtualStoreOwner.getAddress(), VirtualStoreOwner.getAddress()),
			VirtualStoreOwner.getNickName()
		);
		userRepository.save(storeOwner);
		Category category = Category.create(VirtualCategory.getName());
		categoryRepository.save(category);
		productRepository.save(Product.create(
			storeOwner,
			VirtualProduct.getName(),
			VirtualProduct.getExplanation(),
			VirtualProduct.getPrice(),
			VirtualProduct.getStock(),
			category
		));
	}
}