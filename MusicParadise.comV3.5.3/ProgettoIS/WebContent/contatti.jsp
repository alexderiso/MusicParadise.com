
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/font-awesome.css">
    <link rel="stylesheet" href="css/agency.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
	<title>Contatti</title>
</head>
<body>
<%@ include file="header.jsp" %>
	
	<div class ="container">
		<div class="row">
			<div class="col-md-4-offset-4 col-sx-4">
				<h3>Dove Siamo</h3>
				<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d6042.643855254724!2d14.822641875352163!3d40.77693621729949!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x133bc53ed8628c47%3A0xb00cd05d676d7ed!2s84080+Calvanico+SA!5e0!3m2!1sit!2sit!4v1493293306286" width="100%" height="400vh" frameborder="0" style="border:0" allowfullscreen></iframe>
			</div>
			
			<div class="col-md-4-offset-4 col-sx-4">
				<h3>Contattaci</h3>
				<form>
  					<div class="form-group">
    					<label for="userName">Nome</label>
    					<input type="text" class="form-control" id="userName" placeholder="Il tuo nome">
  					</div>
  					
  					<div class="form-group">	
    					<label for="userCognome">Cognome</label>
  						<input type="text" class="form-control" id="userCognome" placeholder="Il tuo cognome">
  					</div>
  					
  					<div class="form-group">
   						<label for="userEmail">Email</label>
    					<input type="email" class="form-control" id="userEmail" placeholder="La tua email">
    					<small class="form-text text-muted">Non diamo la tua email a nessuno.</small>
 					</div>
  
  					<div class="form-group">
    					<label for="contenuto">Cosa vuoi dirci?</label>
   						<textarea class="form-control" id="contenuto" rows="3"></textarea>
  					</div>
  
  					<button type="submit" class="btn btn-primary">Invia</button>
				</form>
			</div>
		</div>
	</div>
	<br>
	 <!-- Team Section -->
    <section id="team" class="bg-light-gray">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="section-heading">Il nostro team</h2>
                    <h3 class="section-subheading text-muted"></h3>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-3">
                    <div class="team-member">
                        <img src="" class="img-responsive img-circle jquery" alt="">
                        <h4>Vincenzo Pandolfo</h4>
                        <p class="text-muted"></p>
                        <ul class="list-inline social-buttons jquery">
                            <li><a href="#"><i class="fa fa-twitter"></i></a>
                            </li>
                            <li><a href="#"><i class="fa fa-facebook"></i></a>
                            </li>
                            <li><a href="#"><i class="fa fa-linkedin"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="team-member">
                        <img src="" class="img-responsive img-circle jquery" alt="">
                        <h4>Domenico Pannone</h4>
                        <p class="text-muted"></p>
                        <ul class="list-inline social-buttons jquery">
                            <li><a href="#"><i class="fa fa-twitter"></i></a>
                            </li>
                            <li><a href="#"><i class="fa fa-facebook"></i></a>
                            </li>
                            <li><a href="#"><i class="fa fa-linkedin"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="team-member">
                        <img src="" class="img-responsive img-circle jquery" alt="">
                        <h4>Antonio Spera</h4>
                        <p class="text-muted"></p>
                        <ul class="list-inline social-buttons jquery">
                            <li><a href="#"><i class="fa fa-twitter"></i></a>
                            </li>
                            <li><a href="#"><i class="fa fa-facebook"></i></a>
                            </li>
                            <li><a href="#"><i class="fa fa-linkedin"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="team-member">
                        <img src="" class="img-responsive img-circle jquery" alt="">
                        <h4>Alessandro De Riso</h4>
                        <p class="text-muted"></p>
                        <ul class="list-inline social-buttons jquery">
                            <li><a href="#"><i class="fa fa-twitter"></i></a>
                            </li>
                            <li><a href="#"><i class="fa fa-facebook"></i></a>
                            </li>
                            <li><a href="#"><i class="fa fa-linkedin"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 text-center">
                    <p class="large text-muted"></p>
                </div>
            </div>
        </div>
    </section>
	<%@ include file="footer.jsp" %>
 
</body>
</html>