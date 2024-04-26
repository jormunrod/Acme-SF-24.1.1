<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="administrator.spam.list.label.spanishWord" path="spanishWord" width="50%"/>"
	<acme:list-column code="administrator.spam.list.label.englishWord" path="englishWord" width="50%"/>
</acme:list>

<jstl:if test="${_command == 'list'}">
    <acme:button code="administrator.spam.list.button.create" action="/administrator/spam/create"/>
</jstl:if>