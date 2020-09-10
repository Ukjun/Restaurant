<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>



<link rel="stylesheet" type="text/css" href="/res/css/common.css">
</head>
<body>
	<div id="container">
		<header>
			<div id="headerLeft">
				<c:choose>
					<c:when test="${LoginUser.profile_img != null}">
						<img class="pImg" onclick="window.open(this.src)"
							src="/res/img/user/${LoginUser.i_user }/${LoginUser.profile_img}">
					</c:when>
					<c:otherwise>
						<img class="pImg" onclick="window.open(this.src)"
							src="/res/img/default_profile.jpg">
					</c:otherwise>
				</c:choose>
				<div id="headerNm">${LoginUser.nm}님 환영합니다.</div>
				<div><a href="/user/logout">로그아웃</a></div>
			</div>
			<div id="headerRight">로그아웃/메뉴들</div>
		</header>
		<section>
			<jsp:include page="/WEB-INF/view/${view }.jsp"></jsp:include>
		</section>
		<footer> 회사 정보 </footer>
	</div>
</body>
</html>