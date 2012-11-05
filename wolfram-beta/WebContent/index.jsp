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
		<div id="header">
			<div id="header-inside">
				<a href="#" class="header-text">About »</a>
				<p class="header-seperator">|</p>
				<a href="#" class="header-text">Sign in »</a>
			</div>
		</div>
		
		<div id="wrapper">
			<div id="input"> 
				
				<div id="inputContainer">
				<img src="static/img/logo.jpg" />
				<p>Enter something you want to <b>calculate</b>.</p>
					<form id="mathForm">
						<input class="mathInput" type="text" />
						<input class="mathSubmit" type="submit" value="=" title="Compute" />
						<div class="clear"></div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>