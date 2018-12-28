<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1"
	charset="utf-8">
<title>Connection Admin</title>
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/mdp.css">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3 col-xs-12 mdp">
				<form role="form" method="post"
					action="http://localhost:8080/projetLicence/connectionAdmin">
					<div class="form-group  col-md-10 col-md-offset-1 col-xs-12">
						<input type="text" class="form-control input-lg"
							placeholder="Pseudo" name="pseudoAdmin">
					</div>
					<div class="form-group col-md-10 col-md-offset-1 col-xs-12">
						<input type="password" class="form-control input-lg"
							placeholder="Mot de passe" name="mdpAdmin">
					</div>
					<c:if test="${!empty msg }">
						<div class="col-xs-12 message-erreur-mdp text-center">
							<p>
								<span class="glyphicon glyphicon-remove-sign"></span>
								<c:out value="${msg }"></c:out>

							</p>
						</div>
					</c:if>
					<div class=" col-md-10 col-md-offset-1 col-xs-12">
						<button type="submit"
							class="btn btn-primary col-md-4 col-md-offset-4 col-xs-12">Connecte</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>