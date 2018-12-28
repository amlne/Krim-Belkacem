<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="head.jsp"%>
<style>
#preinscForm {
	border: 1px solid lightskyblue;
	background-color: #cecece;
	padding: 20px;
	border-radius: 5%;
}
</style>
</head>
<body>
	<%@ include file="header.jsp"%>

	<!-- ************************** -->
	<c:if test="${!empty AjoutPreinscription }">
		<c:out value="${AjoutPreinscription }"></c:out>
	</c:if>
	<div class="container-fluid centre">
		<div class="row">
			<form id="preinscForm" action="http://localhost:8080/projetLicence/preinscription" method="post"
				class="col-lg-6 col-lg-offset-3">
				<div class="form-group col-lg-7">
					<label>Nom : </label> <input type="text" name="nom"
						class="form-control" placeholder="Saisissez votre nom">
				</div>
				<div class="form-group col-lg-7">
					<label>Prénom :</label> <input type="text" name="prenom"
						class="form-control" placeholder="Saisissez votre prénom">
				</div>
				<button type="submit"
					class="btn btn-primary col-lg-3 col-lg-offset-1">Valider</button>
				<div class="form-group col-lg-7">
					<label>Date de naissance : </label> <input type="date"
						name="date_nais" class="form-control">
				</div>
				<div class="form-group col-lg-7">
					<label>Lieu de naissance : </label> <input type="text"
						name="lieu_nais" class="form-control"
						placeholder="Saisissez votre lieu de naissance">
				</div>
				<button type="reset"
					class="btn btn-primary col-lg-3 col-lg-offset-1">Annuler</button>
				<div class="form-group col-lg-7">
					<label>Adresse : </label> <input type="text" name="adresse"
						class="form-control" placeholder="Saisissez votre adresse">
				</div>


			</form>
		</div>
	</div>

	<!-- ************************** -->
	<%@ include file="footer.jsp"%>
</body>
</html>