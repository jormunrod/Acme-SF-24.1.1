<%--
- form.jsp
-
- Copyright (C) 2012-2024 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<h2>
	<acme:message code="developer.developer-dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="developer.developer-dashboard.form.label.total-number-of-training-module-with-an-update-moment"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfTrainingModuleWithAnUpdateMoment}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="developer.developer-dashboard.form.label.total-number-of-training-sesion-with-a-link"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfTrainingSessionsWithALink}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="developer.developer-dashboard.form.label.average"/>
		</th>
		<td>
			<acme:print value="${average}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="developer.developer-dashboard.form.label.deviation"/>
		</th>
		<td>
			<acme:print value="${deviation}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="developer.developer-dashboard.form.label.minimum-time-of-the-training-module"/>
		</th>
		<td>
			<acme:print value="${minimumTimeOfTheTrainingModules}"/>
		</td>
	</tr>	
		<tr>
		<th scope="row">
			<acme:message code="developer.developer-dashboard.form.label.maximum-time-of-the-training-module"/>
		</th>
		<td>
			<acme:print value="${maximumTimeOfTheTrainingModules}"/>
		</td>
	</tr>
</table>
