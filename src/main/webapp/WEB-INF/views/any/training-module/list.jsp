<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="developer.training-module.list.label.code" path="code" width="33%"/>
		<acme:list-column code="developer.training-module.list.label.creationMoment" path="creationMoment" width="33%"/>
		<acme:list-column code="developer.training-module.list.label.difficultyLevel" path="difficultyLevel" width="33%"/>
</acme:list>