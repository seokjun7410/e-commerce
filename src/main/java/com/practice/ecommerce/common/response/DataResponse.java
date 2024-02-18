package com.practice.ecommerce.common.response;

import com.practice.ecommerce.common.excpetion.ErrorCode;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DataResponse<T> {

	private int status;
	private String responseMessage;
	private T response;

	public DataResponse(final int status, final String responseMessage, T data) {
		this.status = status;
		this.responseMessage = responseMessage;
		this.response = data;
	}

	public static <T> DataResponse<T> response(final int status, T data) {
		return DataResponse.<T>builder()
			.status(status)
			.responseMessage("SUCCESS")
			.response(data)
			.build();
	}

	public static <T> DataResponse<T> errorResponse(final ErrorCode errorCode) {
		return DataResponse.<T>builder()
			.status(errorCode.getHttpStatus().value())
			.responseMessage(errorCode.name())
			.response((T) errorCode.getDetail())
			.build();
	}


}
