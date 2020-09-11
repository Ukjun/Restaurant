<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link
	href="https://fonts.googleapis.com/css2?family=Gamja+Flower&family=Nanum+Pen+Script&display=swap"
	rel="stylesheet">
${msg }
<div>
	<div id="map" style="width: 100%; height: 100%;"></div>

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=dc267f04a16124aede3d52e9e1efb762"></script>
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script>
		var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
		var options = { //지도를 생성할 때 필요한 기본 옵션
			center : new kakao.maps.LatLng(35.865337, 128.593313), //지도의 중심좌표.
			level : 3
		//지도의 레벨(확대, 축소 정도)
		};

		const map = new kakao.maps.Map(container, options);
		
		function getRestaurantList(){
			axios.get('/restaurant/getList').then(function(res){
				console.log(res.data)
				
				res.data.forEach(function(item){
					var na = {
							'Ga' : item.lng,
							'Ha' : item.lat
					}
					
					var marker = new kakao.maps.Marker({
						position : map.getCenter()
					});
					marker.setMap(map)
				})
			})	
		}
		getRestaurantList()
	</script>
</div>