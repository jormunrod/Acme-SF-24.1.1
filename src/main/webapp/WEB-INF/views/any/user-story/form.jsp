<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="any.user-story.form.label.title" path="title"/>
	<acme:input-textarea code="any.user-story.form.label.description" path="description"/>
	<acme:input-textbox code="any.user-story.form.label.estimatedHours" path="estimatedHours"/>
	<acme:input-textarea code="any.user-story.form.label.acceptanceCriteria" path="acceptanceCriteria"/>
	<acme:input-select code="any.user-story.form.label.priority" path="priority" choices="${priority}"/>
	<acme:input-url code="any.user-story.form.label.link" path="link"/>
</acme:form>