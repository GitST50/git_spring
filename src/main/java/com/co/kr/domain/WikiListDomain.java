package com.co.kr.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "builder")
public class WikiListDomain {
	
	private String wk_seq;
	private String wk_id;
	private String wk_title;
	private String wk_content;
	private String wk_create_at;
	private String wk_update_at;
	

}
