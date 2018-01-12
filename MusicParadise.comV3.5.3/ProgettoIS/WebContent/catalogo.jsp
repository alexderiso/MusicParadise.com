<%
   Collection<?> prodotti = (Collection<?>) session.getAttribute("prodotti");
ArrayList<ProdottoCatalogoBean> catalogo = (ArrayList<ProdottoCatalogoBean>) session.getAttribute("catalogo");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@page  import="java.util.*, Model.*,Control.*"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style2.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
    <title>Music Paradise</title>
</head>
<body>
<%@ include file="header.jsp" %>


<!-- Page Content -->
<br>
<br>
<div class="content">
    <div class="container">
<%
       if(prodotti != null && prodotti.size() != 0){
    	   Iterator<?> it=prodotti.iterator();
    	   while(it.hasNext()){
    		   ProdottoCatalogoBean prd = (ProdottoCatalogoBean) it.next();
    		   ArrayList<String> foto = prd.getFoto();
    		   System.out.println(foto.get(0));
 %>
        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
            <br>
            
            </div>
        </div>
        <!-- /.row -->

        <!-- Project One -->
     
        <div class="row">
            <div class="col-md-7 ex1">
   
                    <img class="img-responsive " src="<%=foto.get(1)%>" alt="">
        
            </div>
            <div class="col-md-5">
            
        <br>
        <br>
        <br>
        <br>
        <br>
                <h3><%=prd.getNome()%></h3>
                <p>Marca:<b><%=prd.getMarca()%></b></p>
                <p>Descrizione: <%=prd.getDescrizione()%></p>
             
                 <p>Prezzo: <%=prd.getPrezzo()%></p>
                 
                <p>Venduta e spedita da MusicParadispe s.p.a</p>
                
                <%if (cart != null) { %>
                        	<%if(prd.getNumDisp() - cart.getQuantitàByCodice(prd.getCodice()) <= 0){%>
                 <p style="color:red;"><b>NON DISPONIBILE</b></p>
                <%}else{ %>
                 <p style="color:green;"><b>DISPONIBILE</b></p>
                <p>Ancora disponibili: <%=prd.getNumDisp() %></p>
          
                 <%} }else{%>
                 <%if(prd.getNumDisp() > 0){ %>
                 <p style="color:green;"><b>DISPONIBILE</b></p>
                <%}else{ %>
                <p style="color:red;"><b>NON DISPONIBILE</b></p>
                <%}} %>
                 
                 
                <a class="btn btn-primary" href="RicercaProdottoControl?prodotto=<%=prd.getCodice()%>">Vai al prodotto <span class="glyphicon glyphicon-chevron-right"></span></a>
            </div>
        </div>
         <br>
          <br>
          <hr>
<%}}else{ %>
<br>
<br>
<br>
    <h3>Nessun prodotto trovato</h3>
    <a href="index.jsp"><h3>Vai a home</h3></a>
    
        <% } %>
        <hr>

      

        <hr>

        <!-- Footer -->
       
    </div>
    <!-- /.container -->
    </div>
    <%@ include file="footer.jsp" %>
   
   <script src='js/jquery.zoom.js'></script>
      <script>
		$(document).ready(function(){
			$('.ex1').zoom();
		});
	</script>
</body>
</html>