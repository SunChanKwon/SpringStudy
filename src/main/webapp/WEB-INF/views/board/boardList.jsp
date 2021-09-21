<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
	#header, #board{overflow:auto; border-bottom:1px solid gray}
	#header>div{float:left; width:50%;}
	#header>div:last-child{float:right; text-align:right}
	
	#board>li{float:left; width:10%; border-bottom:1px solid #ddd; height:40px;}
	#board>li:nth-child(5n+2){width:60%;}
</style>
<h1>게시판 리스트</h1>
<div id="header">
	<div>총레코드수 :${pVo.totalRecord } </div>
	<div>총페이지수/현재페이지 : ${pVo.totalPage }/${pVo.nowPage }</div>
</div>
<!-- 리스트 -->
<ul id="board">
	<li>번호</li>
	<li>제목</li>
	<li>글쓴이</li>
	<li>등록일</li>
	<li>조회수</li>

<c:forEach var="vo" items="${list }">
	<li>${vo.no }</li>
	<li>${vo.subject }</li>
	<li>${vo.userid }</li>
	<li>${vo.writedate }</li>
	<li>${vo.hit }</li>
 </c:forEach>
</ul>
<!-- 페이징 -->
<div>
<ul class="pagination pagination-lg">
		<li class="page-item"><a href="#" class="page-link">Preview</a></li>
		<li class="page-item"><a href="#" class="page-link">1</a></li>
		<li class="page-item"><a href="#" class="page-link">2</a></li>
		<li class="page-item"><a href="#" class="page-link">3</a></li>
		<li class="page-item"><a href="#" class="page-link">4</a></li>
		<li class="page-item"><a href="#" class="page-link">Next</a></li>
	</ul>
</div>
<!-- 검색 -->
<div>
<form method="post" action="">
	<select>
		<option value>제목</option>
		<option value="">글내용</option>
		<option value="">작성자</option>
	</select>
	<input type="text" name="" id=""/>
	<input type="submit" value="Search"/>
</form>
</div>