<%@page import="se.portalen.wolframbeta.WolframBeta"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="static/js/jquery-1.8.2.js"></script>
	<link href="css/main.css" rel="stylesheet" type="text/css" >
	<link href='http://fonts.googleapis.com/css?family=Orienta' rel='stylesheet' type='text/css'>
	<title>Wolfram Beta</title>
</head>
<body>
	<div id="wrapper">
		<div id="content"> 
			<h1>Wolfram Beta</h1>
			<br />
			<br />
			<form id="mathInput">
				<input type="text" id="input" /> 
				<input type="submit" />
			</form>
			<br />
			<div id="result">
				<p>Results goes here</p>
			</div>
		</div>
	</div>
</body>
</html>