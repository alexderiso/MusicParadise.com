<% 
UtenteBean utente = (UtenteBean) session.getAttribute("utente");
if(utente == null){
	response.sendRedirect("./index.jsp");
	return;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%@ include file="header.jsp" %>
<br>
<br>
<br>

<form class="form-horizontal" method="post" action="AggiungiCartaControl" onsubmit="return isOk();">

<div class="container">
    <div class="row">
        <div class="col-xs-12 col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h3 class="text-center">Payment Details</h3>
                        <img class="img-responsive cc-img" src="http://www.prepbootstrap.com/Content/images/shared/misc/creditcardicons.png">
                    </div>
                </div>
                <div class="panel-body">
                    <form role="form">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="form-group">
                                    <label>CARD NUMBER</label>
                                    <div class="input-group">
                                        <input type="text" name="numCarta" class="form-control" class="form-control"
									id="inputCard" placeholder="Valid Card Number" />
									<label class="col-sm-12 labNamePag" id="txtErrCard"></label>
                                        <span class="input-group-addon"><span class="fa fa-credit-card"></span></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="form-group">
                                    <label>NOME PROPRIETARIO</label>
                                    <div class="input-group">
                                        <input type="text" name="nomProprietario" class="form-control" class="form-control" />
									
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-7 col-md-7">
                                <div class="form-group">
                                   <label for="expityMonth"> SCADENZA </label>
									<p>
										<select name="mese">
											<%for(int i = 1; i<=12;i++){ %>

											<option><%=i %></option>
											<%} %>
										</select>/ <select name="anno">
											<%for(int i = 17; i<=22;i++){ %>

											<option><%=i %></option>
											<%} %>
										</select>
									</p>
                                </div>
                            </div>
                            <div class="col-xs-5 col-md-5 pull-right">
                                <div class="form-group">
                                    <label>CV CODE</label>
                                    <input type="password" class="form-control" id="inputCode" placeholder="CV" />
                                    <label
										class="col-sm-12 labNamePag" id="txtErrCode"></label>
                                </div>
                            </div>
                        </div>
                        
                    </form>
                </div>
                <div class="panel-footer">
                    <div class="row">
                        <div class="col-xs-6 col-md-6">
                          <a class="btn btn-default" href="profiloCliente.jsp">annulla</a> 
                        </div>
                        <div class="col-xs-6 col-md-6">
                           <button type="submit" class="btn btn-success " id="pagamento">aggiungi carta</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</form>

<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/validate.js"></script>

	<script>
function isOk() {
	if(ValidateCard(document.getElementById("inputCard"),document.getElementById("txtErrCard"))
			&& ValidateCode(document.getElementById("inputCode"),document.getElementById("txtErrCode"))
			) {
		
		return true;
		
	}
	
	return false;
}
</script>

</body>
</html>