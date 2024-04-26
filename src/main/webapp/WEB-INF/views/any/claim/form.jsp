<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="any.claim.form.label.code" path="code" placeholder="C-0000"/>
	<acme:input-moment code="any.claim.form.label.instantiationMoment" readonly="true" path="instantiationMoment"/>
	<acme:input-textbox code="any.claim.form.label.heading" path="heading"/>
	<acme:input-textarea code="any.claim.form.label.description" path="description"/>
	<acme:input-textbox code="any.claim.form.label.department" path="department"/>
	<acme:input-email code="any.claim.form.label.email" path="email"/>
	<acme:input-url code="any.claim.form.label.link" path="link"/>
	
	
	<acme:submit test="${_command == 'create'}" code="any.claim.form.button.create" action="/any/claim/create"/>
</acme:form>
