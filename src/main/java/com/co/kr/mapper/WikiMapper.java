package com.co.kr.mapper;

import java.util.HashMap;
import java.util.List;

import com.co.kr.domain.WikiContentDomain;
import com.co.kr.domain.WikiListDomain;

public interface WikiMapper {
	
	//list
	public List<WikiListDomain> wikiList();
	
	//content insert
	public void wikiUpload(WikiContentDomain wikiContentDomain);
	
	//content update
	public void wkContentUpdate(WikiContentDomain wikiContentDomain);
	
	//content delete
	public void wkContentRemove(HashMap<String, Object> map);
	
	//select one
	public WikiListDomain wikiSelectOne(HashMap<String, Object> map);
	

}
