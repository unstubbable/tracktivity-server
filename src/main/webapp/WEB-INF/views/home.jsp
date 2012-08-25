<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Tracktivity - Recent Activities</title>
<link rel="stylesheet" href="/css/normalize.css" type="text/css" />
<link rel="stylesheet" href="/css/style.css" type="text/css" />
</head>
<body>

<h1>Activities</h1>

<p><em>fetched in ${fetchTime} ms</em></p>

<c:forEach var="activity" items="${activities}">
	<s:url value="user/{username}" var="user_url">
		<s:param name="username" value="${activity.user.username}" />
	</s:url>
	<s:url value="activity/{activty_id}" var="activty_url">
		<s:param name="activty_id" value="${activity.id}" />
	</s:url>
	<a href="${activty_url}">${activity.name}</a>, created by <a href="${user_url}">${activity.user.username}</a><br/>
</c:forEach>

</body>
</html>