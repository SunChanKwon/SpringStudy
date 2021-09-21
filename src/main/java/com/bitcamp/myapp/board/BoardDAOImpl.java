package com.bitcamp.myapp.board;

import java.util.List;

public interface BoardDAOImpl {
	//총 레코드 수
	public void totalRecordCount(PagingVO pVo);
	//해당 페이지 레코드 선택
	public List<BoardVO> boardPageSelect(PagingVO pVo);
	
}
