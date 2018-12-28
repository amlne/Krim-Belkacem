<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="head.jsp"%>
</head>
<body>
	<%@ include file="header.jsp"%>

	<!-- ************************** -->
	<!-- debut de centre -->
	<div class="container-fluid centre">
		<div class="row">
			<div class="col-lg-8">
				<div class="row">
					<div class="col-lg-12">
						<!-- debut d'actualité-->
						<div class="page-header text-center">
							<h3>Actualité</h3>
						</div>

						<c:if test="${!empty rubriques }">
							<c:forEach var="rubrique" items="${rubriques }">
								<!-- debut rebrique -->
								<div class="rebrique col-lg-10 col-lg-offset-1">
									<div class="panel panel-primary">
										<div class="panel-heading">
											<c:out value="${rubrique.titre }"></c:out>
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="col-lg-4 img-rebrique">
													<img src='<c:out value="http://localhost:8080/projetLicence/downloadRubrique?id= ${rubrique.id_rub }"></c:out>' class="img-responsive">
												</div>
												<div class="col-lg-8 contenue">
													<p>
														<c:out value="${rubrique.contenu}"></c:out>
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
					<!-- fin de l'actualité -->
				</div>
			</div>
			<!-- centre a gauche -->
			<div class="col-lg-4">
				<header class="page-header text-center">
				<h3>Qui était Krim BELKACEM</h3>
				</header>
				<div class="row">
					<div class="col-lg-6">
						<img src="images/krimbelkacem.jpg" class="img-responsive">
					</div>
					<div class="col-lg-6">
						<p>Krim Belkacem (en kabyle : Krim Belqasem) né le 15 décembre
							1922 à Oued Ksari (Aït Yahia Moussa) en Kabylie (Algérie), et
							mort assassiné à Francfort le 18 octobre 1970 par la Sécurité
							Militaire algérienne, est un homme politique algérien, chef
							historique du Front de libération nationale durant la guerre
							d'indépendance algérienne. C'est Krim Belkacem qui signera l'acte
							d'indépendance de l'Algérie en bas des accords d'Evian en tant
							que plus gradé des anciens maquisards et seul membre des six qui
							ont déclenché le premier novembre encore en vie et non
							prisonnier, mais surtout en tant que vice-président du GPRA.</p>
					</div>
					<div class="col-lg-6">
						<div class="page-header titre">
							<h4 class="text-center">1945-1955 : jeunesse</h4>
							<p>Krim Belkacem est le fils d'un caïd, Hocine Krim[réf.
								nécessaire]. Il fréquente l'école Sarrouy à Alger et y obtient
								son certificat d'études. Le 21 août 1942, il s'engage aux
								chantiers de jeunesse à Laghouat Son père, inquiet de l'intérêt
								de son fils pour les idées nationalistes, hâte son passage sous
								les drapeaux et le fait entrer dans l'armée en devançant l'appel
								de sa classe, le 1er juillet 1943. Il devient un excellent
								tireur. Le 26 novembre 1944, il est nommé caporal-chef au 1er
								régiment de tirailleurs algériens. Démobilisé le 4 octobre 1945,
								il revient vivre à Draâ El Mizan où il occupe le poste de
								secrétaire auxiliaire de la commune.</p>
						</div>
					</div>
					<div class="col-lg-6">
						<div class="page-header titre">
							<h4 class="text-center">1956-1962: Opération Oiseau bleu</h4>
							<p>
								À l'automne 1956, les services secrets du SDECE, menèrent en
								Kabylie, dans la ville d'Azzazga d'abord puis chez les Iflissen,
								l'opération « Oiseau bleu », connue sous l'autre nom de « Force
								K ». Elle consistait dans la création de « contre maquis »
								clandestins destinée à lutter contre Belkacem et ses hommes. Les
								services secrets ont recruté 300 hommes, et les armes et les
								munitions leur sont livrées : 200 armes de guerre arrivent en
								janvier 1956, et 80 en février-mars. <a target="_blank"
									href="https://fr.wikipedia.org/wiki/Krim_Belkacem"> lire la
									suit </lire>
								</a>
							</p>
							<small>- wikipédia</small>
						</div>
					</div>
				</div>
			</div>
			<!-- qui était krim belkacem -->
		</div>
	</div>
	<!-- fin centre -->

	<!-- ************************** -->
	<%@ include file="footer.jsp"%>
</body>
</html>