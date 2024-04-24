<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="administrator.risk.list.label.reference" path="reference" width="30%"/>"
	<acme:list-column code="administrator.risk.list.label.identificationDate" path="identificationDate" width="30%"/>"
	<acme:list-column code="administrator.risk.list.label.impact" path="impact" width="30%"/>"	
</acme:list>

<jstl:if test="${_command == 'list'}">
    <acme:button code="administrator.risk.list.button.create" action="/administrator/risk/create"/>
</jstl:if>
	