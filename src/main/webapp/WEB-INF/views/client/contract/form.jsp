<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="client.contract.form.label.code" path="code" placeholder="AAA-000"/>
	<acme:input-select code="client.contract.form.label.project" path="project" choices="${projects}"/>
	<acme:input-moment code="client.contract.form.label.instantiationMoment" readonly="true" path="instantiationMoment"/>
	<acme:input-textbox code="client.contract.form.label.providerName" path="providerName"/>
	<acme:input-textbox code="client.contract.form.label.customerName" path="customerName"/>
	<acme:input-textarea code="client.contract.form.label.goals" path="goals"/>
	<acme:input-money code="client.contract.form.label.budget" path="budget"/>
	<acme:input-checkbox code="client.contract.form.label.publish" readonly="true" path="isPublished"/>

	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete')}">
			<acme:submit code="client.contract.form.button.delete" action="/client/contract/delete"/>
			<acme:submit code="client.contract.form.button.update" action="/client/contract/update"/>
		</jstl:when>

		<jstl:when test="${_command == 'create' }">
			<acme:submit code="client.contract.form.button.create" action="/client/contract/create"/>
		</jstl:when>
	</jstl:choose>
</acme:form>