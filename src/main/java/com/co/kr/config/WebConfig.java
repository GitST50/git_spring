package com.co.kr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

//Spring MVC에서 사용되는 Thymeleaf 템플릿 엔진의 설정을 담고있는 클래스
@Configuration	
public class WebConfig implements WebMvcConfigurer {

	@Bean
	@Description("Thymeleaf template resolver serving HTML")
	public ClassLoaderTemplateResolver templateResolver() { 
		//ClassLoader를 통해 resources/templates/ 에서 .html 을찾음
		
		var templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setCacheable(true);
		templateResolver.setTemplateMode("html");
		templateResolver.setCharacterEncoding("UTF-8");
		return templateResolver;
	}
	
	@Bean
	@Description("Thymeleaf template engine with Spring integration")
	public SpringTemplateEngine templateEngine() {
		//SpringTemplateEngine 객체를 생성하며 templateResolver()에서 생성한 ClassLoaderTemplateResolver객체를 주입받아 template engine을 생성
		var templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.addDialect(new LayoutDialect()); //dependency 미리 설정해놓음
		return templateEngine;
		//template engine은 web에서 html등을 제공하는데 사용, Thymeleaf와 같은 템플릿엔진 사용
	}
	
	@Bean
	@Description("Thymeleaf view resolver") //해당 Bean의 설명을 지정하는 어노테이션, Bean을 관리하며 이해에 도움
	public ViewResolver viewResolver() { 
		//ThymeleafViewResolver 객체를 생성하는 viewResolver() 메소드 정의: 
		//templateEngine()에서 생성한 SpringTemplateEngine을 주입받아 Thymeleaf 뷰 해석기를 생성
		var viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("UTF-8");
		return viewResolver;
	}
	
	public void addViewControllers(ViewControllerRegistry registry) {
		//addViewControllers()메소드를 오버라이드 하여 "/"경로에대한 ViewController를 등록하고,ViewController는 index.html을 반환
		registry.addViewController("/").setViewName("index.html");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//addResourceHandlers를 오버라이딩하여 /resources/upload/**경로(/upload/경로 하위에있는 리소스파일)에 대한 리소스 핸들러를 등록하며
		//이 리소스 핸들러는 C:/upload/ 경로에있는 리소스 파일을 찾음
		// /images/**은 /resources/images/으로 시작하는 url호출은 /resources/images/경로 하위에 있는 리소스 파일이다 라는 의미입니다.
		registry.addResourceHandler("/resources/upload/**").addResourceLocations("file:///C:/upload/");
	}
	
	
}