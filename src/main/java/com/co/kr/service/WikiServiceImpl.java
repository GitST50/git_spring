package com.co.kr.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.co.kr.domain.WikiListDomain;
import com.co.kr.mapper.WikiMapper;
import com.co.kr.vo.WikiListVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class WikiServiceImpl {
	
	@Autowired
	private WikiMapper wikiMapper;
	
	
	public List<WikiListDomain> wikiList(){
		return wikiMapper.wikiList();
	}
	
	
	//인서트 및 업데이트
	public int wikiProcess(WikiListVO wikiListVO, MultipartHttpServletRequest request, HttpServletRequest httpReq) {
		return 1;
	}
		
		//하나 리스트 조회
	public WikiListDomain wikiSelectOne(HashMap<String, Object> map) {
		return wikiMapper.wikiSelectOne(map);
	}
	

}
