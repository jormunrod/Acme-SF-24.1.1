<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="authenticated.objective.form.label.title" path="title"/>
	<acme:input-textarea code="authenticated.objective.form.label.description" path="description"/>
	<acme:input-moment code="authenticated.objective.form.label.instantiationMoment" path="instantiationMoment"/>
	<acme:input-textbox code="authenticated.objective.form.label.priority" path="priority"/>
	<acme:input-textbox code="authenticated.objective.form.label.startDate" path="startDate"/>
	<acme:input-moment code="authenticated.objective.form.label.endDate" path="endDate"/>
	<acme:input-url code="authenticated.objective.form.label.link" path="link"/>
	<acme:input-checkbox code="authenticated.objective.form.label.isCritical" path="isCritical"/>

    <jstl:if test="${!readonly}">
		<acme:input-checkbox code="authenticated.objective.form.label.confirmation" path="confirmation"/>
		<acme:submit code="authenticated.objective.form.button.create" action="/administrator/announcement/create"/>
	</jstl:if>
</acme:form>