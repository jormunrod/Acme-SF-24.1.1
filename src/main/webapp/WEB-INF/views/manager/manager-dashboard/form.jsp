<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<h2>
	<acme:message code="manager.manager-dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="manager.manager-dashboard.form.label.total-number-must-user-stories"/>
			</th>
			<td>
			    <acme:print value="${totalNumberOfMUSTUserStories}"/>
			</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="manager.manager-dashboard.form.label.total-number-should-user-stories" />
		</th>
		<td>
			<acme:print value="${totalNumberOfSHOULDUserStories}" />
		</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="manager.manager-dashboard.form.label.total-number-could-user-stories" />
		</th>
		<td>
			<acme:print value="${totalNumberOfCOULDUserStories}" />
		</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="manager.manager-dashboard.form.label.total-number-wont-user-stories" />
		</th>
		<td>
			<acme:print value="${totalNumberOfWONTUserStories}" />
		</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="manager.manager-dashboard.form.label.average-number-of-estimated-hours" />
		</th>
		<td>
			<acme:print value="${averageNumberOfEstimatedHours}" />
		</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="manager.manager-dashboard.form.label.deviation-number-of-estimated-hours" />
		</th>
		<td>
			<acme:print value="${deviationNumberOfEstimatedHours}" />
		</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="manager.manager-dashboard.form.label.minimum-number-of-estimated-hours" />
		</th>
		<td>
			<acme:print value="${minimumNumberOfEstimatedHours}" />
		</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="manager.manager-dashboard.form.label.maximum-number-of-cost" />
		</th>
		<td>
			<acme:print value="${maximumNumberOfCost}" />
		</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="manager.manager-dashboard.form.label.average-number-of-cost" />
		</th>
		<td>
			<acme:print value="${averageNumberOfCost}" />
		</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="manager.manager-dashboard.form.label.deviation-number-of-estimated-hours" />
		</th>
		<td>
			<acme:print value="${deviationNumberOfEstimatedHours}" />
		</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="manager.manager-dashboard.form.label.minimum-number-of-cost" />
		</th>
		<td>
			<acme:print value="${minimumNumberOfCost}" />
		</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="manager.manager-dashboard.form.label.maximum-number-of-cost" />
		</th>
		<td>
			<acme:print value="${maximumNumberOfCost}" />
		</td>
	</tr>
</table>
<acme:return/>