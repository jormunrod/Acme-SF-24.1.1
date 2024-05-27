<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="auditor.audit-record.form.label.code" path="code" placeholder="AU-0000-000"/>
	<acme:input-moment code="auditor.audit-record.form.label.auditPeriodStart" path="auditPeriodStart"/>
	<acme:input-moment code="auditor.audit-record.form.label.auditPeriodEnd" path="auditPeriodEnd"/>
	<acme:input-select code="auditor.audit-record.form.label.mark" path="mark" choices="${mark}"/>
	<acme:input-url code="auditor.audit-record.form.label.link" path="link"/>
	
	<jstl:choose>
		<jstl:when test="${_command == 'show' && isPublished == true}">
		</jstl:when>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && isPublished == false}">
			<acme:submit code="auditor.audit-record.form.button.delete" action="/auditor/audit-record/delete"/>
			<acme:submit code="auditor.audit-record.form.button.update" action="/auditor/audit-record/update"/>
			<acme:submit code="auditor.audit-record.form.button.publish" action="/auditor/audit-record/publish"/>
		</jstl:when>

		<jstl:when test="${_command == 'create'}">
			<acme:submit code="auditor.audit-record.form.button.create" action="/auditor/audit-record/create?codeAuditId=${codeAuditId}"/>
		</jstl:when>
	</jstl:choose>
</acme:form>