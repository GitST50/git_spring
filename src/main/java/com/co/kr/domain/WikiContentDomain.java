package com.co.kr.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "builder")
public class WikiContentDomain {
	
	private Integer wk_seq;
	private String wk_id;
	private String wk_title;
	private String wk_content;

}
