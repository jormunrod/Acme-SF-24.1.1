<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="any.user-story.list.label.title" path="title" width="20%"/>"
	<acme:list-column code="any.user-story.list.label.estimatedHours" path="estimatedHours" width="20%"/>
	<acme:list-column code="any.user-story.list.label.priority" path="priority" width="20%"/>
</acme:list>
