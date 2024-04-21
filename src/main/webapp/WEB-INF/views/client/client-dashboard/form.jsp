<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<h2>
	<acme:message code="client.client-dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.number-of-progress-logs-below-25"/>
		</th>
		<td>
			<acme:print value="${numberOfProgressLogsBelow25}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.number-of-progress-logs-between-25-and-50"/>
		</th>
		<td>
			<acme:print value="${numberOfProgressLogsBetween25And50}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.number-of-progress-logs-between-50-and-75"/>
		</th>
		<td>
			<acme:print value="${numberOfProgressLogsBetween50And75}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.number-of-progress-logs-above-75"/>
		</th>
		<td>
			<acme:print value="${numberOfProgressLogsAbove75}"/>
		</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="client.client-dashboard.form.label.average-budget" />
		</th>
		<td>
			<acme:print value="${averageBudget}" />
		</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="client.client-dashboard.form.label.deviation-budget" />
		</th>
		<td>
			<acme:print value="${deviationBudget}" />
		</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="client.client-dashboard.form.label.minimum-budget" />
		</th>
		<td>
			<acme:print value="${minimumBudget}" />
		</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="client.client-dashboard.form.label.maximum-budget" />
		</th>
		<td>
			<acme:print value="${maximumBudget}" />
		</td>
	</tr>
</table>
