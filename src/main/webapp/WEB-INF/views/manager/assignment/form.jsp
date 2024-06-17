<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="manager.assignment.form.label.project-title" readonly="true" path="project-title"/>
	<acme:input-textbox code="manager.assignment.form.label.project-description" readonly="true" path="project-description"/>
	<acme:input-textbox code="manager.assignment.form.label.project-cost" readonly="true" path="project-cost"/>
	<acme:input-select code="manager.assignment.form.label.user-story" path="userStories" choices="${userStories}"/>
	<jstl:choose>
			<jstl:when test="${_command == 'create'}">
			<acme:submit code="manager.assignment.form.button.create"
				action="/manager/assignment/create?projectId=${projectId}" />
			</jstl:when>

			<jstl:when test="${_command == 'delete'}">
				<acme:submit code="manager.assignment.form.button.delete"
					action="/manager/assignment/delete?projectId=${projectId}" />
			</jstl:when>
	</jstl:choose>
</acme:form>