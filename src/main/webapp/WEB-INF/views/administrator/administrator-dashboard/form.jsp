<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<h2>
	<acme:message code="dashboard.form.title.principal-statistics"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="dashboard.form.label.total-number-of-auditor-principals"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfAuditorPrincipals}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="dashboard.form.label.total-number-of-client-principals"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfClientPrincipals}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="dashboard.form.label.total-number-of-consumer-principals"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfConsumerPrincipals}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="dashboard.form.label.total-number-of-developer-principals"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfDeveloperPrincipals}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="dashboard.form.label.total-number-of-manager-principals"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfManagerPrincipals}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="dashboard.form.label.total-number-of-provider-principals"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfProviderPrincipals}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="dashboard.form.label.total-number-of-sponsor-principals"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfSponsorPrincipals}"/>
		</td>
	</tr>

	<!-- Ratios -->
	<tr>
		<th scope="row">
			<acme:message code="dashboard.form.label.ratio-of-notices-with-email-and-link"/>
		</th>
		<td>
			<acme:print value="${ratioOfNoticesWithEmailAndLink}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="dashboard.form.label.ratio-of-critical-objectives"/>
		</th>
		<td>
			<acme:print value="${ratioOfCriticalObjectives}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="dashboard.form.label.ratio-of-non-critical-objectives"/>
		</th>
		<td>
			<acme:print value="${ratioOfNonCriticalObjectives}"/>
		</td>
	</tr>

	<!-- Risk Values -->
	<tr>
		<th scope="row">
			<acme:message code="dashboard.form.label.average-risk-value"/>
		</th>
		<td>
			<acme:print value="${averageRiskValue}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="dashboard.form.label.minimum-risk-value"/>
		</th>
		<td>
			<acme:print value="${minimumRiskValue}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="dashboard.form.label.maximum-risk-value"/>
		</th>
		<td>
			<acme:print value="${maximumRiskValue}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="dashboard.form.label.standard-deviation-risk-value"/>
		</th>
		<td>
			<acme:print value="${standardDeviationRiskValue}"/>
		</td>
	</tr>

	<!-- Claims Statistics -->
	<tr>
		<th scope="row">
			<acme:message code="dashboard.form.label.average-number-of-claims-in-last-ten-weeks"/>
		</th>
		<td>
			<acme:print value="${averageNumberOfClaimsInLastTenWeeks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="dashboard.form.label.minimum-number-of-claims-in-last-ten-weeks"/>
		</th>
		<td>
			<acme:print value="${minimumNumberOfClaimsInLastTenWeeks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="dashboard.form.label.maximum-number-of-claims-in-last-ten-weeks"/>
		</th>
		<td>
			<acme:print value="${maximumNumberOfClaimsInLastTenWeeks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="dashboard.form.label.standard-deviation-number-of-claims-in-last-ten-weeks"/>
		</th>
		<td>
			<acme:print value="${standardDeviationNumberOfClaimsInLastTenWeeks}"/>
		</td>
	</tr>
</table>
