package com.co.kr.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "builder")
public class BoardContentDomain {
	
	private Integer bdSeq; //컨텐츠데이터의 키값
	private String mbId;
	
	private String bdTitle;
	private String bdContent;
	

}
