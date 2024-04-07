<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="developer.training-sesion.form.label.code" path="code"/>
	<acme:input-moment code="developer.training-sesion.form.label.startDate" path="startDate"/>
	<acme:input-moment code="developer.training-sesion.form.label.finishDate" path="finishDate"/>
	<acme:input-textbox code="developer.training-sesion.form.label.location" path="location"/>
	<acme:input-textbox code="developer.training-sesion.form.label.instructor" path="instructor"/>
	<acme:input-email code="developer.training-sesion.form.label.contactEmail" path="contactEmail"/>
	<acme:input-url code="developer.training-sesion.form.label.link" path="link"/>
	
	<jstl:choose>
		<jstl:when test="${_command == 'show'}">
			<acme:submit code="developer.training-sesion.form.button.delete" action="/developer/training-sesion/delete"/>
			<acme:submit code="developer.training-sesion.form.button.update" action="/developer/training-sesion/update"/>
		</jstl:when>
		
		<jstl:when test="${_command == 'create' }">
			<acme:submit code="developer.training-sesion.form.button.create" action="/developer/training-sesion/create"/>
		</jstl:when>
	</jstl:choose>
	
</acme:form>

