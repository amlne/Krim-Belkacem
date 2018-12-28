<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="head.jsp"%>
<link rel="stylesheet" href="css/admin.css">
</head>
<body>
	<%@ include file="headerAdmin.jsp"%><!-- header -->
	<!-- ***************************** -->
	<!-- modal-->

	<div class="modal fade" id="myModal" role="dialog" Style="">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" onclick="">&times;</button>
					<h4 class="modal-title">Type de l'erreur</h4>
				</div>
				<div class="modal-body">
					<p>Description de l'erreur</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						onclick="">Fermer</button>
				</div>
			</div>

		</div>
	</div>

	<!-- ************* -->
	<c:if test="${!empty AjoutDeliberation }">
		<c:out value="${AjoutDeliberation }"></c:out>
	</c:if>
	<c:if test="${!empty AjoutPlanning }">
		<c:out value="${AjoutPlanning }"></c:out>
	</c:if>
	<c:if test="${!empty AjoutRubrique }">
		<c:out value="${AjoutRubrique }"></c:out>
	</c:if>
	<c:if test="${!empty msgOuvrirPreinscription }">
		<c:out value="${msgOuvrirPreinscription }"></c:out>
	</c:if>
	<c:if test="${!empty msgSuppRubrique }">
		<c:out value="${msgSuppRubrique }"></c:out>
	</c:if>
	<c:if test="${!empty msgSuppPlanning }">
		<c:out value="${msgSuppPlanning }"></c:out>
	</c:if>
	<c:if test="${!empty msgSuppDeliberation }">
		<c:out value="${msgSuppDeliberation }"></c:out>
	</c:if>
	<!-- centre -->

	<div class="container-fluid">
		<div class="col-lg-12">
			<div class="col-lg-3 formcolor">
				<!-- debut de centre a gauche -->
				<div class="row">
					<div class="col-lg-12 admin-form-rebrique">
						<div>
							<ul class="nav nav-tabs">
								<li class="active col-lg-5"><a id="ajoutRub"
									class="afficheur">Ajouter</a></li>
								<li class="col-lg-5"><a id="rechercheRub" class="afficheur">Rechercher</a></li>
							</ul>
						</div>

						<!-- debut form rebrique -->
						<div class="row formcolor">
							<div class="col-lg-12">
								<h3 class="page-header text-center headerForm">rubrique</h3>
							</div>
							<div class="col-lg-10 col-lg-offset-1">
								<!-- Formulaire ajout rubrique -->
								<form id="form-ajoutRub" method="post" role="form"
									action="http://localhost:8080/projetLicence/ajoutRubrique"
									enctype="multipart/form-data">
									<div class="form-group">
										<label class="">titre : </label> <input type="text" id=""
											class="form-control" name="titre">
									</div>
									<div class="form-group">
										<label>catégorie : </label> <select class="form-control"
											name="categorie">
											<option value="sport">Sport</option>
											<option value="culture">Culture</option>
											<option value="voyages">Voyages</option>
											<option value="parents">Parents</option>
										</select>
									</div>
									<div class="form-group">
										<label class="">contenu : </label>
										<textarea id="textRub" class="form-control" rows="7"
											name="contenu"></textarea>
									</div>
									<div class="form-group">
										<label class="control-label col-lg-11">Uploader :</label>
										<div class="col-lg-11 upl">
											<input name="fichier" id="uploaderImg" class="form-control"
												type="file" onchange="changeImg(this)"> <img
												src="images/notup.png" onclick="upFile(this)">
										</div>
									</div>
									<button type="submit" class="btn btn-primary col-lg-5">Valider</button>
									<button type="reset"
										class="btn btn-primary col-lg-offset-2 col-lg-5">Annuler</button>
								</form>
								<!-- Formulaire Recherche rubrique -->
								<form id="form-rechercheRub" method="get" action="" role="form"
									hidden="hidden">
									<input name="recherche" value="rubrique" hidden="hidden">
									<div class="form-group">
										<label class="">titre : </label> <input type="text" id=""
											class="form-control" name="titre">
									</div>
									<div class="form-group">
										<label class="">catégorie : </label> <select
											class="form-control" name="categorie">
											<option value="sport">Sport</option>
											<option value="culture">Culture</option>
											<option value="voyages">Voyages</option>
											<option value="parents">Parents</option>
										</select>
									</div>
									<button type="submit" class="btn btn-primary col-lg-5">Valider</button>
									<button type="reset"
										class="btn btn-primary col-lg-offset-2  col-lg-5">Annuler</button>
								</form>
							</div>
						</div>
					</div>

					<!-- fin form rebrique -->
				</div>
			</div>
			<!-- fin centre a gauche -->

			<div class="col-lg-6" id="admin_centre_milieu">
				<!-- debut de centre milieu -->
				<div class="row">
					<div class="col-lg-12 control-prinscription">

						<div class="page-header text-center">
							<h4>Gestion Preinscription</h4>
						</div>
						<c:if test="${!empty preinscriptionOuvert }">
							<c:if test="${preinscriptionOuvert }">
								<button type="button"
									class="btn btn-primary col-lg-5 btn-preinscription"
									onclick="location.href = 'http://localhost:8080/projetLicence/OuvrirPreinscription?ouvrir=1'"
									disabled>
									<span>ouvrir préinscription</span>
								</button>
								<button type="button"
									class="btn btn-danger col-lg-5 col-lg-offset-2 btn-preinscription"
									onclick="location.href = 'http://localhost:8080/projetLicence/OuvrirPreinscription?ouvrir=0'">
									<span>fermer préinscription</span>
								</button>
							</c:if>
							<c:if test="${!preinscriptionOuvert }">
								<button type="button"
									class="btn btn-primary col-lg-5 btn-preinscription"
									onclick="location.href = 'http://localhost:8080/projetLicence/OuvrirPreinscription?ouvrir=1'">
									<span>ouvrir préinscription</span>
								</button>
								<button type="button"
									class="btn btn-danger col-lg-5 col-lg-offset-2 btn-preinscription"
									onclick="location.href = 'http://localhost:8080/projetLicence/OuvrirPreinscription?ouvrir=0'"
									disabled>
									<span>fermer préinscription</span>
								</button>
							</c:if>
						</c:if>
					</div>
					<!-- fin control preinscription -->

					<c:if test="${!empty plannings }">
						<!-- debut resultat planning -->
						<div class="col-lg-12">


							<div class="page-header text-center">
								<h4 class="resRech">Résultat recherche planning</h4>
							</div>
							<table class="table">
								<thead>
									<tr>
										<th>classe</th>
										<th>Cours</th>
										<th>Exams T1</th>
										<th>Exams T2</th>
										<th>Exams T3</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="planning" items="${plannings }">
										<tr>
											<td><c:out value="${planning.classe.id_cls }"></c:out></td>
											<c:if test="${empty planning.annuel }">
												<td>NULL</td>
											</c:if>
											<c:if test="${!empty planning.annuel }">
												<td><a
													href="http://localhost:8080/projetLicence/downloadPlanning?id_plan=<c:out value="${planning.id_planning }"></c:out>&trimestre=annuel"><span
														class="glyphicon glyphicon-download"></span></a> <a
													href="http://localhost:8080/projetLicence/suppPlanning?id_plan=<c:out value="${planning.id_planning }"></c:out>&trimestre=annuel"><span
														class="glyphicon glyphicon-trash"></span></a></td>
											</c:if>

											<c:if test="${empty planning.trimestre1 }">
												<td>NULL</td>
											</c:if>
											<c:if test="${!empty planning.trimestre1 }">
												<td><a
													href="http://localhost:8080/projetLicence/downloadPlanning?id_plan=<c:out value="${planning.id_planning }"></c:out>&trimestre=1"><span
														class="glyphicon glyphicon-download"></span></a> <a
													href="http://localhost:8080/projetLicence/suppPlanning?id_plan=<c:out value="${planning.id_planning }"></c:out>&trimestre=1"><span
														class="glyphicon glyphicon-trash"></span></a></td>
											</c:if>

											<c:if test="${empty planning.trimestre2 }">
												<td>NULL</td>
											</c:if>
											<c:if test="${!empty planning.trimestre2 }">
												<td><a
													href="http://localhost:8080/projetLicence/downloadPlanning?id_plan=<c:out value="${planning.id_planning }"></c:out>&trimestre=2"><span
														class="glyphicon glyphicon-download"></span></a> <a
													href="http://localhost:8080/projetLicence/suppPlanning?id_plan=<c:out value="${planning.id_planning }"></c:out>&trimestre=2"><span
														class="glyphicon glyphicon-trash"></span></a></td>
											</c:if>

											<c:if test="${empty planning.trimestre3 }">
												<td>NULL</td>
											</c:if>
											<c:if test="${!empty planning.trimestre3 }">
												<td><a
													href="http://localhost:8080/projetLicence/downloadPlanning?id_plan=<c:out value="${planning.id_planning }"></c:out>&trimestre=3"><span
														class="glyphicon glyphicon-download"></span></a> <a
													href="http://localhost:8080/projetLicence/suppPlanning?id_plan=<c:out value="${planning.id_planning }"></c:out>&trimestre=3"><span
														class="glyphicon glyphicon-trash"></span></a></td>
											</c:if>

											<td><a
												href="http://localhost:8080/projetLicence/suppPlanning?id_plan=<c:out value="${planning.id_planning }"></c:out>"><span
													class="glyphicon glyphicon-trash"></span></a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- fin resultat planning -->
					</c:if>

					<!-- debut resultat planning -->
					<c:if test="${!empty deliberations }">
						<div class="col-lg-12">
							<div class="page-header text-center">
								<h4>Resultat recherche deliberation</h4>
							</div>
							<table class="table">
								<thead>
									<tr>
										<th>classe</th>
										<th>1er trimestre</th>
										<th>2eme trimestre</th>
										<th>3eme trimestre</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="deliberation" items="${deliberations }">
										<tr>
											<td><c:out value="${deliberation.classe.id_cls }"></c:out></td>
											<c:if test="${empty deliberation.trimestre1 }">
												<td>Vide</td>
											</c:if>
											<c:if test="${!empty deliberation.trimestre1 }">
												<td><a
													href="http://localhost:8080/projetLicence/downloadDeliberation?id_del=<c:out value="${deliberation.id_del }"></c:out>&trimestre=1"><button>
															<span class="glyphicon glyphicon-download"></span>
														</button></a> <a
													href="http://localhost:8080/projetLicence/suppDeliberation?id_del=<c:out value="${deliberation.id_del }"></c:out>&trimestre=1"><button>
															<span class="glyphicon glyphicon-trash"></span>
														</button></a></td>
											</c:if>

											<c:if test="${empty deliberation.trimestre2 }">
												<td>Vide</td>
											</c:if>
											<c:if test="${!empty deliberation.trimestre2 }">
												<td><a
													href="http://localhost:8080/projetLicence/downloadDeliberation?id_del=<c:out value="${deliberation.id_del }"></c:out>&trimestre=2"><button>
															<span class="glyphicon glyphicon-download"></span>
														</button></a> <a
													href="http://localhost:8080/projetLicence/suppDeliberation?id_del=<c:out value="${deliberation.id_del }"></c:out>&trimestre=2"><button>
															<span class="glyphicon glyphicon-trash"></span>
														</button></a></td>
											</c:if>

											<c:if test="${empty deliberation.trimestre3 }">
												<td>Vide</td>
											</c:if>
											<c:if test="${!empty deliberation.trimestre3 }">
												<td><a
													href="http://localhost:8080/projetLicence/downloadDeliberation?id_del=<c:out value="${deliberation.id_del }"></c:out>&trimestre=3"><button>
															<span class="glyphicon glyphicon-download"></span>
														</button></a> <a
													href="http://localhost:8080/projetLicence/suppDeliberation?id_del=<c:out value="${deliberation.id_del }"></c:out>&trimestre=3"><button>
															<span class="glyphicon glyphicon-trash"></span>
														</button></a></td>
											</c:if>

											<td><a
												href="http://localhost:8080/projetLicence/suppDeliberation?id_del=<c:out value="${deliberation.id_del }"></c:out>"><button>
														<span class="glyphicon glyphicon-trash"></span>
													</button></a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- fin resultat planning -->
					</c:if>


					<div class="col-lg-12">
						<!-- debut resultat rubrique -->
						<div class="page-header">
							<h4>Resultat recherche Rubrique</h4>
						</div>
						<c:if test="${!empty rubriques }">
							<c:forEach var="rubrique" items="${rubriques }">
								<!-- debut rebrique -->
								<div class="rebrique col-lg-12">
									<!-- debut rebrique -->
									<div class="panel panel-primary">
										<div class="panel-heading">
											<c:out value="${rubrique.titre }"></c:out>
											<span class="glyphicon glyphicon-trash"></span><span>
												Supprimer</span>
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="col-lg-4 img-rebrique">
													<img src="images/rentre.jpg" class="img-responsive">
												</div>
												<div class="col-lg-8 contenue">
													<p>
														<c:out value="${rubrique.contenu }"></c:out>
														<small><c:out value="${rubrique.categorie }"></c:out>
														</small>
													</p>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- fin rebrique -->
							</c:forEach>
						</c:if>

					</div>
					<!-- fin resultat planning -->
				</div>
			</div>
			<!-- fin centre milieu -->

			<div class="col-lg-3 formcolor">
				<!--  debut centre a droite  -->
				<div class="row">
					<div class="col-lg-12 admin-form-planning">
						<div>
							<ul class="nav nav-tabs">
								<li class="active col-lg-5 dropdown planAct"><a
									id="plannings dropdown-toggle" data-toggle="dropdown"
									class="afficheur">&nbsp;&nbsp;&nbsp;Plannings&nbsp;&nbsp;&nbsp;<span
										class="caret"></span></a>
									<ul class="dropdown-menu">
										<li><a id="ajoutPlanning">Ajouter</a></li>
										<li><a id="recherchePlanning">Rechercher</a></li>
									</ul></li>
								<li class="col-lg-5 dropdown delAct"><a
									id="deliberations dropdown-toggle" data-toggle="dropdown"
									class="afficheur">Délibérations<span class="caret"></span></a>
									<ul class="dropdown-menu">
										<li><a id="ajoutDeliberation">Ajouter</a></li>
										<li><a id="rechercheDeliberation">Rechercher</a></li>
									</ul></li>
							</ul>
						</div>

						<!-- debut form planning -->
						<div id="divPlan" class="row">
							<div class="col-lg-12">
								<h3 class="page-header text-center headerForm">planning</h3>
							</div>
							<div class="col-lg-10 col-lg-offset-1">
								<!-- Formulaire ajout planninge -->
								<form id="form-ajoutPlanning" method="post" role="form"
									action="http://localhost:8080/projetLicence/ajoutPlanning"
									enctype="multipart/form-data">
									<div class="form-group">
										<label class="">Année</label>
										<div class="radio">
											<label class="radio-inline"><input type="radio"
												id="as1" name="annee" checked onclick="optionCls(this)"
												value="1"> 1 AS</label> <label class="radio-inline"><input
												type="radio" id="as2" name="annee" onclick="optionCls(this)"
												value="2"> 2AS</label> <label class="radio-inline"><input
												type="radio" id="as3" name="annee" onclick="optionCls(this)"
												value="3"> 3AS</label>
										</div>
									</div>
									<div class="form-group">
										<label class="">classe</label> <select class="form-control"
											name="classe">
											<option value="">...</option>
											<option value="1st1" class="a a1">1ST1</option>
											<option value="1st2" class="a a1">1ST2</option>
											<option value="2ge1" class="a a2">2GE1</option>
											<option value="2lp1" class="a a2">2LP1</option>
											<option value="2se1" class="a a2">2SE1</option>
											<option value="2se2" class="a a2">2SE2</option>
											<option value="3gc1" class="a a3">3GC1</option>
											<option value="3ge1" class="a a3">3GE1</option>
											<option value="3gm1" class="a a3">3GM1</option>
											<option value="3le1" class="a a3">3LE1</option>
										</select>
									</div>
									<div class="form-group">
										<label class="">type</label> <select name="trimestre"
											class="form-control">
											<option value="annuel">cour annuel</option>
											<option value="1">examen 1 er trimestre</option>
											<option value="2">examen 2 eme trimestre</option>
											<option value="3">examen 3 eme trimestre</option>
										</select>
									</div>
									<div class="form-group">
										<label class="control-label col-lg-11">Uploader :</label>
										<div class="col-lg-11 upl">
											<input name="fichier" id="uploader2" class="form-control"
												type="file" onchange="changeImg(this)"> <img
												src="images/notup.png" onclick="upFile(this)">
										</div>
									</div>
									<button type="submit" class="btn btn-primary col-lg-5">Valider</button>
									<button type="reset"
										class="btn btn-primary col-lg-offset-2 col-lg-5">Annuler</button>
								</form>
								<!-- Formulaire Recherche planning -->
								<form id="form-recherchePlanning" role="form" hidden="hidden"
									method="get" action="">
									<input name="recherche" value="planning" hidden="hidden">
									<div class="form-group">
										<label class="">Année</label>
										<div class="radio">
											<label class="radio-inline"><input type="radio"
												id="as1" name="annee" checked onclick="optionCls(this)"
												value="1"> 1 AS</label> <label class="radio-inline"><input
												type="radio" id="as2" name="annee" onclick="optionCls(this)"
												value="2"> 2AS</label> <label class="radio-inline"><input
												type="radio" id="as3" name="annee" onclick="optionCls(this)"
												value="3"> 3AS</label>
										</div>
									</div>
									<div class="form-group">
										<label class="">classe</label> <select class="form-control"
											name="classe">
											<option value="">...</option>
											<option value="1st1" class="a a1">1ST1</option>
											<option value="1st2" class="a a1">1ST2</option>
											<option value="2ge1" class="a a2">2GE1</option>
											<option value="2lp1" class="a a2">2LP1</option>
											<option value="2se1" class="a a2">2SE1</option>
											<option value="2se2" class="a a2">2SE2</option>
											<option value="3gc1" class="a a3">3GC1</option>
											<option value="3ge1" class="a a3">3GE1</option>
											<option value="3gm1" class="a a3">3GM1</option>
											<option value="3le1" class="a a3">3LE1</option>
										</select>
									</div>
									<button type="submit" class="btn btn-primary col-lg-5">Valider</button>
									<button type="reset"
										class="btn btn-primary col-lg-offset-2 col-lg-5">Annuler</button>
								</form>
							</div>
						</div>
						<div id="divDel" class="row">
							<div class="col-lg-12">
								<h3 class="page-header text-center headerForm">Délibérations</h3>
							</div>
							<div class="col-lg-10 col-lg-offset-1">

								<form id="form-ajoutDeliberation" method="post" role="form"
									hidden="hidden"
									action="http://localhost:8080/projetLicence/ajoutDeliberation"
									enctype="multipart/form-data">
									<div class="form-group">
										<label class="">Année</label>
										<div class="radio">
											<label class="radio-inline"><input type="radio"
												id="as1" name="annee" checked onclick="optionCls(this)"
												value="1"> 1 AS</label> <label class="radio-inline"><input
												type="radio" id="as2" name="annee" onclick="optionCls(this)"
												value="2"> 2AS</label> <label class="radio-inline"><input
												type="radio" id="as3" name="annee" onclick="optionCls(this)"
												value="3"> 3AS</label>
										</div>
									</div>
									<div class="form-group">
										<label class="">classe</label> <select class="form-control"
											name="classe">
											<option value="">...</option>
											<option value="1st1" class="a a1">1ST1</option>
											<option value="1st2" class="a a1">1ST2</option>
											<option value="2ge1" class="a a2">2GE1</option>
											<option value="2lp1" class="a a2">2LP1</option>
											<option value="2se1" class="a a2">2SE1</option>
											<option value="2se2" class="a a2">2SE2</option>
											<option value="3gc1" class="a a3">3GC1</option>
											<option value="3ge1" class="a a3">3GE1</option>
											<option value="3gm1" class="a a3">3GM1</option>
											<option value="3le1" class="a a3">3LE1</option>
										</select>
									</div>
									<div class="form-group">
										<label class="">type</label> <select name="trimestre"
											class="form-control">
											<option value="1">examen 1 er trimestre</option>
											<option value="2">examen 2 eme trimestre</option>
											<option value="3">examen 3 eme trimestre</option>
										</select>
									</div>
									<div class="form-group">
										<label class="control-label col-lg-11">Uploader :</label>
										<div class="col-lg-11 upl">
											<input name="fichier" id="uploader2" class="form-control"
												type="file" onchange="changeImg(this)"> <img
												src="images/notup.png" onclick="upFile(this)">
										</div>
									</div>
									<button type="submit" class="btn btn-primary col-lg-5">Valider</button>
									<button type="reset"
										class="btn btn-primary col-lg-offset-2 col-lg-5">Annuler</button>
								</form>
								<!-- Formulaire Recherche rubrique -->
								<form id="form-rechercheDeliberation" method="get"
									action="http://localhost:8080/projetLicence/admin" role="form"
									hidden="hidden">
									<div class="form-group">
										<input name="recherche" value="deliberation" hidden="hidden">
										<label class="">Année</label>
										<div class="radio">
											<label class="radio-inline"><input type="radio"
												id="as1" name="annee" checked onclick="optionCls(this)"
												value="1"> 1 AS</label> <label class="radio-inline"><input
												type="radio" id="as2" name="annee" onclick="optionCls(this)"
												value="2"> 2AS</label> <label class="radio-inline"><input
												type="radio" id="as3" name="annee" onclick="optionCls(this)"
												value="3"> 3AS</label>
										</div>
									</div>
									<div class="form-group">
										<label class="">classe</label> <select class="form-control"
											name="classe">
											<option value="">...</option>
											<option value="1st1" class="a a1">1ST1</option>
											<option value="1st2" class="a a1">1ST2</option>
											<option value="2ge1" class="a a2">2GE1</option>
											<option value="2lp1" class="a a2">2LP1</option>
											<option value="2se1" class="a a2">2SE1</option>
											<option value="2se2" class="a a2">2SE2</option>
											<option value="3gc1" class="a a3">3GC1</option>
											<option value="3ge1" class="a a3">3GE1</option>
											<option value="3gm1" class="a a3">3GM1</option>
											<option value="3le1" class="a a3">3LE1</option>
										</select>
									</div>
									<button type="submit" class="btn btn-primary col-lg-5">Valider</button>
									<button type="reset"
										class="btn btn-primary col-lg-offset-2 col-lg-5">Annuler</button>
								</form>
							</div>
						</div>
						<!-- fin form planning -->
					</div>
				</div>
			</div>
			<!-- centre a droite -->
		</div>
	</div>
	<!-- centre -->


	<!-- ***************************** -->
	<%@ include file="footer.jsp"%><!-- footer -->
	<script src="http://localhost:8080/projetLicence/js/admin.js"></script>
</body>
</html>