package com.co.kr.exception;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(builderMethodName = "builder") //AllExceptionHandler등에서 빌더패턴으로생성가능하게끔함
public class ErrorResponse { //AllExceptionHandler에서 빌드
	
	private Integer result;  //결과에대한 코드값
	private String resultDesc; //요청결과에대한 설명
	private HttpStatus httpStatus; //응답 http 상태코드
	private String resDate;  //응답결과가 생성된 시간
	private String reqNo; //요청 번호

}
