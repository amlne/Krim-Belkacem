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
						<!-- debut d'actualit�-->
						<div class="page-header text-center">
							<h3>Actualit�</h3>
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
					<!-- fin de l'actualit� -->
				</div>
			</div>
			<!-- centre a gauche -->
			<div class="col-lg-4">
				<header class="page-header text-center">
				<h3>Qui �tait Krim BELKACEM</h3>
				</header>
				<div class="row">
					<div class="col-lg-6">
						<img src="images/krimbelkacem.jpg" class="img-responsive">
					</div>
					<div class="col-lg-6">
						<p>Krim Belkacem (en kabyle : Krim Belqasem) n� le 15 d�cembre
							1922 � Oued Ksari (A�t Yahia Moussa) en Kabylie (Alg�rie), et
							mort assassin� � Francfort le 18 octobre 1970 par la S�curit�
							Militaire alg�rienne, est un homme politique alg�rien, chef
							historique du Front de lib�ration nationale durant la guerre
							d'ind�pendance alg�rienne. C'est Krim Belkacem qui signera l'acte
							d'ind�pendance de l'Alg�rie en bas des accords d'Evian en tant
							que plus grad� des anciens maquisards et seul membre des six qui
							ont d�clench� le premier novembre encore en vie et non
							prisonnier, mais surtout en tant que vice-pr�sident du GPRA.</p>
					</div>
					<div class="col-lg-6">
						<div class="page-header titre">
							<h4 class="text-center">1945-1955 : jeunesse</h4>
							<p>Krim Belkacem est le fils d'un ca�d, Hocine Krim[r�f.
								n�cessaire]. Il fr�quente l'�cole Sarrouy � Alger et y obtient
								son certificat d'�tudes. Le 21 ao�t 1942, il s'engage aux
								chantiers de jeunesse � Laghouat Son p�re, inquiet de l'int�r�t
								de son fils pour les id�es nationalistes, h�te son passage sous
								les drapeaux et le fait entrer dans l'arm�e en devan�ant l'appel
								de sa classe, le 1er juillet 1943. Il devient un excellent
								tireur. Le 26 novembre 1944, il est nomm� caporal-chef au 1er
								r�giment de tirailleurs alg�riens. D�mobilis� le 4 octobre 1945,
								il revient vivre � Dra� El Mizan o� il occupe le poste de
								secr�taire auxiliaire de la commune.</p>
						</div>
					</div>
					<div class="col-lg-6">
						<div class="page-header titre">
							<h4 class="text-center">1956-1962: Op�ration Oiseau bleu</h4>
							<p>
								� l'automne 1956, les services secrets du SDECE, men�rent en
								Kabylie, dans la ville d'Azzazga d'abord puis chez les Iflissen,
								l'op�ration � Oiseau bleu �, connue sous l'autre nom de � Force
								K �. Elle consistait dans la cr�ation de � contre maquis �
								clandestins destin�e � lutter contre Belkacem et ses hommes. Les
								services secrets ont recrut� 300 hommes, et les armes et les
								munitions leur sont livr�es : 200 armes de guerre arrivent en
								janvier 1956, et 80 en f�vrier-mars. <a target="_blank"
									href="https://fr.wikipedia.org/wiki/Krim_Belkacem"> lire la
									suit </lire>
								</a>
							</p>
							<small>- wikip�dia</small>
						</div>
					</div>
				</div>
			</div>
			<!-- qui �tait krim belkacem -->
		</div>
	</div>
	<!-- fin centre -->

	<!-- ************************** -->
	<%@ include file="footer.jsp"%>
</body>
</html>