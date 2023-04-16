package com.co.kr.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class CommonUtils {
	
	//날짜
	public static String currentTime() { //현재날짜와 시간을 yyyy..ss형식으로 반환
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
		Date currentDate = new Date();
		return sdf.format(currentDate);
	}
	
	//아이피 겟
	public static String getClientIP(HttpServletRequest req) { //HttpServletRequest객체에서 IP주소 추출
		String ip = req.getHeader("X-Forwarded-For");
		if(ip==null) {
			ip=req.getHeader("Proxy-Client-IP");
			
		}
		if(ip==null) {
			ip=req.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null) {
			ip = req.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null) {
			ip = req.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null) {
			ip = req.getRemoteAddr();
		}
		if(ip.equals("0:0:0:0:0:0:0:1")) {
			ip = ip.replace("0:0:0:0:0:0:0:1", "127.0.0.1");
		}
		return ip;
	};
	
	// 오쓰 redirect
	public static void redirect(String alertText, String redirectPath, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8"); //서버에서 클라로 전송되는 컨텐츠의 타입을 설정 //text.html 은 HTML파일을 의미 //UTF-8안하면 한글깨짐
		PrintWriter out = response.getWriter();
		ModelAndView mav = new ModelAndView(); //MAV = Model과 View를 동시에 설정할수있는 클래스:View에 전달할 데이터를 Model에 저장후 View이름을 View에 저장하여 전달
		//개발용 리다이렉트                          //이를 통해 컨트롤러에서 처리한 결과 데이터와 뷰이름을 모두 설정 가능
		out.println("<script>alert('"+alertText+"'); location.href='"+redirectPath+"'</script>"); //location.href를 이용해 리다이렉트
		out.flush(); //출력버퍼에 남아있는데이터 강제전송
	}
	

}
