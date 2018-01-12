<%
UtenteBean utente = (UtenteBean) session.getAttribute("utente");
if(utente == null){
	response.sendRedirect("./index.jsp");
	return;
}

%> %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@page import="java.util.*, Model.*,Control.*,Bean.*"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<link rel="stylesheet" href="css/animate.css">


<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">
<title>Music Paradise</title>
</head>
<body>

	<%@ include file="header.jsp"%>
	
	<br>
	<br>
	<br>
	
	<div class = "container">
	<h2>Ordine effettuato con successo <a href="index.jsp">vai agli acquisti</a></h2>
	</div>
	

	



</body>
</html>