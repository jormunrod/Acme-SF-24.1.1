<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="manager.user-story.list.label.title" path="title" width="20%"/>"
	<acme:list-column code="manager.user-story.list.label.estimatedHours" path="estimatedHours" width="20%"/>
	<acme:list-column code="manager.user-story.list.label.priority" path="priority" width="20%"/>
	<acme:list-column code="manager.user-story.list.label.isPublished" path="isPublished" width="20%"/>
</acme:list>

<acme:button test="${ _command == 'list' && projectIsPublished == false}" code="manager.user-story.list.button.create" action="/manager/user-story/create?projectId=${projectId}"/>
