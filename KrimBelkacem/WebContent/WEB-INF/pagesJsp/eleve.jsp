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
	<%@ include file="headerEleve.jsp"%><!-- header -->
	<!-- ***************************** -->

	<div class="container-fluid">
		<!-- debut de centre -->
		<div class="row">
			<div class="col-lg-3">
				<!-- centre a gauche -->
				<div class=" contact-enseignant">
					<h4>contact des enseignant</h4>
					<ul>

						<c:if test="${!empty profs }">
							<c:forEach var="prof" items="${profs }">
								<li>
									<div>
										<span class="filiere"><c:out value="${prof.nom }"></c:out></span>
										<span> <c:out value="${prof.prenom }"></c:out>
										</span>
									</div> <span> <c:out value="${prof.email }"></c:out>
								</span>
								</li>
							</c:forEach>
						</c:if>
					</ul>
				</div>
			</div>
			<!-- centre a gauche -->
			<div class="col-lg-9">
				<!-- msg erreur telechargement deliberation -->
				<c:if test="${!empty DownloadDeliberation }">
					<c:out value="${DownloadDeliberation }"></c:out>
				</c:if>
				<!-- msg erreur telechargement planning -->
				<c:if test="${!empty DownloadPlanning }">
					<c:out value="${DownloadPlanning }"></c:out>
				</c:if>
				<!-- centre a droite -->
				<ul class="nav nav-tabs nav-eleve">
					<li class="active"><a href="#">Cours de ma filiere</a></li>
					<li><a
						href="http://localhost:8080/projetLicence/downloadFicheDeVoeux?filiere=<c:out value="${eleve.filiere }"></c:out> ">Fiche
							de Voeux</a></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">Relve de Notes<span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="http://localhost:8080/projetLicence/downloadDeliberationEleve?classe=1ST1&trimestre=1">1er
									trimestre<small class="menu-eleve-small">[pdf]</small>
							</a></li>
							<li><a
								href="http://localhost:8080/projetLicence/downloadDeliberationEleve?classe=1ST1&trimestre=2">2eme
									trimestre<small class="menu-eleve-small">[pdf]</small>
							</a></li>
							<li><a
								href="http://localhost:8080/projetLicence/downloadDeliberationEleve?classe=1ST1&trimestre=3">3eme
									trimestre<small class="menu-eleve-small">[pdf]</small>
							</a></li>
						</ul></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">Planning<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="http://localhost:8080/projetLicence/downloadPlanningEleve?classe=1ST1&trimestre=annuel">cours
									annuel<small class="menu-eleve-small">[pdf]</small>
							</a></li>
							<li><a
								href="http://localhost:8080/projetLicence/downloadPlanningEleve?classe=1ST1&trimestre=1">examens
									1er trimestre<small class="menu-eleve-small">[pdf]</small>
							</a></li>
							<li><a
								href="http://localhost:8080/projetLicence/downloadPlanningEleve?classe=1ST1&trimestre=2">examens
									2eme trimestre<small class="menu-eleve-small">[pdf]</small>
							</a></li>
							<li><a
								href="http://localhost:8080/projetLicence/downloadPlanningEleve?classe=1ST1&trimestre=3">examens
									3eme trimestre<small class="menu-eleve-small">[pdf]</small>
							</a></li>
						</ul></li>
				</ul>
				<div class="row cour-concerne">
					<!-- cours de ma filiere-->
					<c:if test="${empty docs }">
						<c:out value="aucun cour trouve"></c:out>
					</c:if>
					<c:if test="${!empty docs }">
						<c:forEach var="doc" items="${docs }">
							<div class="col-lg-3">
								<!-- cour -->
								<div class="cour-exam">
									<legend>
										<c:out value="${doc.titre }"></c:out>
									</legend>
									<div>
										<p>
											<strong>matiére</strong> :
											<c:out value="${doc.matiere }"></c:out>
										</p>
									</div>
									<div>
										<p>
											<strong>filiere</strong> :
											<c:out value="${doc.filiere }"></c:out>
										</p>
									</div>
									<div>
										<p>
											<strong>Annee</strong> :
											<c:out value="${doc.annee }"></c:out>
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
								<c:forEach var="i" begin="1" end="${maxPages }" step="1">
									<li class="active"><a
										href="http://localhost:8080/projetLicence/eleve?page=${i}">
											<c:out value="${i }"></c:out>
									</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
				<!-- fin livres scolaires -->

				<!-- ********************* -->

				<div>
					<!-- fiche de voeux -->
				</div>
				<!-- fin de fiche de voeux -->
			</div>
		</div>
	</div>
	<!-- fin centre -->

	<!-- ***************************** -->
	<%@ include file="footer.jsp"%><!-- footer -->
</body>
</html>