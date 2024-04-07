<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="developer.training-module.form.label.code" path="code"/>
	<acme:input-moment code="developer.training-module.form.label.creationMoment" path="creationMoment"/>
	<acme:input-textbox code="developer.training-module.form.label.difficultyLevel" path="difficultyLevel"/>
	<acme:input-moment code="developer.training-module.form.label.updateMoment" path="updateMoment"/>
	<acme:input-textarea code="developer.training-module.form.label.details" path="details"/>
	<acme:input-url code="developer.training-module.form.label.link" path="link"/>
	<acme:input-integer code="developer.training-module.form.label.totalTime" path="totalTime"/>

	<jstl:choose>
		<jstl:when test="${_command == 'show'}">
			<acme:submit code="developer.training-module.form.button.delete" action="/developer/training-module/delete"/>
			<acme:submit code="developer.training-module.form.button.update" action="/developer/training-module/update"/>
		</jstl:when>
		
		<jstl:when test="${_command == 'create' }">
			<acme:submit code="developer.training-module.form.button.create" action="/developer/training-module/create"/>
		</jstl:when>
	</jstl:choose>
</acme:form>
