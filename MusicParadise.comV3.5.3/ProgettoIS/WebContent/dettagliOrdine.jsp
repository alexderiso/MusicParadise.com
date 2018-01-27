
<%
UtenteBean bean = (UtenteBean) session.getAttribute("utente-gestore");
Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
if((bean == null)||(!adminRoles.booleanValue())){
	String errore = "Accesso non consentito";
	request.getSession().setAttribute("errore",errore);
	response.sendRedirect("./404.jsp");
	return;
}
String uri = request.getRequestURI();
OrdineBean ordine = (OrdineBean) session.getAttribute("dettagli");
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
<title>Gestore Ordini</title>
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/sb-admin.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
<%@page import="java.util.*, java.math.*, java.text.*, Model.*,Control.*,Bean.*"%>
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
						
					</div>
				</div>
				<!-- /.row -->

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
						
					
				
			
			</div>
			
	

		</div>

				
					
					
					
					
					
				</div>

			</div>
			<!-- /.row -->

		</div>
		<!-- /.container-fluid -->

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>