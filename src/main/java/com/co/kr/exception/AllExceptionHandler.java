package com.co.kr.exception;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.servlet.ModelAndView;

import com.co.kr.util.CommonUtils;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@ControllerAdvice //해당 클래스가 Controller에서 예외가 발생했을때 처리하는 클래스임을 나타냄
public class AllExceptionHandler { //예외처리
	
	//request error
	//MissingServlet..Exception: Http요청파라미터가 누락되었을때 발생하는 예외
	@ExceptionHandler(MissingServletRequestParameterException.class) //해당 메소드에서 처리할 예외를 설정
	@ResponseStatus(HttpStatus.BAD_REQUEST)  //@ResponseStatus:예외가 발생했을때 응답할 Http상태 코드를 지정
	public HttpEntity<ErrorResponse> handlerBindingResultException(RequestException exception){
		//http요청과 응답을 캡슐화한 객체: ErrorResponse타입의 바디를 포함하는 HttpEntity객체
		
		//catch exception
		if(exception.getException() != null) {
			Exception ex = exception.getException();
			StackTraceElement [] steArr = ex.getStackTrace();
			for(StackTraceElement ste : steArr) {
				System.out.println(ste.toString());
			}
		}
		//response 담기                       //@Builder 어노테이션을 사용하여 생성자 메소드를 자동으로 생성해주는 기능
		                                    //ErrorResponse객체를 생성하기위한 빌더 클래스를 생성하며 필요한 필드값을넣은후 build()를 호출하면 ErrorResponse객체가 생성됨
		ErrorResponse errRes = ErrorResponse.builder()  //에러리스폰스 객체를 생성하며 ResponseEntity객체에 담아서 반환
				.result(exception.getCode().getResult())  //필드값들
				.resultDesc(exception.getCode().getResultDesc())
				.resDate(CommonUtils.currentTime())
				.reqNo(exception.getReqNo())
				.httpStatus(exception.getHttpStatus())
				.build(); //ErrorResponse 객체 생성
		
		return new ResponseEntity<ErrorResponse>(errRes, errRes.getHttpStatus()); //생성된 ErrorResponse객체를 반환
		
		
		
	}
	
	//db error
	@ExceptionHandler(InternalServerError.class) 
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)           //InternalException.java에서 가져옴
	public HttpEntity<ErrorResponse> handelerInternalServerError(InternalException exception){
		//서버 내부 오류가 발생했을때 처리
		System.out.println("==========Internal Error========="+ exception.getMessage());
		ErrorResponse errRes = ErrorResponse.builder()
				.result(exception.getCode().getResult())
				.resultDesc(exception.getCode().getResultDesc())
				.resDate(CommonUtils.currentTime())
				.reqNo(CommonUtils.currentTime())
				.build();
		return new ResponseEntity<ErrorResponse>(errRes,HttpStatus.INTERNAL_SERVER_ERROR);
		
		
		//에러리스폰스객체를 생성한후 ResponseEntity객체에 담아서 리턴
	}
	
	
	//error page  //모든예외처리
	@ExceptionHandler(Exception.class)
	public ModelAndView commonException(Exception e) {
		e.getStackTrace(); //예외 객체의 스택 트레이스를 get
		ModelAndView mv = new ModelAndView(); //ModelAndView객체를 생성
		mv.addObject("exception", e.getStackTrace()); //ModelAndView객체에 exception이라는 이름으로 예외객체의 스택트레이스를 추가
		mv.setViewName("commons/commonErr.html"); //ModelAndView객체에 commons/commonErr.html 이라는 view 설정
		return mv; //리턴
	}
	//예외가 발생하면 화면에 예외 정보를 출력하는 뷰를 보여줌 
	
	
	
	
	
	

}
