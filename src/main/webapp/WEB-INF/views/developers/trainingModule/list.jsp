<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="developers.trainingModule.list.label.code" path="code" width="20%"/>
		<acme:list-column code="developers.trainingModule.list.label.creationMoment" path="creationMoment" width="10%"/>
		<acme:list-column code="developers.trainingModule.list.label.details" path="details" width="20%"/>
		<acme:list-column code="developers.trainingModule.list.label.difficultyLevel" path="difficultyLevel" width="10%"/>
		<acme:list-column code="developers.trainingModule.list.label.updateMoment" path="updateMoment" width="10%"/>
		<acme:list-column code="developers.trainingModule.list.label.link" path="link" width="20%"/>
		<acme:list-column code="developers.trainingModule.list.label.totalTime" path="totalTime" width="10%"/>
</acme:list>