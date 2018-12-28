<header class="container-fluid">
	<!-- debut de header -->
	<div class="col-lg-12">
		<img src="http://localhost:8080/projetLicence/images/images.jpg" class="img-responsive img-header"
			alt="image de lycee"></img>
		<div class="col-lg-12">
			<div class="row">
				<nav class="navbar navbar-inverse">
					<ul class="nav navbar-nav col-lg-7">
						<li><a href="http://localhost:8080/projetLicence/accueil"><span
								class="glyphicon glyphicon-home"></span> Accueil</a></li>
						<li><a href="http://localhost:8080/projetLicence/planning"><span
								class="glyphicon glyphicon-list-alt"> </span> Planning</a></li>
						<li><a href="http://localhost:8080/projetLicence/courExam"><span
								class="glyphicon glyphicon-folder-open"></span> Cour/Exam</a></li>
						<li><a href="#"><span
								class="glyphicon glyphicon-info-sign"></span> Préinscription</a></li>
						<li><a href="#"><span class="glyphicon glyphicon-heart"></span>
								Vie de lycée</a></li>
						<li><a href="#"><span
								class="glyphicon glyphicon-envelope"></span> Contact</a></li>
					</ul>
					<div class="col-lg-5">
						<form action="http://localhost:8080/projetLicence/connectionEleve" method="post" role="form" class="form-inline header-form">
							<input type="text" placeholder="Pseudo" class="form-control input-sm" name="pseudoEleve"> 
							<input type="password" placeholder="Mot de passe" class="form-control input-sm" name="mdpEleve">
							<button type="submit" class="btn btn-default btn-primary btn-sm">connexion</button>
						</form>
					</div>
				</nav>
			</div>
		</div>
	</div>
</header>
<!-- fin header -->