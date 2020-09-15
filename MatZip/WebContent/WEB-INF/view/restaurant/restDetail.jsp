<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link
	href="https://fonts.googleapis.com/css2?family=Gamja+Flower&family=Nanum+Pen+Script&display=swap"
	rel="stylesheet">

<div id="sectionContainer">
	<div>
		<c:if test="${LoginUser.i_user == data.i_user}">
			<div>
				<a href="/restaurant/restMod"><button>수정</button></a>
				<button onclick="isDel()">삭제</button>
				
				<form id="recFrm" action="/restaurant/addRecMenus" enctype="multipart/form-data" method = "post">
					<div><button type="button" onclick="addRecMenu()">메뉴 추가</button></div>
					<input type="hidden" name="i_rest" value="${data.i_rest }">
					 <div id="recItem">
						<!-- 메뉴 : <input type="text" name="menu_nm">
						가격 : <input type="number" name="menu_price">
						사진 : <input type="file" name="menu_pic"> -->
					</div> 
					<div><input type="submit" value = "등록"></div>
				</form>
			</div>
			
		</c:if>
		<div>가게사진들</div>
		<div class="restaurant-detail">
			<div id="detail-header">
				<div class="restaurant_title_wrap">
					<span class="title">
						<h1 class="restaurant_name">가게 이름 : ${data.nm }</h1>
					</span>
				</div>
				<div class="status_branch_none"></div>
				<span class="cnt_hit">조회수 : ${data.cntHits }</span> 
				<span class="cnt_review"></span> 
				<span class="cnt_facvorite">찜 : ${data.cntFavorite }</span>

			</div>
			<div>
				<table>
					<caption>레스토랑 상세정보</caption>
					<tbody>
						<tr>
							<th>주소</th>
							<td>${data.addr}</td>
						</tr>
						<tr>
							<th>카테고리</th>
							<td>${data.cd_category_nm }</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script>
	var idx = 0;
	
	
	console.log(`${LoginUser.i_user}`)
	console.log(`${data.i_user}`)
		function isDel(){
			if(confirm('Do you want Delete?')){
				location.href = '/restaurant/restDel?i_rest=${data.i_rest}'
			}
		}
	
	function addRecMenu(){
		// Ctrl+C Ctrl+V 조심하기
		var div = document.createElement('div');
		
		var inputNm = document.createElement('input');
		inputNm.setAttribute("type","text")
		//생성된 input 이름 설정
		inputNm.setAttribute('name','menu_nm')
		var inputPrice = document.createElement('input');
		inputPrice.setAttribute("type","number")
		inputPrice.setAttribute('name','menu_price')
		var inputPic = document.createElement('input');
		inputPic.setAttribute("type","file")
		inputPic.setAttribute('name','menu_pic_' + idx++)
		
		
		div.append('메뉴 : ')
		div.append(inputNm)
		div.append('가격 : ')
		div.append(inputPrice)
		div.append('사진 : ')
		div.append(inputPic)
		//div id 이름 (메뉴를 다중으로 넣기위해서 )
		recItem.append(div)
	}
	addRecMenu()
	</script>
</div>