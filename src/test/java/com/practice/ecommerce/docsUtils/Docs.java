package com.practice.ecommerce.docsUtils;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import io.restassured.filter.Filter;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.restassured.RestDocumentationFilter;
import org.springframework.util.ObjectUtils;

public abstract class Docs extends BaseDocs {

	private final Class<?> requestClass;

	public Docs(Class<?> requestClass) {
		this.requestClass = requestClass;
	}

	public abstract String getIdentifier();

	public abstract String getDescription();

	public abstract String getSummary();

	protected abstract ResponseDescription[] getResponseDescription();

	protected ResponseDescription[] defaultResponseDescriptions() {
		return new ResponseDescription[]{new ResponseDescription("status", "커스텀 상태 코드")};
	}

	private RestDocumentationFilter restDocumentationFilter = successFilter();

	public void setRestDocumentationFilter(
		RestDocumentationFilter restDocumentationFilter) {
		this.restDocumentationFilter = restDocumentationFilter;
	}

	public RestDocumentationFilter successFilter() {
		RequestFieldsSnippet requestFieldsSnippet = requestSpec();
		ResponseFieldsSnippet responseFieldsSnippet = responseSuccessSpec();
		if (requestFieldsSnippet != null) {
			if (responseFieldsSnippet != null) {
				return document(getIdentifier(),
					getDescription(),
					getSummary(), globalDefaultHeaderSpec(), requestFieldsSnippet,
					responseFieldsSnippet);
			} else {
				return document(getIdentifier(),
					getDescription(),
					getSummary(), globalDefaultHeaderSpec(), requestFieldsSnippet);
			}
		} else {
			if (responseFieldsSnippet != null) {
				return document(getIdentifier(),
					getDescription(),
					getSummary(), globalDefaultHeaderSpec(), responseFieldsSnippet);
			} else {
				return document(getIdentifier(),
					getDescription(),
					getSummary(), globalDefaultHeaderSpec());
			}
		}
	}


	public RestDocumentationFilter validationFilterInRestAssured(String identifier) {
		return document(identifier, getDescription(),
			getSummary(), globalDefaultHeaderSpec(), super.defaultExceptionResponseSpec());
	}

	public RestDocumentationResultHandler validationFilterInMockMvc(String identifier) {
		return MockMvcRestDocumentationWrapper.document(
			identifier,
			getDescription(),
			globalDefaultHeaderSpec(),
			super.defaultExceptionResponseSpec());
	}



	private RequestFieldsSnippet requestSpec() {
		if (this.requestClass == null) {
			return null;
		}

		ConstraintDescriptions userConstraints = new ConstraintDescriptions(this.requestClass);

		FieldDescriptor[] fieldDescriptors = getRequestFields().stream().map(each -> {
			List<String> loginIdConstrains = userConstraints.descriptionsForProperty(each);
			return fieldWithPath(each).description(convertForDescription(loginIdConstrains));
		}).toArray(FieldDescriptor[]::new);

		return requestFields(fieldDescriptors);
	}

	private ResponseFieldsSnippet responseSuccessSpec() {
		ResponseDescription[] responseDescription = getResponseDescription();
		if (ObjectUtils.isEmpty(responseDescription)) {
			return null;
		}

		FieldDescriptor[] fieldDescriptors = Arrays.stream(responseDescription).map(each -> {
			return fieldWithPath(each.responseFieldName).description(each.description);
		}).toArray(FieldDescriptor[]::new);

		return responseFields(fieldDescriptors);
	}

	private List<String> getRequestFields() {
		Field[] fields = this.requestClass.getDeclaredFields();
		return Arrays.stream(fields).map(Field::getName).collect(Collectors.toList());
	}

	public Filter getFilter() {
		return restDocumentationFilter;
	}

	public record ResponseDescription(String responseFieldName, String description) {

	}
}