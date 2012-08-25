<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Tracktivity - Activity ${activity.name}</title>

<!-- Mobile viewport optimized: j.mp/bplateviewport -->
<meta name="viewport" content="width=device-width, minimum-scale=1.0 maximum-scale=1.0 initial-scale=1.0" />

<link rel="stylesheet" href="/css/normalize.css" type="text/css" />
<link rel="stylesheet" href="/css/style.css" type="text/css" />
<link rel="stylesheet" href="http://cdn.leafletjs.com/leaflet-0.4.4/leaflet.css" />
</head>
<body>

<c:choose>
	<c:when test="${empty activity}">
		<h2>The activity with the ID <em>${activityId}</em> does not exist.</h2>
	</c:when>
	<c:otherwise>
		<h1>${activity.name}</h1>
		<p>created by ${activity.user.username}</p>
		<div id="map"></div>
	</c:otherwise>
</c:choose>

<script src="http://cdn.leafletjs.com/leaflet-0.4.4/leaflet.js"></script>
<script type="text/javascript">
	var multiPolyline = ${activity.track.sparseMultiPolyline};
	var latLonBounds = ${activity.track.latLngBounds};
	
	var map = L.map('map');
	map.fitBounds(latLonBounds);
	
	L.tileLayer('http://{s}.tile.cloudmade.com/{key}/{styleId}/256/{z}/{x}/{y}.png', {
		key: '6eac6d67cf3f4fa8a18bbf5bec747cdc',
		styleId: 997,
	    attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery � <a href="http://cloudmade.com">CloudMade</a>',
	    maxZoom: 17,
	    detectRetina: true
	}).addTo(map);
	
	var polyline = L.multiPolyline(multiPolyline).addTo(map);
</script>

</body>
</html>