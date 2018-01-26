<%String errore = (String) request.getAttribute("errore"); %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="css/style2.css">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>404 - Resource not found</title>
    <style type="text/css">a{background-color:transparent;-webkit-text-decoration-skip:objects}a:active,a:hover{outline-width:0}body,html{width:100%;height:100%;background-color:#21232a}body{color:#fff;text-align:center;text-shadow:0 2px 4px rgba(0,0,0,.5);padding:0;min-height:100%;-webkit-box-shadow:inset 0 0 75pt rgba(0,0,0,.8);box-shadow:inset 0 0 75pt rgba(0,0,0,.8);display:table;font-family:"Open Sans",Arial,sans-serif}h1{font-family:inherit;font-weight:500;line-height:1.1;color:inherit;font-size:36px}h1 small{font-size:68%;font-weight:400;line-height:1;color:#777}a{text-decoration:none;color:#fff;font-size:inherit;border-bottom:dotted 1px #707070}.lead{color:silver;font-size:21px;line-height:1.4}.cover{display:table-cell;vertical-align:middle;padding:0 20px}</style>
<style>
body {
    background-image: url("immagini/fotoHome.jpg");
     background-repeat: no-repeat;
    background-attachment: fixed;
    background-position: center; 
}
</style>
</head>
<body class ="sfondo">
<div class="cover">
        <h1>Risorsa non trovata <small>Error 404</small></h1>
        <% if(errore != null){%>
        <p class="lead"><%=errore %></p>
        <%}else{%>
        <p class="lead">La risorsa non è stata trovata ma potrebbe essere disponibile in futuro.</p>
        <%} %>
        <p><a href="index.jsp">vai alla pagina iniziale</a></p>
    </div>
</body>
</html>