<%@page import="se.portalen.wolframbeta.TeXMaker"%>
<%@page import="se.portalen.wolframbeta.EquationGen"%>
<%@ include file="static/templates/header.jsp" %>	

<% 
	String equation = request.getParameter("mathInput");

	TeXMaker texMaker = new TeXMaker();
	EquationGen eqGen = new EquationGen();

	if(WebFunctions.checkIfEqExists(WebFunctions.generateEqName(equation)) == 0) {
		eqGen.generateEquation(texMaker.parseTex("5pix^5) / (7 + 5x - 4x^2)"), WebFunctions.generateEqName(equation));
	}
%>	
		

	
	<div id="smallInputContainer">
		<div id="smallLogo">
			<h1>Wolfram</h1><h2>Beta</h2>
		</div>
		<div class="inputContainer">
			<form class="mathForm" id="smallInputForm">
				<input class="mathInput" type="text" value="<% out.print(equation); %>" />
				<button class="mathSubmit" title="compute">=</button>
				<div class="clear"></div>
			</form>
		</div>
	</div>
	
	<div class="block">
		<div class="answer">
			<h3>Input:</h3>
			<p class="output"><img src="temp/equations/<% out.print(equation); %>.png" /></p>
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
	</div>
	
<%@ include file="static/templates/footer.jsp" %>
