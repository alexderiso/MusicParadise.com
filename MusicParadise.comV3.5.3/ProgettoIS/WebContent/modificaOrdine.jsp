
<%
	Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
	if ((adminRoles == null) || (!adminRoles.booleanValue())) {
		response.sendRedirect("./index.jsp");
		return;
	}
	String uri = request.getRequestURI();
	UtenteBean bean = (UtenteBean) session.getAttribute("utente-gestore");
	Collection<?> ordini = (Collection<?>) session.getAttribute("ordini");
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@page import="java.util.*, java.text.*, Model.*,Control.*,Bean.*"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<!-- Custom CSS -->
<link href="css/sb-admin.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<meta name="viewport" content="width=device-width, initial-scale=1">

 <link  href="http://code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
  <script src="http://code.jquery.com/jquery-1.9.0.js"></script>
  <script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js"></script>
  <script type="text/javascript">
        $(function(){
            $('.datepicker').datepicker({showAnim: "fadeIn"});
        })
 </script>

<title>Modifica ordine</title>
</head>
<body>
	<br>
	<br>
	<br>
	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-ex1-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="">Amministrazione</a>
		</div>
		<!-- Top Menu Items -->
		<ul class="nav navbar-right top-nav">
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"><i class="fa fa-user"></i> <%=bean.getNickName()%>
					<b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="#"><i class="fa fa-fw fa-user"></i> Profilo</a></li>

					<li><a href="#"><i class="fa fa-fw fa-gear"></i> Opzioni</a></li>
					<li class="divider"></li>
					<li><a href="AccediRequest?action=esci"><i
							class="fa fa-fw fa-power-off"></i> Log Out</a></li>
				</ul></li>
		</ul>
		<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav side-nav">

				<li <%if (uri.equals("/ProgettoIS/gestore-ordini.jsp")) {%>
					class="active" <%}%>><a href="gestore-ordini.jsp"><i
						class="fa fa-fw fa-dashboard"></i> Dashboard</a></li>

			</ul>
		</div>
		<!-- /.navbar-collapse --> </nav>


	</div>

	<div class="container">



		<div id="wrapper">
			<h1>Stato ordine</h1>

			<form action="ConfermaModOrdineControl" method="post"
				class="form-horizontal" onsubmit="return isOk();">
				<div class="form-group">
					<label>Corriere</label> <select class="form-control" name="corriere">
						<option>Bartolini</option>
						<option>GLS</option>
						<option>DHL</option>
						<option>Poste italiane</option>
						<option>SDA</option>
					</select>
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">N° Tracking</label> <input
						type="text" class="form-control" id="inputNumTracking"
						placeholder="Numero tracking" name="numTracking" maxlength="10"> <span
						id="txtErr"></span>
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">Data di consegna</label>
					<p>
						<input type="text" id="datepickerIniz" onchange="checkDate()"
							name="dataConsegna">
					</p>
				</div>
				<button type="submit" class="btn btn-default">conferma</button>
			</form>


		</div>
	</div>

	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>


	<script>
	function isOk() {
		if (ValidatePhone(document.getElementById("inputNumTracking"), document.getElementById("txtErr"))) {

			return true;

		}

		return false;
	}
</script>



	<script>
		$(function() {
			//Data Inizio
			$("#datepickerIniz").datepicker({
				minDate : 0
			});
			$("#datepickerIniz").datepicker().datepicker("setDate", new Date());

			var d = $("#datepickerIniz").datepicker('getDate');

			//Data Fine
			$("#datepickerFine").datepicker({
				minDate : d
			});
			$("#datepickerFine").datepicker().datepicker("setDate", new Date());

		});
	</script>
	<script>
		function checkDate() {
			var d = $("#datepickerIniz").datepicker('getDate');
			var s = $("#datepickerFine").datepicker('getDate');

			$("#datepickerFine").datepicker("option", "minDate", d);
			if (d > s)
				$("#datepickerFine").datepicker().datepicker("setDate", d);
		}
	</script>

</body>
</html>