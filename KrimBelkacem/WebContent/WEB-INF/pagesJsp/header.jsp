<c:if test="${!empty prof }">
	<%@ include file="headerProf.jsp"%>
</c:if>
<c:if test="${empty prof }">
	<c:if test="${!empty eleve }">
		<%@ include file="headerEleve.jsp"%>
	</c:if>
	<c:if test="${empty eleve }">
		<c:if test="${!empty admin }">
			<%@ include file="headerAdmin.jsp"%>
		</c:if>
		<c:if test="${empty admin }">
			<%@ include file="headerVisiteur.jsp"%>
		</c:if>
	</c:if>
</c:if>