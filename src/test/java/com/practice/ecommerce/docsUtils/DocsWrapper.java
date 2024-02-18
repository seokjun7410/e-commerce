package com.practice.ecommerce.docsUtils;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

import com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper;
import com.practice.ecommerce.config.DocsDescription;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.headers.RequestHeadersSnippet;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.request.PathParametersSnippet;
import org.springframework.restdocs.request.QueryParametersSnippet;
import org.springframework.restdocs.restassured.RestDocumentationFilter;

public class DocsWrapper {

	public RequestFieldsSnippet BuildRequestFieldSnippet(Class<?> requestClass) {
		ConstraintDescriptions userConstraints = new ConstraintDescriptions(requestClass);
		Field[] fields = requestClass.getDeclaredFields();
		FieldDescriptor[] fieldDescriptors = Arrays.stream(fields)
			.map(each -> {
				List<String> constraints = userConstraints.descriptionsForProperty(
					each.getName());
				DocsDescription descriptionAnnotation = each.getAnnotation(DocsDescription.class);
				if (descriptionAnnotation != null) {
					if (!StringUtils.isBlank(descriptionAnnotation.value())) {
						constraints.add(descriptionAnnotation.value());
					}
					if (descriptionAnnotation.nullable()) {
						return fieldWithPath(each.getName()).description(
							descriptionFormatting(constraints)).optional();
					}
				}
				Class<?> type = each.getType();
				if (type.isEnum()) {
					Object[] enumConstants = type.getEnumConstants();
					for (Object enumConstant : enumConstants) {
						constraints.add(((Enum) enumConstant).name());
					}
				}
				return fieldWithPath(each.getName()).description(
					descriptionFormatting(constraints));
			}).toArray(FieldDescriptor[]::new);

		return requestFields(fieldDescriptors);
	}


	public ResponseFieldsSnippet buildResponseFieldSnippet(Class<?> responseClass) {
		List<FieldDescriptor> responseDescriptions = getFieldDescriptors("", responseClass);
		return responseFields(responseDescriptions.toArray(new FieldDescriptor[0]));
	}

	public ResponseFieldsSnippet buildListResponseFieldSnippet(Class<?> responseClass) {
		List<FieldDescriptor> responseDescriptions = getListFieldDescriptors("", responseClass);
		return responseFields(responseDescriptions.toArray(FieldDescriptor[]::new));
	}

	private List<FieldDescriptor> getFieldDescriptors(String prefix, Class<?> responseClass) {


		Field[] fields = responseClass.getDeclaredFields();
		List<FieldDescriptor> responseDescriptions = new ArrayList<>();
		responseDescriptions.add(fieldWithPath("status").description("커스텀 상태 코드"));
		responseDescriptions.add(fieldWithPath("responseMessage").description("커스텀 상태 코드"));
		for (Field field : fields) {
			DocsDescription descriptionAnnotation = field.getAnnotation(DocsDescription.class);
			String fieldName = "";
			String descriptionValue = "";

			Class<?> fieldType = field.getType();
			Package packagePath = field.getType().getPackage();
			if (packagePath == null || packagePath.getName().startsWith("java")) {
				fieldName = prefix + field.getName();
				if (descriptionAnnotation != null) {
					descriptionValue = descriptionAnnotation.value();
				}
				responseDescriptions.add(fieldWithPath("response."+fieldName).description(descriptionValue));
			} else {
				List<FieldDescriptor> fieldDescriptors = getFieldDescriptors(field.getName() + ".",
					fieldType);
				responseDescriptions.addAll(fieldDescriptors);
			}
		}
		return responseDescriptions;
	}

	private List<FieldDescriptor> getListFieldDescriptors(String prefix, Class<?> responseClass) {
		Field[] fields = responseClass.getDeclaredFields();
		List<FieldDescriptor> responseDescriptions = new ArrayList<>();

		for (Field field : fields) {
			responseDescriptions.add(fieldWithPath("status").description("커스텀 상태 코드"));
			responseDescriptions.add(fieldWithPath("responseMessage").description("응답 메시지"));

			DocsDescription descriptionAnnotation = field.getAnnotation(DocsDescription.class);
			String fieldName = "";
			String descriptionValue = "";

			Class<?> fieldType = field.getType();
			Package packagePath = field.getType().getPackage();

			String typeName = field.getType().getTypeName();
			String packagePathName = null;
			fieldName = prefix + field.getName();
			if (packagePath != null) {
				packagePathName = packagePath.getName();
			}
			if (typeName.contains("List")) {
				packagePathName = getRootPackage(field);
				fieldType = getRootFieldType(field);
			}
			if (packagePath == null || (packagePathName.startsWith("java"))) {
				fieldName = "[]." + fieldName;
				if (descriptionAnnotation != null) {
					descriptionValue = descriptionAnnotation.value();
				}
				if (descriptionAnnotation != null) {
					if (descriptionAnnotation.nullable()) {
						responseDescriptions.add(
							fieldWithPath(fieldName).optional().description(descriptionValue)
						);
					}else{
						responseDescriptions.add(
							fieldWithPath(fieldName).description(descriptionValue));
					}
				}else {
					responseDescriptions.add(
						fieldWithPath(fieldName).description(descriptionValue));
				}

			} else {
				List<FieldDescriptor> fieldDescriptors = getListFieldDescriptors(
					field.getName() + "[].", fieldType);
				responseDescriptions.addAll(fieldDescriptors);
			}
		}
		return responseDescriptions;
	}

	private Class<?> getRootFieldType(Field listField) {

		// 필드가 리스트 타입인지 확인합니다.
		if (List.class.isAssignableFrom(listField.getType())) {
			// 리스트 타입의 제네릭 타입을 얻어옵니다.
			Type genericType = listField.getGenericType();

			if (genericType instanceof ParameterizedType) {
				// 제네릭 타입이 매개변수화된 타입인 경우
				ParameterizedType parameterizedType = (ParameterizedType) genericType;

				// 제네릭 타입의 실제 타입 인자를 가져옵니다.
				Type[] typeArguments = parameterizedType.getActualTypeArguments();

				for (Type typeArgument : typeArguments) {
					if (typeArgument instanceof Class) {
						// 실제 타입 인자가 클래스인 경우
						return (Class<?>) typeArgument;
					}
				}
			}
		}
		return null;
	}

	private String getRootPackage(Field listField) {
		if (List.class.isAssignableFrom(listField.getType())) {
			// 리스트 타입의 제네릭 타입을 얻어옵니다.
			Type genericType = listField.getGenericType();

			if (genericType instanceof ParameterizedType) {
				// 제네릭 타입이 매개변수화된 타입인 경우
				ParameterizedType parameterizedType = (ParameterizedType) genericType;

				// 제네릭 타입의 실제 타입 인자를 가져옵니다.
				Type[] typeArguments = parameterizedType.getActualTypeArguments();

				for (Type typeArgument : typeArguments) {
					// 실제 타입 인자의 패키지 정보를 출력합니다.
					return ((Class<?>) typeArgument).getPackage().getName();
				}
			}
		}
		return null;
	}

	protected String descriptionFormatting(List<String> constraints) {
		if (constraints.isEmpty()) {
			return "";
		}
		if (constraints.size() == 1) {
			return "[" + constraints.get(0) + "]";
		}
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < constraints.size() - 1; i++) {
			stringBuilder.append("[").append(constraints.get(i)).append("], ");
		}

		stringBuilder.append("[").append(constraints.get(constraints.size() - 1)).append("]");

		return stringBuilder.toString();
	}

	public RestDocumentationFilter buildRestDocumentationFilter(
		String identifier,
		String description,
		RequestHeadersSnippet requestHeadersSnippet,
		String summary,
		RequestFieldsSnippet requestFieldsSnippet,
		ResponseFieldsSnippet responseFieldsSnippet,
		QueryParametersSnippet queryParametersSnippet,
		PathParametersSnippet pathParametersSnippet) {

		if (requestFieldsSnippet != null && responseFieldsSnippet != null) {
			if (queryParametersSnippet != null && pathParametersSnippet != null) {
				return RestAssuredRestDocumentationWrapper.document(identifier,
					description,
					summary,
					requestHeadersSnippet,
					requestFieldsSnippet,
					responseFieldsSnippet,
					queryParametersSnippet,
					pathParametersSnippet
				);
			} else if (queryParametersSnippet != null) {
				return RestAssuredRestDocumentationWrapper.document(identifier,
					description,
					summary,
					requestHeadersSnippet,
					requestFieldsSnippet,
					responseFieldsSnippet,
					queryParametersSnippet
				);
			} else if (pathParametersSnippet != null) {
				return RestAssuredRestDocumentationWrapper.document(identifier,
					description,
					summary,
					requestHeadersSnippet,
					requestFieldsSnippet,
					responseFieldsSnippet,
					pathParametersSnippet
				);
			} else {
				return RestAssuredRestDocumentationWrapper.document(identifier,
					description,
					summary,
					requestHeadersSnippet,
					requestFieldsSnippet,
					responseFieldsSnippet
				);
			}
		} else if (requestFieldsSnippet != null) {
			if (queryParametersSnippet != null && pathParametersSnippet != null) {
				return RestAssuredRestDocumentationWrapper.document(identifier,
					description,
					summary,
					requestHeadersSnippet,
					requestFieldsSnippet,
					queryParametersSnippet,
					pathParametersSnippet
				);
			} else if (queryParametersSnippet != null) {
				return RestAssuredRestDocumentationWrapper.document(identifier,
					description,
					summary,
					requestHeadersSnippet,
					requestFieldsSnippet,
					queryParametersSnippet
				);
			} else if (pathParametersSnippet != null) {
				return RestAssuredRestDocumentationWrapper.document(identifier,
					description,
					summary,
					requestHeadersSnippet,
					requestFieldsSnippet,
					pathParametersSnippet
				);
			} else {
				return RestAssuredRestDocumentationWrapper.document(identifier,
					description,
					summary,
					requestHeadersSnippet,
					requestFieldsSnippet
				);
			}
		} else if (responseFieldsSnippet != null) {
			if (queryParametersSnippet != null && pathParametersSnippet != null) {
				return RestAssuredRestDocumentationWrapper.document(identifier,
					description,
					summary,
					requestHeadersSnippet,
					responseFieldsSnippet,
					queryParametersSnippet,
					pathParametersSnippet
				);
			} else if (queryParametersSnippet != null) {
				return RestAssuredRestDocumentationWrapper.document(identifier,
					description,
					summary,
					requestHeadersSnippet,
					responseFieldsSnippet,
					queryParametersSnippet
				);
			} else if (pathParametersSnippet != null) {
				return RestAssuredRestDocumentationWrapper.document(identifier,
					description,
					summary,
					requestHeadersSnippet,
					responseFieldsSnippet,
					pathParametersSnippet
				);
			} else {
				return RestAssuredRestDocumentationWrapper.document(identifier,
					description,
					summary,
					requestHeadersSnippet,
					responseFieldsSnippet
				);
			}
		} else {
			if (queryParametersSnippet != null && pathParametersSnippet != null) {
				return RestAssuredRestDocumentationWrapper.document(identifier,
					description,
					summary,
					queryParametersSnippet,
					pathParametersSnippet
				);
			} else if (queryParametersSnippet != null) {
				return RestAssuredRestDocumentationWrapper.document(identifier,
					description,
					summary,
					queryParametersSnippet
				);
			} else if (pathParametersSnippet != null) {
				return RestAssuredRestDocumentationWrapper.document(identifier,
					description,
					summary,
					pathParametersSnippet
				);
			} else {
				return RestAssuredRestDocumentationWrapper.document(identifier,
					description,
					summary
				);
			}
		}
	}
}
