
<%
OrdineBean ordine = (OrdineBean) session.getAttribute("ordine");
UtenteBean utente = (UtenteBean) session.getAttribute("utente");
if(utente == null || ordine == null){
	response.sendRedirect("./index.jsp");
	return;
}

%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@page import="java.util.*, java.math.*, Model.*,Control.*,Bean.*"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" media="print"
	href="css/print.css" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">

<title>Fattura ordine N°ORDINE N°<%=ordine.getNumOrdine()%></title>
</head>
<body>

	<%@ include file="header.jsp"%>
	<br>
	<br>
	<div class="container">
		<div id="logo">
			<img class="img-responsive" src="immagini/brand-inverse.png">
		</div>
	</div>

	<div class="content">
		<div class="container">
			<div class="row">
				<div class="col-xs-6">
					<h2>
						ORDINE N°<%=ordine.getNumOrdine() %></h2>
					<div>
						<div>Music Paradise S.p.a</div>
						<div>
							Via Napoli 10,<br /> MA 90034, IT
						</div>
						<div>(081) 841-2021</div>
						<div>
							<a href="">musicparadise@gmail.com</a>
						</div>
					</div>
				</div>

				<div class="col-xs-6">
				
						<h2>DATI SPEDIZIONE</h2>
						<div>
							<span>Nome e cognome: </span><%=ordine.getIndirizzo().getNome() +" "+ordine.getIndirizzo().getCognome() %>
						</div>
						<div>
							<span>INDIRIZZO: </span><%=ordine.getIndirizzo().getIndirizzo() %></div>
						<div>
							<span>CAP: </span><%=ordine.getIndirizzo().getCap() %></div>
						<div>
							<span>CITTA': </span><%=ordine.getIndirizzo().getCittà() %></div>
						<div>
							<span>EMAIL: </span> <a href=""><%=utente.getEmail() %></a>
						</div>
						<div>
							<span>DATA ORDINE: </span><%=ordine.getData() %></div>
					
					</div>
				</div>
			</div>
			
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
						<%double prezzoTotale = 0; %>
						<%ArrayList<ProdottoOrdineBean> prodotto = ordine.getProdotti(); %>
						<%for(ProdottoOrdineBean temp : prodotto){%>
						<tr>
							<td><%=temp.getNome()%></td>
							<td><%=temp.getMarca()%></td>
							<td><%=temp.getStrumento()%></td>
							<td><%=temp.getPrezzo()%></td>
							<td><%=temp.getQuantità()%></td>
							<%
            
            double totale = temp.getPrezzo()*temp.getQuantità();%>
							<td><%=totale %> +</td>
						</tr>
						<%prezzoTotale = prezzoTotale+totale; %>
						<%} %>
						<tr>
							<td class="totale" colspan="5">SUBTOTAL</td>
							<td><%=prezzoTotale %> =</td>
						</tr>
						<tr>
							<%double iva = prezzoTotale*22/100;
          double totaleIva = prezzoTotale+iva; %>
							<td class="totale" colspan="5">IVA 22%</td>
							<td><%=iva %> +</td>
						</tr>
						<tr>
							<%double spedizione = 15.00; %>
							<td class="totale" colspan="5">SPEDIZIONE</td>
							<td><%=spedizione %> +</td>
						</tr>
						<tr>
							<td class="totale" colspan="5">TOTALE ORDINE</td>
							<td><%=new BigDecimal(prezzoTotale+iva+spedizione).setScale(2 , BigDecimal.ROUND_UP).doubleValue() %>EUR</td>
						</tr>

					</tbody>
				</table>


			</div>
				<h2>Dati pagamento</h2>
				<div>
							<span>Nome intestatario carta: </span><%=ordine.getCarta().getNomeProprietario() %>
						</div>
						<div>
							<span>N°Carta: </span><%=ordine.getCarta().getNumCarta()%></div>
						
					
				
			<p>
				<button class="btn btn-primary" onclick="myFunction()">Print
					this page</button>
			</p>
			</div>
			
	

		</div>
	


	<%@ include file="footer.jsp"%>
	<script>
function myFunction() {
    window.print();
}
</script>

</body>
</html>