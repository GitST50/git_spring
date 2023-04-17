package com.co.kr.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.co.kr.domain.BoardListDomain;
import com.co.kr.domain.LoginDomain;
import com.co.kr.service.UploadService;
import com.co.kr.service.UserService;
import com.co.kr.util.CommonUtils;
import com.co.kr.vo.LoginVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(value = "/")
public class UserController { //로그인을 처리하고 게시판목록을 가져오는 기능을 제공
	
	@Autowired //자동으로 객체 생성하고 주입:클래스의 인스턴스를 생성하고 해당 필드와 메소드파라미터에 자동주입
	private UserService userService;
	
	@Autowired
	private UploadService uploadSerivce;
	
	@RequestMapping(value = "board")
	public ModelAndView login(LoginVO loginDTO, HttpServletRequest request, HttpServletResponse response) throws IOException{
		//session 처리
		HttpSession session = request.getSession(); //request.getSession():HttpServletRequest 객체에서 세션을 얻는 메소드
		ModelAndView mav = new ModelAndView();      //또한 이미 생성된 세션이있으면 그 세션을 리턴하고 없으면 새로운세션 생성후 리턴
		//중복체크
		Map<String, String> map = new HashMap();
		map.put("mbId", loginDTO.getId()); //LoginVO에서 id를 가져와서 밸루값에 넣고 키값에 mbId를 넣어줌
		map.put("mbPw", loginDTO.getPw()); //위와 동일 //나중에 map.get("mbId")호출하면 loginDTO.getId()의 값을 리턴하게됨
		
		
		//중복체크
		int dupleCheck = userService.mbDuplicationCheck(map); //userService 인터페이스의 mbDu..Check(map)을 가져와 dupleCheck에 집어넣음
		LoginDomain loginDomain = userService.mbGetId(map); //UserMapper.xml -> UserMapper.java -> userService.impl -> userService.java
		                                                    //를 통하여 map에 id값을 담아서 가져온뒤 LoginDomain 인스턴스에 주입
		
		if(dupleCheck == 0) {
			String alertText = "없는 아이디이거나 패스워드가 잘못되었습니다. 가입해주세요";
			String redirectPath = "main/signin";
			CommonUtils.redirect(alertText, redirectPath, response);
			return mav;
		}
		
		//현재 아이피 추출
		String IP = CommonUtils.getClientIP(request);
		
		//session 저장
		session.setAttribute("ip", IP); //session객체에 속성을추가,업뎃 
		session.setAttribute("id", loginDomain.getMbId());
		session.setAttribute("mbLevel", loginDomain.getMbLevel());
		
		List<BoardListDomain> items = uploadSerivce.boardList(); //uploadService.java 맨위에있는 전체리스트조회하는 메서드를 호출하여 결과값을 List<BoardListDomain>
		                                                         //형태의 변수인 items에 집어넣음
		System.out.println("items ==> " + items);                //이 List가 들어간 items를 뷰에서 이용할수있게함
		mav.addObject("items", items); //ModelAndView가 전달할 데이터를 설정.  즉, ModelAndView에 board/boardList.html이라는 뷰와 실제데이터 items가 들어감
		
		mav.setViewName("board/boardList.html"); //ModelAndView가 렌더링할 JSP페이지 설정: 이 메소드의 인자로 JSP파일명을 전달
		return mav;                              //컨트롤러에서 처리된 결과데이터와 뷰를 함께 전달, 뷰 이름 지정
	};
	
	//좌측 메뉴 클릭시 보드화면 이동(로그인된 상태)
	@RequestMapping(value = "bdList")
	public ModelAndView bdList() {
		ModelAndView mav = new ModelAndView();
		List<BoardListDomain> items = uploadSerivce.boardList();
		System.out.println("items ==> "+ items);
		mav.addObject("items", items);
		mav.setViewName("board/boardList.html");
		return mav;
	}

}
