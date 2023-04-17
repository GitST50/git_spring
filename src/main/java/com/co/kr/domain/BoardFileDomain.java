package com.co.kr.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "builder")
public class BoardFileDomain { //uploadMapper.xml에 쿼리문 존재하며 domain패키지내의 ..Domain.java와 연결되어있음
	
	private Integer bdSeq; //키값
	private String mbId;
	
	private String upOriginalFileName; //최초올렸던 originalfilename을 쓸수있게끔
	private String upNewFileName; //동일 이름 업로드 될 경우
	private String upFilePath;
	private Integer upFileSize;

}
