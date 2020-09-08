<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    ${msg }
<div>
	Map
</div>

<div id ="googleMap" style="width:500px; height:300px;"></div>
<script src ="http://maps.google.apis.com/maps/api/js"></script>
<script>
	function initialize(){
		var Lating  = new google.maps.Lating(37.506736,126.783362);
		
		var map Prop={
				center: Lating, zoom:5,
				mapTypeId: google.maps.MapTypeId.ROADMAP
		};
		
		var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);
		
		var marker = new google.maps.Marker({
			position: Lating, map: map,
		});
	}
	google.maps.event.addDomListener(window,'load',initialize);
</script>