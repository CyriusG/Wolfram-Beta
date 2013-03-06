<%@page import="se.portalen.wolframbeta.TeXMaker"%>
<%@page import="se.portalen.wolframbeta.EquationGen"%>
<%@ include file="static/templates/header.jsp" %>	

<% 
	// Save the user input in a String.
	String equation = request.getParameter("mathInput");
	
	// Where should the images for the equations be stored?
	String relativeWebPath = "temp/equations/";
	// Get the absolute path of that directory on the server.
	String path = getServletContext().getRealPath(relativeWebPath) + "/";
	// Tell the server to generate the name for the current equation.
	String name = WebFunctions.generateEqName(equation);
	// Tell the server to calculate the user input and return the result into a String.
	String result = WebFunctions.webCalculate(equation);
	
	// If there's not equation with the same name already generated, generate a new image for it.
	if(WebFunctions.checkIfEqExists(name) == 0) {
		WebFunctions.generateEqImage(equation, name, path);
	}
%>	

	<div id="smallInputContainer">
		<div id="smallLogo">
			<h1>Wolfram</h1><h2>Beta</h2>
		</div>
		<div class="inputContainer">
			<form class="mathForm" id="smallInputForm" action="result.jsp" method="get">
				<input class="mathInput" name="mathInput" type="text" value="<% out.print(equation); %>" />
				<button class="mathSubmit" title="compute">=</button>
				<div class="clear"></div>
			</form>
		</div>
	</div>
	
	<div class="block">
		<div class="answer">
			<h3>Input:</h3>
			<p class="output"><img src="temp/equations/<% out.print(name); %>.png" /></p>
		</div>	
		
		<div class="answer">
			<h3>Result:</h3>
			<p class="output"><% out.print(result); %></p>
		</div>
		
	</div>
	
<%@ include file="static/templates/footer.jsp" %>
