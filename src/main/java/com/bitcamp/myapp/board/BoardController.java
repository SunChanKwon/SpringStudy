package com.bitcamp.myapp.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	BoardDAO dao =  new BoardDAO();
	@RequestMapping("/boardList")
	public ModelAndView boardList(PagingVO pVo) {
		ModelAndView mav = new ModelAndView();
		
		//총 레코드 수
		dao.totalRecordCount(pVo);
		
		mav.addObject("pVo", pVo);
		mav.addObject("list",dao.boardPageSelect(pVo)); //해당 페이지 레코드 선택
		mav.setViewName("board/boardList");
		return mav;
	}
}
