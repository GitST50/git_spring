package com.co.kr.exception;

import java.util.Map;

import com.co.kr.code.Code;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InternalException extends RuntimeException{
	private static final long serialVersionUID = -7457312605485052136L;
	//serialVersionUID == '분산처리 환경에서 유일한 Unique 클래스라는 것을 증명하기 위한 신분증'
	
	private Code code; //Code.java의 Code의 객체,예외코드
	private Map<String, Object> map; //map:키-값으로 데이터를 저장하며 키는 중복불가능, 키를 통해 값에 접근
	private String errorMsg; //에러메시지
	
	public InternalException(Code code) {
		this.code = code; //code필드를 초기화하는 생성자
	}
	
	public InternalException(Code code, Map<String, Object> map) {
		this(code);  //code,map필드를 초기화하는 ..
		this.map = map;
	}
	
	public InternalException(Code code,  String errorMsg) {
		this(code); //code,..
		this.errorMsg = errorMsg;
	}
	
	public InternalException(Code code, Map<String, Object> map, String errorMsg) {
		this(code); //..
		this.map = map;
		this.errorMsg = errorMsg;
	}
	
	//생성자
	public static InternalException fire(Code code) { //code필드를 초기화한 InternalException 객체를 리턴하는 메소드
		return new InternalException(code);
	}
	
	public static InternalException fire(Code code, Map<String, Object> map) { //code, Map필드를 초기화한 ..
		return new InternalException(code, map);
	}
	
	public static InternalException fire(Code code, String errorMsg) { // ..
		return new InternalException(code, errorMsg);
	}
	
	public static InternalException fire(Code code, Map<String, Object> map, String errorMsg) { //..
		return new InternalException(code, map, errorMsg);
	}

}
