<%@ include file="static/templates/header.jsp" %>
	
	<div id="smallInputContainer">
		<div id="smallLogo">
			<h1>Wolfram</h1><h2>Beta</h2>
		</div>
		<div class="inputContainer">
			<form id="mathForm">
				<input class="mathInput" type="text" />
				<input class="mathSubmit" type="submit" value="=" title="Compute" />
				<div class="clear"></div>
			</form>
		</div>
	</div>
	
	<div class="block">
		<div class="answer">
			<p>Hello</p>
		</div>		
	</div>
	
<%@ include file="static/templates/footer.jsp" %>