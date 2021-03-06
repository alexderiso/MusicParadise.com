
<%
	ClienteBean cliente = (ClienteBean) session.getAttribute("utente");

	if (cliente == null) {
		response.sendRedirect("./index.jsp");
		return;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Il mio profilo</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container">
		<div class="row">
			<br> <br> <br>
			<div class="col-lg-12">


				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">Il mio profilo</h3>
					</div>
					<div class="panel-body">
						<div class="row">



							<div class=" col-lg-12">
								<table class="table table-user-information">
									<tbody>
										<tr>
											<td>Nome:</td>
											<td><%=bean.getNome()%></td>
										</tr>
										<tr>
											<td>Cognome</td>
											<td><%=bean.getCognome()%></td>
										</tr>
										<tr>
											<td>Nickname</td>
											<td><%=bean.getNickName()%></td>
										</tr>

										<tr>
										<tr>
											<td>Email</td>
											<td><%=bean.getEmail()%></td>
										</tr>
										<tr>
											<td>Password</td>
											<td>*******</td>
										</tr>


									</tbody>
								</table>

							</div>
						</div>

					</div>


				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-5  toppad  pull-right col-md-offset-3 ">



				<br> <br> <br>

			</div>
			<div class="col-lg-12">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">I miei indirizzi</h3>
					</div>
					<div class="panel-body">
						<div class="row">



							<div class="col-lg-12">
								<div class="table-responsive">

									<table class="table table-hover">
										<thead>
											<tr>
												<td>Nome e Cognome</td>
												<td>Indirizzo</td>
												<td>Citt�</td>
												<td>Cap</td>
												<td>Telefono</td>
												<td>Rimuovi</td>
											</tr>
										</thead>
										<%
											if ((cliente.getIndirizzi() != null)) {
										%>
										<%
											ArrayList<IndirizzoBean> indirizzi = cliente.getIndirizzi();
										%>
										<%
											for (IndirizzoBean temp : indirizzi) {
										%>
										<tbody>
											<tr>

												<td><%=temp.getNome() + " " + temp.getCognome()%></td>
												<td><%=temp.getIndirizzo()%></td>
												<td><%=temp.getCitt�()%></td>
												<td><%=temp.getCap()%></td>
												<td><%=temp.getTelefono()%></td>
												<td><a href="RimuoviIndirizzoControl?cod=<%=temp.getCodice()%>">Remove</a>
											</tr>
										</tbody>
								
										<%
											}
											}
										%>




									</table>



									<div>
										<a href="aggiungiIndirizzo.jsp" type="button"
											class="btn btn-primary">inserisci indirizzo</a>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



	<div class="container">
		<div class="row">
			<div class="col-md-5  toppad  pull-right col-md-offset-3 ">



				<br> <br> <br>

			</div>
			<div class="col-lg-12">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">Le mie carte</h3>
					</div>
					<div class="panel-body">
						<div class="row">



							<div class="col-lg-12">
								<div class="table-responsive">
									<table class="table table-hover">
										<thead>
											<tr>
												<th>N� Carta</th>
												<th>Scadenza</th>
												<th>Proprietario</th>
												<th>Rimuovi</th>

											</tr>


										</thead>
										<%
											if ((cliente.getCarte() != null)) {
										%>
										<%
											ArrayList<CartaBean> carte = cliente.getCarte();
										%>
										<%
											for (CartaBean carta : carte) {
										%>
										<tbody>
											<tr>

												<td><%=carta.getNumCarta()%></td>
												<td><%=carta.getScadenza()%></td>
												<td><%=carta.getNomeProprietario()%></td>
												<td><a href="RimuoviCartaControl?cod=<%=carta.getCodice()%>">Remove</a>
											</tr>
										</tbody>
		

										<%
											}
										
											}
										%>


									</table>


									<div>
										<a href="aggiungiCarta.jsp" type="button"
											class="btn btn-primary">inserisci carta</a>
									</div>
								</div>


							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>





</body>
</html>