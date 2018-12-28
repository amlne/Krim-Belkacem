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
	<%@ include file="header.jsp"%><!-- header -->
	<!-- ***************************** -->

	<div class="container-fluid">
		<!-- debut centre -->
		<div class="row">
			<div class="col-lg-4">
				<!-- centre a gauche -->
				<legend class="legend-rech">Recherche de cour</legend>
				<div class="col-lg-10 col-lg-offset-1">
					<form role="form">
						<div class="form-group">
							<label>Titre</label> <input type="text" class="form-control"
								placeholder="tapez une partie" name="titre">
						</div>
						<div class="form-group">
							<label>filiere</label> <select class="form-control"
								name="filiere">
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
								<option value="2se" class="a a2">Science Experimentales
									(2se)</option>
								<option value="3gc" class="a a3">Génie civil (3gc)</option>
								<option value="3ge" class="a a3">Gestion et économie
									(3ge)</option>
								<option value="3gm" class="a a3">Génie Mécanique (3gm)</option>
								<option value="3le" class="a a3">Langue étrangeres
									(3le)</option>
								<option value="3lp" class="a a3">Littérateur et
									Philosophie (3lp)</option>
								<option value="3se" class="a a3">Science Experimentales
									(3se)</option>
							</select>
						</div>
						<div class="form-group">
							<label>Niveau</label>
							<div class="bouton-radio" name="annee">
								<input type="radio" name="annee" value="1" checked><label>1AS
								</label> <input type="radio" name="annee" value="2"><label>2AS
								</label> <input type="radio" name="annee" value="3"><label>3AS
								</label>
							</div>
						</div>
						<div class="form-group">
							<button type="submite"
								class="btn btn-primary col-lg-4 col-lg-offset-4">
								<span class="glyphicon glyphicon-search"></span>Recherche
							</button>
						</div>
					</form>
				</div>
			</div>
			<!-- fin centre a gauche -->
			<div class="col-lg-8">
				<!-- debut centre a droite -->

				<legend class="cours-trouves">Cours trouves</legend>

				<c:if test="${empty docs }">
					<c:out value="aucun cour trouve"></c:out>
					<img src="http://localhost:8080/projetLicence/images/notfound.jpg">
				</c:if>
				<c:if test="${!empty docs }">
					<c:forEach var="doc" items="${docs }">
						<div class="col-lg-3">
							<!-- cour -->
							<div class="cour-exam">
								<legend><c:out value="${doc.titre }"></c:out></legend>
								<div>
									<p>
										<strong>matiére</strong> :
									</p>
								</div>
								<div>
									<p>
										<strong>filiere</strong> :
									</p>
								</div>
								<div>
									<p>
										<strong>Annee</strong> :
									</p>
								</div>
								<div>
									<button class="btn btn-primary"
										onclick="document.location.href = 'http://localhost:8080/projetLicence/download?id=<c:out value="${doc.id_doc }"></c:out>'">
										<span class="glyphicon glyphicon-download-alt"></span>Telecharge
									</button>
								</div>
							</div>
						</div>
						<!-- fin cour -->
					</c:forEach>
				</c:if>
				
				<div class="col-lg-12">
					<!--  pagination  -->
					<div class="col-lg-4 col-lg-offset-4">
						<ul class="pagination">
							<c:forEach var="i" begin="1" end="${maxPages }" step="1" >
								<li class="active"><a href="http://localhost:8080/projetLicence/courExam<c:out value="${requestPagination }"></c:out>&page=${i}"> <c:out value="${i }"></c:out> </a></li>
							</c:forEach>


						</ul>
					</div>
				</div>

			</div>
		</div>
		<!-- fin centre -->



		<!-- ***************************** -->
		<%@ include file="footer.jsp"%><!-- footer -->
</body>
</html>