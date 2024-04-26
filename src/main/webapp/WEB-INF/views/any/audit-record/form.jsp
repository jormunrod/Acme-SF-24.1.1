<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="any.audit-record.form.label.code" path="code"/>
	<acme:input-moment code="any.audit-record.form.label.auditPeriodStart" path="auditPeriodStart"/>
	<acme:input-moment code="any.audit-record.form.label.auditPeriodEnd" path="auditPeriodEnd"/>
	<acme:input-select code="any.audit-record.form.label.mark" path="mark" choices="${mark}"/>
	<acme:input-url code="any.audit-record.form.label.link" path="link"/>
</acme:form>