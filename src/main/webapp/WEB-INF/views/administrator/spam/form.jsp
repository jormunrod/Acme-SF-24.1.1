<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textarea code="administrator.spam.form.label.spanishWord" path="spanishWord"/>
	<acme:input-textarea code="administrator.spam.form.label.englishWord" path="englishWord"/>

	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete')}">
			<acme:submit code="administrator.spam.form.button.update" action="/administrator/spamspam/update"/>
			<acme:submit code="administrator.spam.form.button.delete" action="/administrator/spam/delete"/>
		</jstl:when>
		
		<jstl:when test="${_command == 'create' }">
			<acme:submit code="administrator.spam.form.button.create" action="/administrator/spam/create"/>
		</jstl:when>
	</jstl:choose>
</acme:form>