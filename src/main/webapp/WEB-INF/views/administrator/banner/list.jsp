<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="administrator.banner.list.label.instantiationMoment" path="instantiationMoment" width="30%"/>"
	<acme:list-column code="administrator.banner.list.label.updateMoment" path="updateMoment" width="30%"/>
	<acme:list-column code="administrator.banner.list.label.displayStart" path="displayStart" width="20%"/>
	<acme:list-column code="administrator.banner.list.label.displayEnd" path="displayEnd" width="20%"/>
</acme:list>

<jstl:if test="${_command == 'list'}">
    <acme:button code="administrator.banner.list.button.create" action="/administrator/banner/create"/>
</jstl:if>