
<%
	ArrayList<ProdottoCatalogoBean> prodottiOrdine = (ArrayList<ProdottoCatalogoBean>) session.getAttribute("prodottiOrdine");
	ClienteBean utente = (ClienteBean) session.getAttribute("utente");
	if (utente == null || prodottiOrdine == null) {
		response.sendRedirect("./index.jsp");
		return;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@page import="java.util.*, Model.*,Control.*"%>
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
<form action="ConfermaOrdineControl" method="post">
	<div class="container">

		<h2>Scegli indirizzo di spedizione</h2>


		<%
			if (utente.getIndirizzi().size() > 0) {
		%>


		<div class="form-group">

			<select class="form-control" name ="indirizzo">
				<%
					for (IndirizzoBean temp : utente.getIndirizzi()) {
				%>
				<option><%=temp.toString()%></option>
				<%
					}
				%>
			</select>
		</div>
		<a href="aggiungiIndirizzo.jsp">Aggiungi nuovo indirizzo</a>

		<%
			} else {
		%>
		<fieldset disabled>
			<div class="form-group">
				<select id="disabledSelect" class="form-control">
					<option>Nessun indirizzo</option>
				</select>
			</div>
		</fieldset>
		<a href="aggiungiIndirizzo.jsp">Aggiungi nuovo indirizzo</a>
		<%
			}
		%>


		<h2>Scegli carta di credito</h2>


		<%
			if (utente.getCarte().size() > 0) {
		%>
		<div class="form-group">
			<select class="form-control" name = "carta">
				<%
					for (CartaBean temp : utente.getCarte()) {
				%>

				<option><%=temp.toString()%></option>
				<%
					}
				%>
			</select>
		</div>
		<a href="aggiungiCarta.jsp">Aggiungi nuova carta</a>
		<%
			} else {
		%>
		<fieldset disabled>
		
			<div class="form-group">
				<select id="disabledSelect" class="form-control">
					<option>Nessua carta</option>

				</select>
			</div>
		</fieldset>
		<a href="aggiungiCarta.jsp">Aggiungi nuova carta</a>


		<%
			}
		%>
	</div>
	




	<div class="container">
		<h2>Dettagli ordine</h2>
	</div>


	<div class="content">
		<div class="container">
			<div class="table-responsive">
				<table class="table">
					<thead>
						<tr>
							<th>PRODOTTO</th>
							<th>MARCA</th>
							<th>DESCRIZIONE</th>
							<th>PREZZO</th>
							<th>QUANTITA'</th>
							<th>TOTALE</th>
						</tr>
					</thead>
					<tbody>
						<%
							double prezzoTotale = 0;
						%>
						<%
							for (int i = 0; i < prodottiOrdine.size(); i++) {
								ProdottoCatalogoBean prodotto = prodottiOrdine.get(i);
						%>
						<tr>
							<th><%=prodotto.getNome()%></th>
							<th><%=prodotto.getMarca()%></th>
							<th><%=prodotto.getStrumento()%></th>
							<th><%=prodotto.getPrezzo()%></th>
							<th><%=prodotto.getQuantAgg()%></th>
							<%
								double totale = prodotto.getPrezzo() * prodotto.getQuantAgg();
							%>
							<td><%=totale%> +</td>
						</tr>
						<%
							prezzoTotale = prezzoTotale + totale;
						%>
						<%
							}
						%>
						<tr>
							<td class="totale" colspan="5">SUBTOTAL</td>
							<td><%=prezzoTotale%> =</td>
						</tr>
						<tr>
							<%
								double iva = prezzoTotale * 22 / 100;
								double totaleIva = prezzoTotale + iva;
							%>
							<td class="totale" colspan="5">IVA 22%</td>
							<td><%=iva%> +</td>
						</tr>
						<tr>
							<%
								double spedizione = 15.00;
							%>
							<td class="totale" colspan="5">SPEDIZIONE</td>
							<td><%=spedizione%> +</td>
						</tr>
						<tr>
							<td class="totale" colspan="5">TOTALE ORDINE</td>
							<td><%=prezzoTotale + iva + spedizione%> EUR</td>
						</tr>

					</tbody>
				</table>


			</div>

			<%
				if (utente.getCarte().size() == 0 || utente.getIndirizzi().size() == 0) {
			%>
			<fieldset disabled>
			<div class="form-group">
				<p>
					<a class="btn btn-primary"  >conferma
						ordine</a>
				</p>
				</div>
			</fieldset>
			<%
				} else {
			%>
			<p>
				<button class="btn btn-primary" type="submit" >conferma
					ordine</button>
			</p>
			<%
				}
			%>

		</div>
	</div>
	</form>








</body>
</html>