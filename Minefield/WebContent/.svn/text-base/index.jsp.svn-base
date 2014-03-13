<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="net.scrummanager.minefield.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<% Minefield minefield = (Minefield)session.getAttribute("minefield"); %>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Minefield</title>
	</head>
	<body>
		<form action="<%=request.getContextPath()%>/controller/Minefield" method="post">
			<%	if (minefield!= null) {%>
				<%=minefield.getBoardAsHtml(request.getContextPath())%>
			<%	
					if (minefield.isLost()) {
						session.removeAttribute("minefield");%>
				Has perdido	
				<input type="submit" value="Iniciar juego" />		
			<%		} else if (minefield.isWin()) {
				session.removeAttribute("minefield");%>
				Has ganado
				<input type="submit" value="Iniciar juego" />
			<%		}
				} else { %>
				<input type="submit" value="Iniciar juego" />
			<%	} %>
		</form>
	</body>
</html>