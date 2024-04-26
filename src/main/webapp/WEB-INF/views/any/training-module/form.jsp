<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="developer.training-module.form.label.code" path="code" placeholder="AAA-000"/>
	<acme:input-select code="developer.training-module.form.label.project" path="project" choices="${projects}"/>
	<acme:input-moment code="developer.training-module.form.label.creationMoment" path="creationMoment"/>
	<acme:input-select code="developer.training-module.form.label.difficultyLevel" path="difficultyLevel" choices = "${difficultyLevels}"/>
	<acme:input-moment code="developer.training-module.form.label.updateMoment" path="updateMoment" readonly="true" placeholder=""/>
	<acme:input-textarea code="developer.training-module.form.label.details" path="details"/>
	<acme:input-url code="developer.training-module.form.label.link" path="link"/>
	<acme:input-integer code="developer.training-module.form.label.totalTime" path="totalTime"/>
</acme:form>
