<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="authenticated.auditor.form.label.firm" path="firm" placeholder=""/>
	<acme:input-textbox code="authenticated.auditor.form.label.professionalID" path="professionalID" placeholder=""/>
	<acme:input-textarea code="authenticated.auditor.form.label.certifications" path="certifications" placeholder=""/>
	<acme:input-url code="authenticated.auditor.form.label.link" path="link"/>

	<acme:submit test="${_command == 'create'}" code="authenticated.auditor.form.button.create" action="/authenticated/auditor/create"/>
</acme:form>