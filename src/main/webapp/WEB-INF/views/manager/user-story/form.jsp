<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>"

<acme:form>
	<acme:input-textbox code="manager.user-story.form.label.title" path="title"/>
	<acme:input-textarea code="manager.user-story.form.label.description" path="description"/>
	<acme:input-textbox code="manager.user-story.form.label.estimatedHours" path="estimatedHours"/>
	<acme:input-textarea code="manager.user-story.form.label.acceptanceCriteria" path="acceptanceCriteria"/>
	<acme:input-select code="manager.user-story.form.label.priority" path="priority" choices="${priority}"/>
	<acme:input-url code="manager.user-story.form.label.link" path="link"/>
	<acme:input-select code="manager.user-story.form.label.project" path="project" choices="${project}"/>
	<acme:input-checkbox code="manager.user-story.form.label.isPublished" readonly="true" path="isPublished"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && isPublished == false}">
			<acme:submit code="manager.project.form.button.delete" action="/manager/project/delete"/>
			<acme:submit code="manager.project.form.button.update" action="/manager/project/update"/>
			<acme:submit code="manager.project.form.button.publish" action="/manager/project/publish"/>
		</jstl:when>

		<jstl:when test="${_command == 'create'}">
			<acme:submit code="manager.project.form.button.create" action="/manager/project/create"/>
		</jstl:when>
	</jstl:choose>
</acme:form>