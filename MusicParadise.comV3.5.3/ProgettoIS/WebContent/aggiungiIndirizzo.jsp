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
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style2.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Aggiungi indirizzo</title>
</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="container-fluid">
		<form class="form-horizontal" method="post" action="AggIndirizzoControl"
			onsubmit="return isOk();">


			<div class="row">

				<div class="container">
					<h2>Inserisci Indirizzo</h2>
				</div>

				<div class="col-xs-12">


				
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Nome</label>
						<label class="col-sm-2 labNamePag" id="txtErrNome"></label>
						<div class="col-sm-10">

							<input type="text" name="paramOrder" class="form-control"
								id="inputNome" maxlength="20">

						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">Cognome</label>
						<label class="col-sm-2 labNamePag" id="txtErrCognome"></label>
						<div class="col-sm-10">

							<input type="text" name="paramOrder" class="form-control"
								id="inputCognome" maxlength="20">

						</div>
					</div>
					
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">Via</label>
						<label class="col-sm-2 labNamePag" id="txtErrIndirizzo"></label>
						<div class="col-sm-10">

							<input type="text" name="paramOrder" class="form-control"
								id="inputIndirizzo" maxlength="20">

						</div>
					</div>


					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">Città</label>
						<label class="col-sm-2 labNamePag" id="txtErrCittà"></label>
						<div class="col-sm-10">

							<input type="text" name="paramOrder" class="form-control"
								id="inputCittà" maxlength="20">

						</div>
					</div>

					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">Cap</label>
						<label class="col-sm-6 labNamePag" id="txtErrCap"></label>
						<div class="col-sm-10">

							<input type="text" name="cap" class="form-control" id="inputCap" maxlength="5">

						</div>
					</div>

					

					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">Telefono</label>
						<label class="col-sm-2 labNamePag" id="txtErrTelefono"></label>
						<div class="col-sm-10">

							<input type="text" name="paramOrder" class="form-control"
								id="inputTelefono" maxlength="10">

						</div>
					</div>

				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">


						<a href="profiloCliente.jsp"><input value="Annulla"
							class="btn btn-default"></input></a> <input type="submit"
							value="aggiungi" class="btn btn-default"></input>

					</div>

				</div>
			</div>
		</form>
	</div>




	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/validate.js"></script>

	<script>
function isOk() {
	if( ValidateLetter(document.getElementById("inputNome"),document.getElementById("txtErrNome"))
			&& ValidateLetter(document.getElementById("inputCognome"),document.getElementById("txtErrCognome")) 
			&& ValidateAlfa(document.getElementById("inputIndirizzo"),document.getElementById("txtErrIndirizzo"))
			&& ValidateLetter(document.getElementById("inputCittà"),document.getElementById("txtErrCittà"))
			&& ValidateCap(document.getElementById("inputCap"),document.getElementById("txtErrCap"))
			&& ValidatePhone(document.getElementById("inputTelefono"),document.getElementById("txtErrTelefono"))
			) {
		
		return true;
		
	}
	
	return false;
}
</script>




</body>
</html>