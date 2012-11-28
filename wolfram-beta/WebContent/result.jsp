<%@ include file="static/templates/header.jsp" %>
	
	<div id="smallInputContainer">
		<div id="smallLogo">
			<h1>Wolfram</h1><h2>Beta</h2>
		</div>
		<div class="inputContainer">
			<form class="mathForm" id="smallInputForm">
				<input class="mathInput" type="text" />
				<button class="mathSubmit" title="compute">=</button>
				<div class="clear"></div>
			</form>
		</div>
	</div>
	
	<div class="block">
		<div class="answer">
			<h3>Input:</h3>
			<p class="output">6</p>
		</div>	
		
		<div class="answer">
			<h3>Output:</h3>
			<p class="output">82</p>
		</div>
		
		<div class="answer">
			<h3>Properties:</h3>
			<ul>
				<% 
					for (int i = 0; i < TalkSrv.constructTest().length; i++) {
						out.print("<div class='answerItemContainer'><li>" + TalkSrv.constructTest()[i] + "</li></div>");
					}
				%>
			</ul>
		</div>
		
		<div class="bottomLinks">
			<a href="#">Learn more</a>
		</div>
		
	</div>
	
<%@ include file="static/templates/footer.jsp" %>