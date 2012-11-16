<%--Header starts here --%>

<%@ include file="static/templates/header.jsp" %>

<%-- Header ends here --%>
	<%--
		Building up the div where the user inputs what to calculate.
	 --%>
	<div id="input" class="block"> 
		<div class="inputContainer">
			<h1>Wolfram</h1><h2>Beta</h2>
			<div class="clear"></div>
			<p>Enter something you want to <b>calculate</b>.</p>
			<%--The input form. --%>
			<form class="mathForm">
				<input class="mathInput" type="text" />
				<input class="mathSubmit" type="submit" value="=" title="Compute" />
				<div class="clear"></div>
			</form>
		</div>
	</div>

<%-- Footer starts here --%>

<%@ include file="static/templates/footer.jsp" %>

<%-- Footer ends here --%>