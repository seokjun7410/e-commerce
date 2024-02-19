package com.practice.ecommerce.common.response;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
public class Status {


	/*
     * 2000 success
     * 3XXX input error
     * 4XXX server error
     * 5XXX database error
     */
	public static final int SUCCESS = 2000;


}
