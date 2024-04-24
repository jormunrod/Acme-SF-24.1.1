<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="auditor.audit-record.list.label.code" path="code" width="10%"/>
	<acme:list-column code="auditor.audit-record.list.label.auditPeriodStart" path="auditPeriodStart" width="30%"/>
	<acme:list-column code="auditor.audit-record.list.label.auditPeriodEnd" path="auditPeriodEnd" width="30%"/>
	<acme:list-column code="auditor.audit-record.list.label.mark" path="mark" width="10%"/>
	<acme:list-column code="auditor.audit-record.list.label.isPublished" path="isPublished" width="20%"/>
</acme:list>

<acme:button test="${showCreate}" code="auditor.audit-record.list.button.create" action="/auditor/audit-record/create?codeAuditId=${codeAuditId}"/>
