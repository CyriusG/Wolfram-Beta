<%@ include file="static/templates/header.jsp" %>
	
	<div id="smallInputContainer">
		<div id="smallLogo">
			<h1>Wolfram</h1><h2>Beta</h2>
		</div>
		<div class="inputContainer">
			<form class="mathForm" id="smallInputForm">
				<input class="mathInput" type="text" />
				<input class="mathSubmit" type="submit" value="=" title="Compute" />
				<div class="clear"></div>
			</form>
		</div>
	</div>
	
	<div class="block">
		<div class="answer">
			<h3>Input:</h3>
			<div class="output">
				<p>6</p>
			</div>
		</div>	
		
		<div class="answer">
			<h3>Output:</h3>
			<div class="output">
				<p>82</p>
			</div>
		</div>	
	</div>
	
<%@ include file="static/templates/footer.jsp" %>