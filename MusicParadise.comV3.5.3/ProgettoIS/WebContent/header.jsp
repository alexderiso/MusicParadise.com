
<%
	ClienteBean bean = (ClienteBean) session.getAttribute("utente");
	GestoreOrdiniBean gestore =(GestoreOrdiniBean) session.getAttribute("utente-gestore");
    String uri = request.getRequestURI();
    Boolean admin = (Boolean)session.getAttribute("adminRoles");
    CarrelloBean cart = (CarrelloBean) session.getAttribute("cart");
    if(admin != null){
    	 if((admin.booleanValue())){
    	    	response.sendRedirect("./gestore-ordini.jsp");
    	    	return;
    	    }
    }
   
 
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@page import="java.util.*, Model.*,Control.*,Bean.*"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/animate.css">

<link rel="stylesheet" href="css/landing-page.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/style2.css">

<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<img class="navbar-brand " src="immagini/brand.png">
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">

				<li <%if(uri.equals("/ProgettoIS/index.jsp")) { %> class="active"
					<% } %> aria-hidden="true"><a href="index.jsp">HOME</a></li>
				<li <%if(uri.equals("/ProgettoIS/catalogo.jsp")) { %> class="active"
					<% } %> class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">CATEGORIA <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="RicercaProdottoControl?strumento=batteria">Batterie</a></li>
						<li><a href="RicercaProdottoControl?strumento=chitarra">Chitarre</a></li>
						<li><a href="RicercaProdottoControl?strumento=basso">Bassi</a></li>
						<li><a href="RicercaProdottoControl?strumento=tastiere">Tastiere</a></li>
						<li><a href="RicercaProdottoControl?strumento=amplificatore">Amplificatori</a></li>
						<li><a href="RicercaProdottoControl?strumento=dj">DJ</a></li>
						<li><a href="RicercaProdottoControl?strumento=home recording">Home Recording</a></li>
						<li><a href="RicercaProdottoControl?strumento=software">Software</a></li>
						<li><a href="RicercaProdottoControl?strumento=fiati">Fiati</a></li>
						<li><a href="RicercaProdottoControl?strumento=archi">Archi</a></li>
						<li><a href="RicercaProdottoControl?strumento=didattica">Didattica</a></li>
						<li><a href="RicercaProdottoControl?strumento=luci">Luci</a></li>
						<li><a href="RicercaProdottoControl?strumento=accessori">Accessori</a></li>
					</ul></li>
					<li ><a href="VisualizzaCatalogoControl">CATALOGO</a></li>
				<li <%if(uri.equals("/ProgettoIS/contatti.jsp")) { %>class="active" 
					<% } %>><a href="contatti.jsp">CONTATTI</a></li>
			</ul>
			
			<form class="navbar-form navbar-left" method="GET"
				action="RicercaProdottoControl">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search"
						name="name" maxlength="20">
				</div>
				<button type="submit"
					class="btn btn-default glyphicon glyphicon-search" id="cerca"></button>
			</form>

			<% if(bean == null) { %>
			<ul class="nav navbar-nav navbar-right">

				<%  if(cart != null) { %>
				<li class="jquery3"><a href="VisualizzaCarrelloControl"
					class="fa fa-shopping-cart fa-3x"> <span id="iconCartNum"><%=cart.countingPrds()%></span></a></li>
				<% } else { %>
				<li class="jquery3"><a href="VisualizzaCarrelloControl"
					class="fa fa-shopping-cart fa-3x"><span id="iconCartNum">0</span></a></li>
				<% } %>
				<li><a href="" data-toggle="modal" data-target="#myModal"
					class="glyphicon glyphicon-log-in" id="benvenuto"> Login</a></li>
			</ul>
			<%  }else{  %>
			<ul class="nav navbar-nav navbar-right">
				<% if(admin.booleanValue()){%>
				<li><a href="amministrazione.jsp">Amministratore</a></li>
				<% } %>
				<%  if(cart != null) { %>
				<li class="jquery3"><a href="carrello.jsp"
					class="fa fa-shopping-cart fa-3x"><span id="iconCartNum"><%=cart.countingPrds()%></span></a></li>
				<% } else { %>
				<li class="jquery3"><a href="carrello.jsp"
					class="fa fa-shopping-cart fa-3x"><span id="iconCartNum">0</span></a></li>
				<% } %>
				<li class="dropdown"><a href="" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false" id="benvenuto">Benvenuto: <%= bean.getNickName() %> <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a
							href="ViualizzaOrdiniCliente?nickname=<%= bean.getNickName()%>" >I
								miei ordini</a></li>
						<li><a href="VisualizzaProfiloControl">Il mio account</a></li>
						<li><a href="LogoutControl?action=esci">Esci</a></li>
					</ul></li>
			</ul>
			<%}%>

		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>
	<br>
	<br>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Login</h4>
				</div>
				<div class="modal-body">

					<form method="post" action="AccediControl" class="form-horizontal"
						id="form1" onsubmit="return isOk();">
						<center>
							<div class="col-sm-12" id="txtErr"></div>
						</center>
						<center>
							<div class="col-sm-12" id="txtErrNickname"></div>
						</center>
						<input type="hidden" name="action" value="accedi"> <input
							type="hidden" name="id" value="<%=uri%>">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">Nickname</label>
							<span
									class="col-sm-2 labName" id="txtErrNickname"></span>
							<div class="col-sm-10">
								<input type="text" name="nick" class="form-control"
									id="inputNick" placeholder="Nickname">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">Password</label>
							<span
									class="col-sm-2 labName" id="txtErrPassword"></span>
							<div class="col-sm-10">
								<input type="password" name="password" class="form-control"
									id="inputPassword" placeholder="Password">
							</div>
						
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<input type="submit" class="btn btn-default" value="Accedi">
								<input type="reset" value="annulla" class="btn btn-default"
									data-dismiss="modal"></input>
							</div>
						</div>
					</form>

					
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/validate.js"></script>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

<script>
function isOk() {
	if( ValidateNickname(document.getElementById("inputNick"),document.getElementById("txtErrNickname"))
			) {
		
		return true;
		
	}
	
	return false;
}
</script>
	<script>

$( document ).ready(function() {
	var form = $('#form1');
	form.submit(function () {
		$("#txtErr").html("");
		var obj = {nick: $('#inputNick').val(), password: $('#inputPassword').val(), action: "accedi"};
	$.ajax({
		type: form.attr('method'),
		url: form.attr('action'),
		dataType: "JSON",
		data: obj,
		success: function (data) {
			if (data.role == "falso") {
				$("#inputNick").css("border-color", "red");
				$("#inputPassword").css("border-color", "red");
				$("#txtErr").html("Password e/o Nickname o Email errati");
			} 
			if( data.role == "cliente") {
				window.location.replace("index.jsp");
			}
			if(data.role == "gestore-ordini") {
				window.location.replace("gestore-ordini.jsp");
			}
		}
	});
	 
	return false;
	}); });
</script>


	<script>
 $(document).ready(function(){
	    $(".jquery").hover(function(){
	        $(this).toggleClass('animated pulse');
	    });
	});
$(document).ready(function(){
    $(".jquery2").hover(function(){
        $(this).toggleClass('animated swing');
    });
});
$(document).ready(function(){
    $(".jquery3").hover(function(){
        $(this).toggleClass('animated swing');
    });
});
$(document).ready(function(){
	// cache the window object
	$window = $(window);
	$('.jquery4').each(function() {
	// declare the variable to affect the defined data-type
	var $scroll = $(this);
	$(window).scroll(function() {
	// HTML5 proves useful for helping with creating JS functions!
	// also, negative value because we're scrolling upwards
	var yPos = -($window.scrollTop() / 6);
	// background position
	var coords = '50% ' + yPos + 'px';
	// move the background
	$scroll.css({ backgroundPosition: coords });
	}); // end window scroll
	});  // end section function
	}); // close out script
 </script>
</body>
</html>