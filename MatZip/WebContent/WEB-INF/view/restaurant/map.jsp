<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link
	href="https://fonts.googleapis.com/css2?family=Gamja+Flower&family=Nanum+Pen+Script&display=swap"
	rel="stylesheet">
	<style>
		.label {margin-bottom: 96px;}
		.label * {display: inline-block;vertical-align: top;}
		.label .left {background: url("https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_l.png") no-repeat;display: inline-block;height: 24px;overflow: hidden;vertical-align: top;width: 7px;}
		.label .center {background: url(https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_bg.png) repeat-x;display: inline-block;height: 24px;font-size: 12px;line-height: 24px;}
		.label .right {background: url("https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_r.png") -1px 0  no-repeat;display: inline-block;height: 24px;overflow: hidden;width: 6px;}
	</style>
<div id="sectionContainerCenter">
	<div id="mapContainer" style="width:100%; height:100%;"></div>
	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=dc267f04a16124aede3d52e9e1efb762"></script>
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script>		
	 const options = { //지도를 생성할 때 필요한 기본 옵션
		      center: new kakao.maps.LatLng(35.865864, 128.593841), //지도의 중심좌표.
		      level: 5 //지도의 레벨(확대, 축소 정도)
		   };

		   var map = new kakao.maps.Map(mapContainer, options);
		   
		   function getRestaurantList() {
		      axios.get('/restaurant/ajaxGetList').then(function(res) {
		         console.log(res.data)
		         
		         res.data.forEach(function(item) {      
		            
		            createMarker(item)
		    
		         })
		      })      
		   }
		   getRestaurantList()
		   
		   function createMarker(item){
		      var content = `<div class ="label"><span class="left"></span><span class="center">\${item.nm}</span><span class="right"></span></div>`;
		      var mPos = new kakao.maps.LatLng(item.lat, item.lng)
		      
		      var marker = new kakao.maps.CustomOverlay({
		          position: mPos,
		          content: content   
		      });
		      marker.setMap(map)
		   }
		   // check for Geolocation support
		   if (navigator.geolocation) {
		     console.log('Geolocation is supported!');
		     
		     var startPos;        
		     navigator.geolocation.getCurrentPosition(function(pos) {      
		           startPos = pos           
		          console.log('lat : ' + startPos.coords.latitude)
		          console.log('lng : ' + startPos.coords.longitude)
		          
		          if(map) {
		             var moveLatLon = new kakao.maps.LatLng(startPos.coords.latitude, startPos.coords.longitude)
		             map.panTo(moveLatLon)                
		          }
		     });
		     
		   } else {
		     console.log('Geolocation is not supported for this Browser/OS.');
		   }
	</script>
</div>