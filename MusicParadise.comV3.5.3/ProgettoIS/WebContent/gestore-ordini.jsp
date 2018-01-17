
<%
Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
if((adminRoles == null)||(!adminRoles.booleanValue())){
	response.sendRedirect("./index.jsp");
	return;
}
String uri = request.getRequestURI();
    UtenteBean bean = (UtenteBean) session.getAttribute("utente");
	Collection<?> ordini = (Collection<?>) session.getAttribute("ordini");
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/sb-admin.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
<%@page import="java.util.*, java.text.*, Model.*,Control.*,Bean.*"%>
</head>
<body>

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
				data-toggle="dropdown"><i class="fa fa-user"></i> <%=bean.getNickName() %>
					<b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="#"><i class="fa fa-fw fa-user"></i> Profilo</a></li>

					<li><a href="#"><i class="fa fa-fw fa-gear"></i> Opzioni</a></li>
					<li class="divider"></li>
					<li><a href="LogoutControl?action=esci"><i
							class="fa fa-fw fa-power-off"></i> Log Out</a></li>
				</ul></li>
		</ul>
		<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav side-nav">

				<li <%if(uri.equals("/ProgettoIS/gestore-ordini.jsp")) { %>
					class="active" <% } %>><a href="gestore-ordini.jsp"><i
						class="fa fa-fw fa-dashboard"></i> Dashboard</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse --> </nav>


	</div>

	<div id="wrapper">



		<div id="page-wrapper">

			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							Benvenuto
							<%=bean.getNickName()%><small> Dashboard</small>
						</h1>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i> Dashboard
							</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->



				<div class="row">




					<div class="col-lg-3 col-md-6">
						<div class="panel panel-red">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-shopping-cart fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge"><%=ordini.size()%></div>
										<div>Ordini</div>
									</div>
								</div>
							</div>
							<a data-toggle="tab" href="#ordini">
								<div class="panel-footer">
									<span class="pull-left">Visualizza dettagli</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
				</div>

				<div class="tab-content">

					<!-- Tebella ordini -->
					<div id="ordini" class="tab-pane fade">
						<div class="row">


							<div class="col-lg-12">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h3 class="panel-title">
											<i class="fa fa-shopping-cart fa-fw"></i>Ordini
										</h3>
									</div>
									<div class="panel-body">
										<div class="table-responsive">
											<table class="table table-bordered table-hover table-striped">
												<thead>
													<tr>
														<th>N° ordine</th>
														<th>Utente</th>
														<th>Data ordine</th>
														<th>Totale (EUR)</th>
														<th>Stato</th>
														<th>Corriere</th>
														<th>N°Tracking</th>
														<th>Data consegna</th>
														<th>Modifica stato</th>
													</tr>
												</thead>
												<%
													if (ordini != null && ordini.size() != 0) {
														Iterator<?> it = ordini.iterator();
														while (it.hasNext()) {
															OrdineBean ord = (OrdineBean) it.next();
												%>
												<tbody>
													<tr>
														<td><%=ord.getNumOrdine()%></td>
														<td><%=ord.getUser()%></td>
														<% SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
														String data = sdf.format(ord.getData());
														%>
														<td><%=data%></td>
														<td><%=ord.getTotale()%></td>
														<%if(ord.getStato().equals("in preparazione")){ %>
														<td><%=ord.getStato()%></td>
														<td>in corso</td>
														<td>in corso</td>
														<td>in corso</td>
														<td><a
															href="ModificaStatoOrdineControl?cod=<%=ord.getNumOrdine() %>"
															class="btn btn-success">modifica</a></td>
														<%}else{ %>
														<td><%=ord.getStato()%></td>
														<td><%=ord.getCorriere() %></td>
														<td><%=ord.getTracking()%></td>
														<%  sdf = new SimpleDateFormat("dd/MM/yyyy");
														data = sdf.format(ord.getDataConsegna());%>
														<td><%=data%></td>
																<%} %>
													</tr>
												</tbody>

												<%
													}
													}
												%>
											</table>
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
			<!-- /.row -->

		</div>
		<!-- /.container-fluid -->

	</div>
	<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>