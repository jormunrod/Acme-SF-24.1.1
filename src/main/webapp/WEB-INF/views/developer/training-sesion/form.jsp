<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="developer.training-sesion.form.label.code" path="code"/>
	<acme:input-textbox code="developer.training-sesion.form.label.startDate" path="startDate"/>
	<acme:input-textbox code="developer.training-sesion.form.label.finishDate" path="finishDate"/>
	<acme:input-textbox code="developer.training-sesion.form.label.location" path="location"/>
	<acme:input-textbox code="developer.training-sesion.form.label.instructor" path="instructor"/>
	<acme:input-textbox code="developer.training-sesion.form.label.contactEmail" path="contactEmail"/>
	<acme:input-textbox code="developer.training-sesion.form.label.link" path="link"/>
</acme:form>