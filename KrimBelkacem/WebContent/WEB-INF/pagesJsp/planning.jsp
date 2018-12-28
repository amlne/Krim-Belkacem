<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="head.jsp"%>
<link rel="stylesheet" href="css/admin.css">
<link rel="stylesheet" href="css/planning.css">
</head>
<body>
	<%@ include file="header.jsp"%><!-- header -->
	<!-- ***************************** -->

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

					<form role="form" id="form_recherche">
						<!-- debut rechercher-->
						<div class="form-group">
							<label class="control-label col-lg-11">Numéro :</label>
							<div class="col-lg-11">
								<input id="num" class="form-control" type="number"
									placeholder="numero">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-11">Intitule :</label>
							<div class="col-lg-11">
								<input id="intitule" class="form-control" type="text"
									placeholder="intitule">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-11">date-creation :</label>
							<div class="col-lg-11">
								<input id="dateCreation" class="form-control" type="date">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-11">filiere :</label>
							<div class="col-lg-11">
								<input id="filiere" class="form-control" type="text"
									placeholder="filiere">
							</div>
						</div>
						<div class="col-lg-11 form-group">
							<label class="control-label">Niveau :</label>
							<div class="radio">
								<label class="radio-inline"><input type="radio" id="1as"
									name="annee" checked> 1 AS</label> <label class="radio-inline"><input
									type="radio" id="2as" name="annee"> 2AS</label> <label
									class="radio-inline"><input type="radio" id="2as"
									name="annee"> 3AS</label>
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

					<form role="form" hidden="hidden" id="form_ajouter">
						<!-- debut formulaire ajouter -->
						<div class="form-group">
							<label class="control-label col-lg-11">Intitule :</label>
							<div class="col-lg-11">
								<input id="intitulea" class="form-control" type="text"
									placeholder="intitule">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-11">Uploader :</label>
							<div class="col-lg-11">
								<input id="uploader" class="form-control" type="file"
									style="display: none;" onchange="changeImg()"> <img
									id="upload" src="images/notup.png" onclick="upFile()">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-11">filiere :</label>
							<div class="col-lg-11">
								<input id="filierea" class="form-control" type="text"
									placeholder="filiere">
							</div>
						</div>
						<div class="col-lg-11 form-group">
							<label class="control-label">Niveau :</label>
							<div class="radio">
								<label class="radio-inline"><input type="radio"
									id="1asa" name="annee" checked> 1 AS</label> <label
									class="radio-inline"><input type="radio" id="2asa"
									name="annee"> 2AS</label> <label class="radio-inline"><input
									type="radio" id="2asa" name="annee"> 3AS</label>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-11">
								<button type="submit" class="btn btn-primary">
									<span class="glyphicon glyphicon-upload"></span> Ajouter
								</button>
								<button type="reset" class="btn btn-primary">
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
				<!-- debut de centre a droite -->
				<div class="row">
					<c:if test="${!empty plannings }">
						<c:forEach var="planning" items="${ plannings}">
							<div class="col-lg-10 col-lg-offset-1 planning">

								<!-- planning -->
								<div class="planning-header col-lg-12">
									<div class="row">
										<div class="col-lg-3">
											<p>
												classe :
												<c:out value="${planning.classe.id_cls }"></c:out>
											</p>
										</div>
										<div class="col-lg-3">
											<p>annee : 2 AS</p>
										</div>
										<div class="col-lg-4">
											<p>filiere : scientifique</p>
										</div>
										<div class="col-lg-2">
											<span class="glyphicon glyphicon-circle-arrow-down"
												onclick="ouvrir(this)"></span> <span
												class="glyphicon glyphicon-circle-arrow-right"
												style="display: none;"></span>
										</div>
									</div>
								</div>
								<div class="planning-contenu col-lg-12" hidden="hidden">
									<div class="row">
										<!-- aff planning -->
										<div class="col-lg-3">
											<div class="aff-planning">
												<div>
													<h4>planning</h4>
												</div>
												<div>
													<p>cour</p>
												</div>
												<div>
													<p>annuel</p>
												</div>

												<c:if test="${!empty planning.annuel }">
													<button
														onclick="document.location.href = 'http://localhost:8080/projetLicence/downloadPlanning?id_plan=<c:out value="${planning.id_planning }"></c:out>&trimestre=annuel'"
														class="btn btn-primary" type="button">
														<span class="glyphicon glyphicon-download"></span>
														telecharger
													</button>
												</c:if>
												<c:if test="${empty planning.annuel }">
													<button onclick="alert('planning n existe pas')"
														class="btn btn-primary" type="button">
														<span class="glyphicon glyphicon-download"></span>
														telecharger
													</button>
												</c:if>

											</div>
										</div>
										<!-- aff planning -->
										<div class="col-lg-3">
											<div class="aff-planning">
												<div>
													<h4>planning</h4>
												</div>
												<div>
													<p>examen</p>
												</div>
												<div>
													<p>trimestre 1</p>
												</div>

												<c:if test="${!empty planning.trimestre1 }">
													<button
														onclick="document.location.href = 'http://localhost:8080/projetLicence/downloadPlanning?id_plan=<c:out value="${planning.id_planning }"></c:out>&trimestre=1'"
														class="btn btn-primary" type="button">
														<span class="glyphicon glyphicon-download"></span>
														telecharger
													</button>
												</c:if>
												<c:if test="${empty planning.trimestre1 }">
													<button onclick="alert('planning n existe pas')"
														class="btn btn-primary" type="button">
														<span class="glyphicon glyphicon-download"></span>
														telecharger
													</button>
												</c:if>

											</div>
										</div>
										<!-- aff planning -->
										<div class="col-lg-3">
											<div class="aff-planning">
												<div>
													<h4>planning</h4>
												</div>
												<div>
													<p>examen</p>
												</div>
												<div>
													<p>trimestre 2</p>
												</div>

												<c:if test="${!empty planning.trimestre2 }">
													<button
														onclick="document.location.href = 'http://localhost:8080/projetLicence/downloadPlanning?id_plan=<c:out value="${planning.id_planning }"></c:out>&trimestre=2'"
														class="btn btn-primary" type="button">
														<span class="glyphicon glyphicon-download"></span>
														telecharger
													</button>
												</c:if>
												<c:if test="${empty planning.trimestre2 }">
													<button onclick="alert('planning n existe pas')"
														class="btn btn-primary" type="button">
														<span class="glyphicon glyphicon-download"></span>
														telecharger
													</button>
												</c:if>

											</div>
										</div>
										<!-- aff planning -->
										<div class="col-lg-3">
											<div class="aff-planning">
												<div>
													<h4>planning</h4>
												</div>
												<div>
													<p>examen</p>
												</div>
												<div>
													<p>trimestre 3</p>
												</div>

												<c:if test="${!empty planning.trimestre3 }">
													<button
														onclick="document.location.href = 'http://localhost:8080/projetLicence/downloadPlanning?id_plan=<c:out value="${planning.id_planning }"></c:out>&trimestre=3'"
														class="btn btn-primary" type="button">
														<span class="glyphicon glyphicon-download"></span>
														telecharger
													</button>
												</c:if>
												<c:if test="${empty planning.trimestre3 }">
													<button onclick="alert('planning n existe pas')"
														class="btn btn-primary" type="button">
														<span class="glyphicon glyphicon-download"></span>
														telecharger
													</button>
												</c:if>

											</div>
										</div>

									</div>
								</div>
							</div>
						</c:forEach>
					</c:if>

				</div>
			</div>
			<!-- centre a droite -->
		</div>
	</div>
	<!-- fin centre -->

	<!-- ***************************** -->
	<%@ include file="footer.jsp"%><!-- footer -->
	<script type="text/javascript" src="js/planning.js"></script>
</body>
</html>