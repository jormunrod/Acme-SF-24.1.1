<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="authenticated.claim.form.label.code" path="code" placeholder="C-0000"/>
	<acme:input-moment code="authenticated.claim.form.label.instantiationMoment" readonly="true" path="instantiationMoment"/>
	<acme:input-textbox code="authenticated.claim.form.label.heading" path="heading"/>
	<acme:input-textarea code="authenticated.claim.form.label.description" path="description"/>
	<acme:input-textbox code="authenticated.claim.form.label.department" path="department"/>
	<acme:input-email code="authenticated.claim.form.label.email" path="email"/>
	<acme:input-url code="authenticated.claim.form.label.link" path="link"/>
	
	
	<acme:submit test="${_command == 'create'}" code="authenticated.claim.form.button.create" action="/authenticated/claim/create"/>
	<acme:submit test="${_command == 'update'}" code="authenticated.claim.form.button.update" action="/authenticated/claim/update"/>
</acme:form>
