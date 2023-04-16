package com.co.kr.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class Pagination {
	
	public static Map<String, Object> pagination(int totalcount, HttpServletRequest request){
		//총 데이터 수(totalCount)와 HttpServletRequest인 request를 매개변수로 받아서 Map<string,object>형태로 페이지정보 반환
		//
		Map<String, Object> map = new HashMap<String,Object>();
		//페이지넘버 초기화
		String pnum = request.getParameter("page");
		System.out.println("pnum"+pnum);
		if(pnum==null) { pnum = "1"; }
		
		//스트링을 인트로 파싱
		int rowNUM = Integer.parseInt(pnum);
		if(rowNUM <0) { rowNUM = 1;}
		
		//페이지네이션 범위정함 나머지 없거나 있으면 +1
		int pageNum;
		if(totalcount % 10 == 0) {
			pageNum = totalcount /10;
			
		}else {
			pageNum = (totalcount /10)+1;
		}
		if (rowNUM > pageNum) { rowNUM = pageNum;}
		//페이지네이션 중간범위 지정 -- 시작페이지 21번~27번 -- 끝페이지 30
		int temp = (rowNUM - 1)% 10; //0,1,2 나머지 값
		int startpage = rowNUM - temp; // temp는 startpage보다 끝자리 항상 1작음 27-6
		int endpage = startpage + (10-1); //10 20 30 40
		
		//startpage기준 무조건 +9한것이어서 pageCount 비교 후 pagecCount 적용
		if(endpage>pageNum) {endpage = pageNum;}
		
		//쿼리 범위 지정
		int offset = (rowNUM - 1) * 10;
		
		map.put("rowNUM", rowNUM);
		map.put("pageNUM", pageNum);
		map.put("startpage", startpage);
		map.put("endpage", endpage);
		map.put("offset", offset);
		//리턴된 map객체에 rowNUM(요청된페이지번호),pageNum(전체 페이지수),startPage(시작페이지),endPage(끝페이지)
		//,offset(데이터 조회시 시작위치)저장
		
		return map;
	}
	

}
