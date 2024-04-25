<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
    <acme:input-textbox code="any.code-audit.form.label.auditor" path="auditor"/>
	<acme:input-textbox code="any.code-audit.form.label.code" path="code"/>
	<acme:input-select code="any.code-audit.form.label.project" path="project" choices="${projects}"/>
	<acme:input-moment code="any.code-audit.form.label.execution" path="execution"/>
	<acme:input-select code="any.code-audit.form.label.type" path="type" choices="${types}"/>
	<acme:input-textarea code="any.code-audit.form.label.correctiveActions" path="correctiveActions"/>
	<acme:input-textarea code="any.code-audit.form.label.mark" path="mark"/>
	<acme:input-url code="any.code-audit.form.label.link" path="link"/>			

	<acme:button code="any.code-audit.form.button.audit-record" action="/any/audit-record/list?id=${id}"/>

</acme:form>