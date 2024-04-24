<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="any.progress-log.list.label.recordId" path="recordId" width="10%"/>
	<acme:list-column code="any.progress-log.list.label.completenessPercentage" path="completenessPercentage" width="10%"/>
	<acme:list-column code="any.progress-log.list.label.progressComment" path="progressComment" width="10%"/>
	<acme:list-column code="any.progress-log.list.label.registrationMoment" path="registrationMoment" width="10%"/>
	<acme:list-column code="any.progress-log.list.label.responsiblePerson" path="responsiblePerson" width="10%"/>
</acme:list>