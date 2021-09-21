package com.bitcamp.myapp.register;

import java.util.List;

public interface RegisterDAOImpl {
	//회원가입
	public int insertRecord(RegisterVO vo);
	//회원정보 수정 시 기존 정보 선택하는 method
	//public RegisterVO selectRecord(String userid);
	public void selectRecord(RegisterVO vo);
	//회원정보 수정
	public int updateRecord(RegisterVO vo);
	//로그인
	public void loginSelect(RegisterVO vo);
	//아이디 중복검사
	public int idDoubleCheck(String userid);
	//도로명을 이용한 우편번호, 주소 검색
	public List<ZipCodeVO> zipSearchRecord(String doro);
	
}
