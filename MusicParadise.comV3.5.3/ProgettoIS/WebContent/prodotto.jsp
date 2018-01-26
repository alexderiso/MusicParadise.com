<%
ProdottoCatalogoBean prd = (ProdottoCatalogoBean) session.getAttribute("prodotto");
Collection<?> prodotti = (Collection<?>) session.getAttribute("prodotti");
if(prd == null){
	response.sendRedirect("./index.jsp");
}
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@page  import="java.util.*, Model.*,Control.*"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/dettagliProdotto.css">
  <link rel="stylesheet" href="css/font-awesome.css">
  
<title><%=prd.getNome() %></title>
</head>
<body>

<%@ include file="header.jsp" %>
<% 
	int quant = 0;
	if (cart != null) { 
	   quant = cart.getQuantitàByCodice(prd.getCodice()); 
 	}
%>
      <div class="pld">
         <!--[if lt IE 8]>
         <!-- Product Simple Area Start -->
         <div class="product-simple-area">
            <div class="container">
               <div class="row">
                  <div class="col-md-6 col-sm-6">
                     <div class="single-product-image-inner">
                        <!-- Tab panes -->
                        <div class="tab-content">
                           <div  role="tabpanel" class="tab-pane active ex1" id="one">
                           <%ArrayList<String> foto = prd.getFoto(); %>
                              <img src="<%=foto.get(0)%>" alt="">
                           </div>
                           <div role="tabpanel" class="tab-pane ex1" id="two">
                              <img src="<%=foto.get(1)%>"  alt="">
                           </div>
                           <div role="tabpanel" class="tab-pane ex1" id="three">
                              <img  src="<%=foto.get(2)%>" alt="">
                           </div>
                        </div>
                        <!-- Nav tabs -->
                        <ul class="product-tabs" role="tablist">
                           <li role="presentation" class="active"><a href="#one" aria-controls="one" role="tab" data-toggle="tab"><img src="<%=foto.get(0)%>" alt="" width="170" height="150"></a></li>
                           <li role="presentation"><a href="#two" aria-controls="two" role="tab" data-toggle="tab"><img src="<%=foto.get(1)%>" alt="" width="170" height="150"></a></li>
                           <li role="presentation"><a href="#three" aria-controls="three" role="tab" data-toggle="tab"><img src="<%=foto.get(2)%>" alt="" width="170" height="150"></a></li>
                        </ul>
                     </div>
                  </div>
                  <div class="clearfix visible-xs"></div>
                  <div class="col-md-offset-1 col-md-5 col-sm-6">
                     <div class="single-product-details">
                        <div class="list-pro-rating">
                           <i class="fa fa-star icolor"></i>
                           <i class="fa fa-star icolor"></i>
                           <i class="fa fa-star icolor"></i>
                           <i class="fa fa-star icolor"></i>
                           <i class="fa fa-star"></i>
                        </div>
                        <h2><%=prd.getNome() %></h2>
                        <%if (cart != null) { %>
                        	<%if(prd.getNumDisp() - cart.getQuantitàByCodice(prd.getCodice()) <= 0){%>
                        	<h4>NON DISPONIBILE</h4>
                       		<%}else{ %>
                        	<h4>DISPONIBILE</h4>
                        	<%} %>
                        <%} %>
                        <p></p>
               
                         <div class="single-product-price">
                           <h2><%=prd.getPrezzo()%> EURO</h2>
                          
                        </div>
                     
                       
                        <div class="product-attributes clearfix">
                          
                           <ul>
                            <li>Caratteristiche:</li>
                           <li>Marca: <%=prd.getMarca() %></li>
                           <li>Peso: <%=prd.getPeso() %></li>
                           <li>Colore:<%=prd.getColore() %> </li> 
                           <li>
                           </ul>
                          <br>
                           
                          
                  
                           <%if(prd.getNumDisp() - quant <=0){ %>
                           <span>
                           <a class="cart-btn btn-danger" href="">
                           <span><i class="fa fa-cart-plus"></i> NON DISPONIBILE</span>
                           </a>
                           </span>
                           <%}else{ %>
                   
                           <span>
                           <span id="txtAgg"></span>
                           <a class="cart-btn btn-success" id="agg">
                           <span><i class="fa fa-cart-plus"></i> Aggiungi al carrello</span>
                           </a>
                           </span>
<script>
                           var numC = 0;
                     	  <% if (cart != null) { %>                          
                     		var numC = <%=cart.countingPrds()%>
                     	<% } %>
                           $('#agg').click(function() { 
                        	   numC = numC + 1;
                        	   $("#iconCartNum").html(numC); 
                           });
                      	
                          
                      $( document ).ready(function() {
                    	
							$('#agg').click(function() {
								$('#txtAgg').hide().html("Aggiunto al carrello").show("slow");
								 aggProdotto();
						});
						function aggProdotto() {
							var quantità = $(".product-quantity").val();
								$.get("AggiungiProdottoAlCarrelloControl", {quantità: "1"},
										function (resp) { 
											$("#disp").html("Ancora disponibili: "); 
											
											if(resp != "fineDisp") {
												$("#dispNum").html(resp);
											}
											if (resp == "fineDisp") { 
												location.reload(); 
											} 
										});
						}
                      });
</script>
 
  
              
                           <%} %>
                        </div>
                        <div class="add-to-wishlist">
                           
                           <span id="disp">Ancora disponibili: </span><span id="dispNum"><%=prd.getNumDisp() - quant %></span>
                          
                           
                        </div>
                        
                     </div>
                  </div>
               </div>
               
               
               <div class="row">
                  <div class="col-md-9">
                     <div class="p-details-tab-content section-padding-2">
                        <div class="p-details-tab">
                           <ul class="p-details-nav-tab" role="tablist">
                              <li role="presentation" class="active">
                                 <a href="#more-info" aria-controls="more-info" role="tab" data-toggle="tab">Descrizione prodotto</a>
                              </li>
                             
                           </ul>
                        </div>
                        <div class="clearfix"></div>
                        <div class="tab-content review">
                           <div role="tabpanel" class="tab-pane active" id="more-info">
                              <p><%=prd.getDescrizione() %></p>
                           </div>
                          
                           
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
         <!-- Product Simple Area End -->
         
         
         <!--Related Product Area Start-->

      </div>
      <div class="featured-product-area section-padding-2">
            <div class="container">
               <div class="row">
                  <div class="section-title">
                     <h2>Prodotti simili</h2>
                  </div>
                  <%
       if(prodotti != null && prodotti.size() != 0){
    	   Iterator<?> it=prodotti.iterator();
    	   while(it.hasNext()){
    		   ProdottoCatalogoBean prodotto = (ProdottoCatalogoBean) it.next();
    		   ArrayList<String> foto2 = prodotto.getFoto();
    		   if(prd.getCodice() != prodotto.getCodice()){
 %>
                  <div class="col-lg-3 col-sm-6  ">
                     <!-- Thumbnail Images -->
                     
                        <img class="img-prodotti" src="<%=foto2.get(1) %>" alt=""> 
                    
                       
                        <p><strong><%=prodotto.getNome()%></strong></p>
        
                        <a class = "btn btn-primary" href="ProdottoControl?prodotto=<%=prodotto.getCodice()%>" >acquista</a>
                    
                  </div>
                  <%}}} %>
               </div>
            </div>
         </div>
      <%@ include file="footer.jsp" %>
      
      <script src='js/jquery.zoom.js'></script>
      <script>
		$(document).ready(function(){
			$('.ex1').zoom();
		});
	</script>
   </body>
   <%System.out.println("QUANTITA PROVA NELLA PAGE:" + prd.getQuantAgg()); %>
</html>