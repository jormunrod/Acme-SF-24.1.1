<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="developer.training-module.list.label.code" path="code" width="20%"/>
		<acme:list-column code="developer.training-module.list.label.creationMoment" path="creationMoment" width="10%"/>
		<acme:list-column code="developer.training-module.list.label.difficultyLevel" path="difficultyLevel" width="10%"/>
		<acme:list-column code="developer.training-module.list.label.updateMoment" path="updateMoment" width="10%"/>
		<acme:list-column code="developer.training-module.list.label.link" path="link" width="20%"/>
		<acme:list-column code="developer.training-module.list.label.totalTime" path="totalTime" width="10%"/>
</acme:list>

<jstl:if test="${ _command == 'list'}">
	<acme:button code="developer.training-module.list.button.create" action="/developer/training-module/create"/>
</jstl:if>