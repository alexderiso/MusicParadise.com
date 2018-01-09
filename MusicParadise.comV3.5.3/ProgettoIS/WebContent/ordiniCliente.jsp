
<%
ArrayList<OrdineBean> ordiniUtente = (ArrayList<OrdineBean>)session.getAttribute("ordiniUtente");
UtenteBean utente = (UtenteBean) session.getAttribute("utente");
if(utente == null){
	response.sendRedirect("./index.jsp");
	return;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@page import="java.util.*,java.text.*, Model.*,Control.*"%>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ordini</title>
</head>
<body>


	<%@ include file="header.jsp"%>
	<div class="content">
		<br>
		<div id="page-wrapper">

			<div class="container-fluid">


				<!-- Tebella ordini -->

				<div class="row">


					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Ordini</h3>
							</div>
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-bordered table-hover table-striped">
										<thead>
											<tr>
												<th>N° ordine</th>
												<th>Data ordine</th>
												<th>Totale (EUR)</th>
												<th>Stato</th>
												<th>Corriere</th>
												<th>N°Tracking</th>
												<th>Data consegna</th>
												<th>Fattura ordine</th>
											</tr>
										</thead>
										<% if(ordiniUtente != null && ordiniUtente.size() != 0){
                                        	for(int i= 0; i < ordiniUtente.size();i++){
                                        		OrdineBean ord = (OrdineBean) ordiniUtente.get(i);
                                        		%>
										<tbody>
											<tr>
												<td><%=ord.getNumOrdine()%></td>
												<td><%=ord.getData()%></td>
												<td><%=ord.getTotale()%></td>
												<%if(ord.getStato().equals("in preparazione")){ %>
												<td><%=ord.getStato()%></td>
												<td>in corso</td>
												<td>in corso</td>
												<td>in corso</td>
												
												<%}else{ %>
														<td><%=ord.getStato()%></td>
														<td><%=ord.getCorriere() %></td>
														<td><%=ord.getTracking()%></td>
														<% SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
														String data = sdf.format(ord.getDataConsegna());%>
														<td><%=data%></td>
																<%} %>
												<td><a class="btn btn-success"
													href="RicercaOrdineControl?codOrdine=<%=ord.getNumOrdine()%>">visualizza
														fattura</a></td>
											</tr>
										</tbody>

										<%}}else{%>
										<p>Non ci sono ordini disponibili</p>
										<% } %>
									</table>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>



		<!-- /.row -->


	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>