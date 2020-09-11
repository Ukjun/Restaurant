<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="/res/css/common.css">
<link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<div id="sectionContainerCenter">
	<div>
		<form id="frm" action="/restaurant/regProc" method="post">
			<div>
				<input type="text" name="nm" placeholder="가게명">
			</div>
			<div>
				<input type="text" name="addr" placeholder="주소">
				<input type="button" onclick="getLatLng()" value="좌표 가져오기">
			</div>
				<input type="hidden1" name="lat" value="0">
				<input type="hidden1"name="lng" value="0">
			<div>
				카테고리 : 
				<select name="cd_category">
				<c:forEach items="${categoryList }" var="item">
				<option value="${item.cd }">${item.val }</option>
				</c:forEach>
				</select>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=dc267f04a16124aede3d52e9e1efb762&libraries=services"></script>
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script>
		var geocoder = new kakao.maps.services.Geocoder();
		function getLatLng(){
			const addStr = frm.addr.value;
			
			if(addStr.length < 9){
				alert("Please Check Your Address")
				frm.addr.focus();
				return
			}
			
			geocoder.addressSearch(addStr, function(result,status){
				if (status === kakao.maps.services.Status.OK) {
			        console.log(result);
			    }
			});
		}

	</script>
</div>