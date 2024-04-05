<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
		<acme:list-column code="developer.training-sesion.list.label.code" path="code" width="20%"/>
		<acme:list-column code="developer.training-sesion.list.label.startDate" path="startDate" width="10%"/>
		<acme:list-column code="developer.training-sesion.list.label.finishDate" path="finishDate" width="10%"/>
		<acme:list-column code="developer.training-sesion.list.label.location" path="location" width="10%"/>
		<acme:list-column code="developer.training-sesion.list.label.instructor" path="instructor" width="20%"/>
		<acme:list-column code="developer.training-sesion.list.label.contactEmail" path="contactEmail" width="10%"/>
		<acme:list-column code="developer.training-sesion.list.label.link" path="link" width="20%"/>
</acme:list>