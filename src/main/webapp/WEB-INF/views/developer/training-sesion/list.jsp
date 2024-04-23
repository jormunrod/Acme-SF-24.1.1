<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
		<acme:list-column code="developer.training-sesion.list.label.code" path="code" width="33%"/>
		<acme:list-column code="developer.training-sesion.list.label.startDate" path="startDate" width="33%"/>
		<acme:list-column code="developer.training-sesion.list.label.location" path="location" width="33%"/>

</acme:list>

<jstl:choose>
	<jstl:when test="${ _command == 'list' && showCreate}">
		<acme:button code="developer.training-sesion.list.button.create" action="/developer/training-sesion/create?masterId=${masterId}"/>
	</jstl:when>
</jstl:choose>