<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="auditor.code-audit.list.label.code" path="code" width="30%"/>
	<acme:list-column code="auditor.code-audit.list.label.execution" path="execution" width="30%"/>
	<acme:list-column code="auditor.code-audit.list.label.type" path="type" width="20%"/>
	<acme:list-column code="auditor.code-audit.list.label.mark" path="mark" width="10%"/>
	<acme:list-column code="auditor.code-audit.list.label.published" path="isPublished" width="10%"/>
</acme:list>

<jstl:if test="${ _command == 'list'}">
	<acme:button code="auditor.code-audit.list.button.create" action="/auditor/code-audit/create"/>
</jstl:if>