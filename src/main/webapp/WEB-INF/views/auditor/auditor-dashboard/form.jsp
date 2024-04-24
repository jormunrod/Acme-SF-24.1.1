<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<h2>
	<acme:message code="auditor.auditor-dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row"> <acme:message 
				code="auditor.auditor-dashboard.form.label.total-number-of-static-code-audits" />
			</th>
			<td>
			    <acme:print value="${totalNumberOfStaticCodeAudits}"/>
			</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="auditor.auditor-dashboard.form.label.total-number-of-dynamic-code-audits" />
		</th>
		<td>
			<acme:print value="${totalNumberOfDynamicCodeAudits}" />
		</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="auditor.auditor-dashboard.form.label.average-number-of-audit-records" />
		</th>
		<td>
			<acme:print value="${averageNumberOfAuditRecord}" />
		</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="auditor.auditor-dashboard.form.label.deviation-number-of-audit-record" />
		</th>
		<td>
			<acme:print value="${deviationNumberOfAuditRecord}" />
		</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="auditor.auditor-dashboard.form.label.minimum-number-of-audit-record" />
		</th>
		<td>
			<acme:print value="${minimumNumberOfAuditRecord}" />
		</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="auditor.auditor-dashboard.form.label.maximum-number-of-audit-record" />
		</th>
		<td>
			<acme:print value="${maximumNumberOfAuditRecord}" />
		</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="auditor.auditor-dashboard.form.label.average-time-of-period-in-audit-record" />
		</th>
		<td>
			<acme:print value="${averageTimeOfPeriodInAuditRecord}" />
		</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="auditor.auditor-dashboard.form.label.deviation-time-of-period-in-audit-record" />
		</th>
		<td>
			<acme:print value="${deviationTimeOfPeriodInAuditRecord}" />
		</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="auditor.auditor-dashboard.form.label.minimum-time-of-period-in-audit-record" />
		</th>
		<td>
			<acme:print value="${minimumTimeOfPeriodInAuditRecord}" />
		</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="auditor.auditor-dashboard.form.label.maximum-time-of-period-in-audit-record" />
		</th>
		<td>
			<acme:print value="${maximumTimeOfPeriodInAuditRecord}" />
		</td>
	</tr>
</table>
<acme:return/>