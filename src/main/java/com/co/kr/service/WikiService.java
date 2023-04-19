package com.co.kr.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.HashMap;


import com.co.kr.domain.LoginDomain;
import com.co.kr.domain.WikiListDomain;
import com.co.kr.vo.FileListVO;
import com.co.kr.vo.WikiListVO;


public interface WikiService {
	

    //전체 리스트 조회
	public List<WikiListDomain> wikiList();
	
	//인서트 및 업데이트
	public int wikiProcess(WikiListVO wikiListVO, MultipartHttpServletRequest request, HttpServletRequest httpReq);
	
	//하나 리스트 조회
	public WikiListDomain wikiSelectOne(HashMap<String, Object> map);
	

}
