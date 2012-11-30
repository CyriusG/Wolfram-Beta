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
			<p class="output"><img src="static/img/AlphaBeta.png" /></p>
		</div>	
		
		<div class="answer">
			<h3>Output:</h3>
			<p class="output">82</p>
		</div>
		
		<div class="answer">
			<h3>Properties:</h3>
			<ul>
				<li>6 = 82</li>
				<li>82 = 6</li>
				<li>6 is a number.</li>
				<li>82 is another number.</li>
				<li>82 minus something is 6.</li>
			</ul>
		</div>
		
		<div class="bottomLinks">
			<a href="#">Learn more</a>
		</div>
		
	</div>
	
<%@ include file="static/templates/footer.jsp" %>