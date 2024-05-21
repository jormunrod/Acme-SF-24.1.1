<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="developer.training-session.list.label.code" path="code" width="25%"/>
	<acme:list-column code="developer.training-session.list.label.startDate" path="startDate" width="25%"/>
	<acme:list-column code="developer.training-session.list.label.location" path="location" width="25%"/>
	<acme:list-column code="developer.training-session.list.label.draftMode" path="draftMode" width="25%"/>
</acme:list>

<jstl:choose>
	<jstl:when test="${ _command == 'list' && showCreate}">
		<acme:button code="developer.training-session.list.button.create" action="/developer/training-session/create?masterId=${masterId}"/>
	</jstl:when>
</jstl:choose>