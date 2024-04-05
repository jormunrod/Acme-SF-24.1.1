<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="developer.training-module.form.label.code" path="code"/>
	<acme:input-textbox code="developer.training-module.form.label.creationMoment" path="creationMoment"/>
	<acme:input-textbox code="developer.training-module.form.label.difficultyLevel" path="difficultyLevel"/>
	<acme:input-textbox code="developer.training-module.form.label.updateMoment" path="updateMoment"/>
	<acme:input-textbox code="developer.training-module.form.label.details" path="details"/>
	<acme:input-textbox code="developer.training-module.form.label.link" path="link"/>
	<acme:input-textbox code="developer.training-module.form.label.totalTime" path="totalTime"/>
	
	<acme:button code="developer.training-module.form.button.training-sessions" action="/developer/training-sesion/list?id=${id}"/>
</acme:form>
