<%

UtenteBean utente= (UtenteBean)session.getAttribute("utente");

%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@page  import="java.util.*, Model.*,Control.*"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="css/cart.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
 <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
<title>Carrello</title>
</head>
<body>
<%@ include file="header.jsp" %>

<div class="content">


<br>
<br>
<br>
<br>
<br>
<br>
<%double somma=0; %>
                <%if (cart != null){
                	
                	ArrayList<ProdottoCatalogoBean> lista = cart.getProducts();
                	if(lista.size() > 0){%>
                <div class="container">
                	       <div class="shopping-cart">

                	  <div class="column-labels">
                	    <label class="product-image">Immagine</label>
                	    <label class="product-details">Prodotto</label>
                	    <label class="product-price">Prezzo</label>
                	    <label class="product-quantity">Quantità</label>
                	    <label class="product-removal">Rimuovi</label>
                	    <label class="product-line-price">Totale</label>
                	  </div>
                	  
                	  
                	  
                 <% for(int i= 0; i < lista.size(); i++) { %>
       
 
  
  <div class="product">
    <div class="product-image">
      <img class="img-responsive" src=<%=lista.get(i).getFoto().get(1)%>>
    </div>
    <div class="product-details">
      <div class="product-title"><%=lista.get(i).getNome()%></div>
      <p class="product-description"><%=lista.get(i).getDescrizione()%></p>
      <p class="product-cod"><%=lista.get(i).getCodice()%></p>
    </div>
    
  
   
    
  
    
    <div class="product-price"><%=lista.get(i).getPrezzo()%></div>
 
    
    <div class="product-quantity">
      <input id="quantità" type="number" name="<%=lista.get(i).getCodice()%>" value="<%=lista.get(i).getQuantAgg()%>" min="1" max="<%=lista.get(i).getNumDisp()%>" onchange="aggQ()">
    </div>
    
    <script>$("[type='number']").keypress(function (evt) {
        evt.preventDefault();
    });</script>
    <% somma = somma + lista.get(i).getPrezzo()*lista.get(i).getQuantAgg();%>
    <div class="product-removal">
    
      <button class="remove-product" type="button" onclick="remove(<%=lista.get(i).getCodice()%>)">Remove</button>
     
    </div>
    <div class="product-line-price"><%=lista.get(i).getPrezzo()*lista.get(i).getQuantAgg()%></div>
    
     <%System.out.println("JSP: "+ lista.get(i).getCodice()); %>
     <%System.out.println("JSP2: "+ lista.size()); %>
  </div>
  
    <script> 
  	function remove(codice) {
	      
  		$.get("RimuoviProdottoCarrelloControl", { id: codice }, 
				   function (resp) {
			   		if(resp == "finitiPrd") { location.reload();  }
		   });
		   removeItem($(event.target));
		   
	   }

var taxRate = 0.22;
var shippingRate = 15.00; 
var fadeTime = 300;



/* Recalculate cart */
function recalculateCart()
{
  var subtotal = 0;
  
  /* Sum up row totals */
  $('.product').each(function () {
    subtotal += parseFloat($(this).children('.product-line-price').text());
  });
  
  /* Calculate totals */
  var tax = subtotal * taxRate;
  var shipping = (subtotal > 0 ? shippingRate : 0);
  var total = subtotal + tax + shipping;
  
  /* Update totals display */
  $('.totals-value').fadeOut(fadeTime, function() {
    $('#cart-subtotal').html(subtotal.toFixed(2));
    $('#cart-tax').html(tax.toFixed(2));
    $('#cart-shipping').html(shipping.toFixed(2));
    $('#cart-total').html(total.toFixed(2));
    if(total == 0){
      $('.checkout').fadeOut(fadeTime);
    }else{
      $('.checkout').fadeIn(fadeTime);
    }
    $('.totals-value').fadeIn(fadeTime);
  });
}
	
function removeItem(removeButton){
  /* Remove row from DOM and recalc cart total */
  var productRow = $(removeButton).parent().parent();
  productRow.slideUp(fadeTime, function() {
    productRow.remove();
    recalculateCart();
  });

}

function aggQ() {
	var quant = event.target.value;
	var cod = $(event.target).attr('name');
	$.get("AggiornaQuantitàProdottoCarrello", { quantità: quant, id: cod });
}



$('.product-quantity input').change( function() {
	updateQuantity(this);
	
});

function updateQuantity(quantityInput)
{
  /* Calculate line price */
  var productRow = $(quantityInput).parent().parent();
  var price = parseFloat(productRow.children('.product-price').text());
  var quantity = $(quantityInput).val();
  var linePrice = price * quantity;
  
  /* Update line price display and recalc cart totals */
  productRow.children('.product-line-price').each(function () {
    $(this).fadeOut(fadeTime, function() {
      $(this).text(linePrice.toFixed(2));
      recalculateCart();
      $(this).fadeIn(fadeTime);
    });
  });  
}


</script>


 <% }%>
  <div class="totals">
    <div class="totals-item">
      <label>Subtotal</label>
      <div class="totals-value" id="cart-subtotal"><%=somma%></div>
    </div>
    <div class="totals-item">
      <label>IVA (22%)</label>
       <% double iva = somma*22/100;
       somma = somma+15;%>
      <div class="totals-value" id="cart-tax"><%=iva%></div>
    </div>
    <div class="totals-item">
      <label>Spedizione</label>
      <div class="totals-value" id="cart-shipping">15.00</div>
    </div>
    <div class="totals-item totals-item-total">
      <label>Grand Total</label>
      <div class="totals-value" id="cart-total"><%=somma+iva%></div>
    </div>
  </div>
       <%if(utente != null){%>
      <a class="checkout" href="CheckoutControl">Checkout</a>
      <%}else{ %>
      <button class="no-checkout" data-toggle="tooltip" data-placement="bottom" title="Devi essere registrato">Checkout</button>
      <%} %>
</div>
</div>

	 <%}else{%>
<div class="container">
<h2>Nesun prodotto nel carello</h2>
</div>

<br>
<br>
<br>
<div class="container">
       <div class="shopping-cart">

  <div class="column-labels">
    <label class="product-image">Image</label>
    <label class="product-details">Product</label>
    <label class="product-price">Price</label>
    <label class="product-quantity">Quantity</label>
    <label class="product-removal">Remove</label>
    <label class="product-line-price">Total</label>
  </div>
  
 




  
<div class="totals">
    <div class="totals-item">
      <label>Subtotal</label>
      <div class="totals-value" id="cart-subtotal">0</div>
    </div>
    <div class="totals-item">
      <label>Tax (22%)</label>
      <div class="totals-value" id="cart-tax">0</div>
    </div>
    <div class="totals-item">
      <label>Shipping</label>
      <div class="totals-value" id="cart-shipping">0</div>
    </div>
    <div class="totals-item totals-item-total">
      <label>Grand Total</label>
      <div class="totals-value" id="cart-total">0</div>
    </div>
  </div>
  </div>
  </div>
  <%}%>
  <%}else{ %>

<div class="container">
<h2>Nesun prodotto nel carello</h2>
</div>

<br>
<br>
<br>
<div class="container">


       <div class="shopping-cart">

  <div class="column-labels">
    <label class="product-image">Image</label>
    <label class="product-details">Product</label>
    <label class="product-price">Price</label>
    <label class="product-quantity">Quantity</label>
    <label class="product-removal">Remove</label>
    <label class="product-line-price">Total</label>
  </div>
  
 




  
<div class="totals">
    <div class="totals-item">
      <label>Subtotal</label>
      <div class="totals-value" id="cart-subtotal">0</div>
    </div>
    <div class="totals-item">
      <label>Tax (22%)</label>
      <div class="totals-value" id="cart-tax">0</div>
    </div>
    <div class="totals-item">
      <label>Shipping</label>
      <div class="totals-value" id="cart-shipping">0</div>
    </div>
    <div class="totals-item totals-item-total">
      <label>Grand Total</label>
      <div class="totals-value" id="cart-total">0</div>
    </div>
  </div>
</div>
</div>

<%} %>
</div>



<!-- Footer -->
    <%@ include file="footer.jsp" %>


<script>

$(function () {
	  $('[data-toggle="tooltip"]').tooltip()
	});
                   
</script>


</body>
</html>