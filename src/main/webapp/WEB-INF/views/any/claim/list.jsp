<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="any.claim.list.label.code" path="code" width="10%"/>
	<acme:list-column code="any.claim.list.label.instantiationMoment" path="instantiationMoment" width="10%"/>
	<acme:list-column code="any.claim.list.label.heading" path="heading" width="10%"/>
	<acme:list-column code="any.claim.list.label.description" path="description" width="10%"/>
	<acme:list-column code="any.claim.list.label.department" path="department" width="10%"/>
	<acme:list-column code="any.claim.list.label.email" path="email" width="10%"/>
	<acme:list-column code="any.claim.list.label.link" path="link" width="10%"/>
</acme:list>

<jstl:if test="${ _command == 'list'}">
	<acme:button code="any.claim.list.button.create" action="/any/claim/create"/>
</jstl:if>
