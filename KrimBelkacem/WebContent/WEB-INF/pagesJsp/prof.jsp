<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="head.jsp"%>
<link rel="stylesheet"
	href="http://localhost:8080/projetLicence/css/prof.css">
</head>
<body>
	<%@ include file="headerProf.jsp"%><!-- header connecte -->
	<!-- ******************** -->

	<!-- centre -->

	<div class="container-fluid">
		<div class="col-lg-12">
			<div class="col-lg-4 recherche">
				<div>
					<ul class="nav nav-tabs">
						<li class="active col-lg-3"><a id="arecherche">Recherche</a></li>
						<li class="col-lg-3"><a id="aajouter">Ajouter</a></li>
					</ul>
				</div>
				<div class="col-lg-11">

					<!--  ***************************   -->

					<form role="form" id="form_recherche" method="get"
						action="http://localhost:8080/projetLicence/prof">
						<!-- debut rechercher-->
						<div class="form-group">
							<label class="control-label col-lg-11">Numéro :</label>
							<div class="col-lg-11">
								<input name="numero" id="numero" class="form-control"
									type="number" placeholder="numero">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-11">Intitule :</label>
							<div class="col-lg-11">
								<input id="intitule" name="titre" class="form-control"
									type="text" placeholder="intitule">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-11">date-creation :</label>
							<div class="col-lg-11">
								<input id="date_creation" name="dateCreation"
									class="form-control" type="date">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-11">filiere :</label>
							<div class="col-lg-11">
								<select id="filierea" name="filiere" class="form-control"
									type="text" placeholder="filiere" name="filiere">
									<option value="">--------</option>
									<option value="tcst" class="a a1">Tronc commun science
										et technologie (tcst)</option>
									<option value="tcl" class="a a1">Tronc commun
										Littérateur (tcl)</option>
									<option value="2gc" class="a a2">Génie civil (2gc)</option>
									<option value="2ge" class="a a2">Gestion et économie
										(2ge)</option>
									<option value="2gm" class="a a2">Génie Mécanique (2gm)</option>
									<option value="2le" class="a a2">Langue étrangeres
										(2le)</option>
									<option value="2lp" class="a a2">Littérateur et
										Philosophie (2lp)</option>
									<option value="2se" class="a a2">Science
										Experimentales (2se)</option>
									<option value="3gc" class="a a3">Génie civil (3gc)</option>
									<option value="3ge" class="a a3">Gestion et économie
										(3ge)</option>
									<option value="3gm" class="a a3">Génie Mécanique (3gm)</option>
									<option value="3le" class="a a3">Langue étrangeres
										(3le)</option>
									<option value="3lp" class="a a3">Littérateur et
										Philosophie (3lp)</option>
									<option value="3se" class="a a3">Science
										Experimentales (3se)</option>
								</select>

							</div>
						</div>
						<div class="col-lg-11 form-group">
							<label class="control-label">Niveau :</label>
							<div class="radio">
								<label class="radio-inline" for="ras1"><input
									type="radio" id="ras1" value="1" name="annee"
									onclick="optionFil(this)" checked> 1 AS</label> <label
									class="radio-inline" for="ras2"><input type="radio"
									id="ras2" value="2" name="annee" onclick="optionFil(this)">
									2AS</label> <label class="radio-inline" for="ras3"><input
									type="radio" id="ras3" value="3" name="annee"
									onclick="optionFil(this)"> 3AS</label>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-11">
								<button type="submit" class="btn btn-primary">
									<span class="glyphicon glyphicon-search"></span> Recherche
								</button>
								<button type="reset" class="btn btn-primary">
									<span class="glyphicon glyphicon-refresh"></span> Annule
								</button>
							</div>
						</div>
					</form>
					<!-- fin formulaire rechercher-->

					<!--   *******************************    -->
					<!-- debut formulaire ajout -->
					<form method="post"
						action="http://localhost:8080/projetLicence/ajoutDoc"
						enctype="multipart/form-data" role="form" id="form_ajouter"
						hidden="hidden">
						<!-- debut formulaire ajouter -->
						<div class="form-group">
							<label class="control-label col-lg-11">Titre :</label>
							<div class="col-lg-11">
								<input name="titre" type="text" id="intitulea"
									class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-11">Uploader :</label>
							<div class="col-lg-11">
								<input name="fichier" id="uploader" class="form-control"
									type="file" onchange="changeImg()"> <img id="upload"
									src="images/notup.png" onclick="upFile()">
							</div>
						</div>
						<div class="col-lg-11 form-group">
							<label class="control-label">Niveau :</label>
							<div class="radio">
								<label class="radio-inline" for="as1"><input
									type="radio" id="as1" value="1" name="annee"
									onclick="optionFil(this)" checked> 1 AS</label> <label
									class="radio-inline" for="as2"><input type="radio"
									id="as2" value="2" name="annee" onclick="optionFil(this)">
									2AS</label> <label class="radio-inline" for="as3"><input
									type="radio" id="as3" value="3" name="annee"
									onclick="optionFil(this)"> 3AS</label>
							</div>
						</div>
						<div class="col-lg-11 form-group">
							<label class="control-label">Type :</label>
							<div class="radio">
								<select id="" class="form-control" type="text"
									placeholder="Type" name="type">
									<option value="exam">Examen</option>
									<option value="cour">Cour</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-11">filiere :</label>
							<div class="col-lg-11">
								<select id="filierea" class="form-control" type="text"
									placeholder="filiere" name="filiere">
									<option value="tcst" class="a a1">Tronc commun science
										et technologie (tcst)</option>
									<option value="tcl" class="a a1">Tronc commun
										Littérateur (tcl)</option>
									<option value="2gc" class="a a2">Génie civil (2gc)</option>
									<option value="2ge" class="a a2">Gestion et économie
										(2ge)</option>
									<option value="2gm" class="a a2">Génie Mécanique (2gm)</option>
									<option value="2le" class="a a2">Langue étrangeres
										(2le)</option>
									<option value="2lp" class="a a2">Littérateur et
										Philosophie (2lp)</option>
									<option value="2se" class="a a2">Science
										Experimentales (2se)</option>
									<option value="3gc" class="a a3">Génie civil (3gc)</option>
									<option value="3ge" class="a a3">Gestion et économie
										(3ge)</option>
									<option value="3gm" class="a a3">Génie Mécanique (3gm)</option>
									<option value="3le" class="a a3">Langue étrangeres
										(3le)</option>
									<option value="3lp" class="a a3">Littérateur et
										Philosophie (3lp)</option>
									<option value="3se" class="a a3">Science
										Experimentales (3se)</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-11">
								<button class="" type="submit" onclick="alert(intitulea.value)">
									<span class="glyphicon glyphicon-upload"></span> Ajouter
								</button>
								<button type="reset" class="">
									<span class="glyphicon glyphicon-refresh"></span> Annule
								</button>
							</div>
						</div>
					</form>
					<!-- fin formulaire recherche -->

				</div>
			</div>
			<!-- fin centre a gauche -->
			<div class="col-lg-8">
				<!--  debut centre a droite  -->
				<!-- ************************** -->
				<c:if test="${!empty msgDownload }">
					<c:out value="${msgDownload }"></c:out>
				</c:if>
				<!-- ************************** -->
				<div class="col-lg-12 cour-controle">
					<button type="button" class="btn btn-primary" id="cocher">
						<span class="glyphicon glyphicon-check"></span> cocher tous les
						cases
					</button>
					<button type="button" class="btn btn-primary" id="decocher">
						<span class="glyphicon glyphicon-refresh	"></span> decocher tous
						les cases
					</button>
					<button type="button" class="btn btn-primary">
						<span class="	glyphicon glyphicon-download"></span> telecharger
					</button>
					<button type="button" class="btn btn-danger">
						<span class="glyphicon glyphicon-trash"></span> supprimer
					</button>
				</div>
				<div class="col-lg-12 footer-cour">
					<span class="col-lg-4 span-cour">nombre de cour <span
						class="badge badge-cour"><c:out
								value="${nombreDoc.nbrCour }"></c:out></span>
					</span> <span class="col-lg-4 span-cour">nombre d'examen <span
						class="badge badge-cour"><c:out
								value="${nombreDoc.nbrExam }"></c:out></span>
					</span> <span class="col-lg-4 span-cour">Total <span
						class="badge badge-cour"><c:out
								value="${nombreDoc.nbrDoc }"></c:out> </span>
					</span>
				</div>
				<c:if test="${!empty docs }">
					<div class="col-lg-12  table-cour">
						<table class="table">
							<thead>
								<tr>
									<th><input type="checkbox" id="tous"></th>
									<th>Titre</th>
									<th>Date de creation</th>
									<th>Type</th>
									<th>Filiere</th>
									<th>Annee</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="doc" items="${docs }">
									<tr>
										<td><input type="checkbox"></td>
										<td><c:out value="${doc.titre }"></c:out></td>
										<td><c:out value="${doc.date }"></c:out></td>
										<td><c:out value="${doc.type }"></c:out></td>
										<td><c:out value="${doc.filiere }"></c:out></td>
										<td><c:out value="${doc.annee }"></c:out></td>
										<td>
											<button
												onclick="document.location.href = 'http://localhost:8080/projetLicence/download?id=<c:out value="${doc.id_doc }"></c:out>'">
												<span class="glyphicon glyphicon-download"></span>
											</button>
										</td>
										<td>
											<button
												onclick="document.location.href = 'http://localhost:8080/projetLicence/suppDoc?id=<c:out value="${doc.id_doc }"></c:out>'">
												<span class="glyphicon glyphicon-trash">
											</button> </span>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>
				<c:if test="${empty docs }">
					<c:out value="aucun resultat"></c:out>
				</c:if>

				<div class="col-lg-12">
					<!--  pagination  -->
					<div class="col-lg-4 col-lg-offset-4">
						<ul class="pagination">
							<c:forEach var="i" begin="1" end="${maxPages }" step="1" >
								<li class="active"><a href="http://localhost:8080/projetLicence/prof<c:out value="${requestPagination }"></c:out>&page=${i}"> <c:out value="${i }"></c:out> </a></li>
							</c:forEach>


						</ul>
					</div>
				</div>
			</div>
			<!-- centre a droite -->
		</div>
	</div>
	<!-- fin centre -->


	<!-- ******************** -->
	<%@ include file="footer.jsp"%><!-- footer -->
	<script src="http://localhost:8080/projetLicence/js/prof.js"></script>
</body>
</html>