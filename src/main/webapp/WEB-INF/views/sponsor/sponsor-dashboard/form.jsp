<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<h2>
	<acme:message code="sponsor.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.total-number-of-invoices-with-a-tax-less-than-or-equal-to-21"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfInvoicesWithATaxLessThanOrEqualTo21}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.total-number-of-sponsorships-with-a-link"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfSponsorshipsWithALink}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.average-amount-of-the-sponsorships-eur"/>
		</th>
		<td>
			<acme:print value="${averageAmountOfTheSponsorshipsEUR}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.average-amount-of-the-sponsorships-usd"/>
		</th>
		<td>
			<acme:print value="${averageAmountOfTheSponsorshipsUSD}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.average-amount-of-the-sponsorships-gbp"/>
		</th>
		<td>
			<acme:print value="${averageAmountOfTheSponsorshipsGBP}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.deviation-amount-of-the-sponsorships-eur"/>
		</th>
		<td>
			<acme:print value="${deviationAmountOfTheSponsorshipsEUR}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.deviation-amount-of-the-sponsorships-usd"/>
		</th>
		<td>
			<acme:print value="${deviationAmountOfTheSponsorshipsUSD}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.deviation-amount-of-the-sponsorships-gbp"/>
		</th>
		<td>
			<acme:print value="${deviationAmountOfTheSponsorshipsGBP}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.minimum-amount-of-the-sponsorships-eur"/>
		</th>
		<td>
			<acme:print value="${minimumAmountOfTheSponsorshipsEUR}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.minimum-amount-of-the-sponsorships-usd"/>
		</th>
		<td>
			<acme:print value="${minimumAmountOfTheSponsorshipsUSD}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.minimum-amount-of-the-sponsorships-gbp"/>
		</th>
		<td>
			<acme:print value="${minimumAmountOfTheSponsorshipsGBP}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.maximum-amount-of-the-sponsorships-eur"/>
		</th>
		<td>
			<acme:print value="${maximumAmountOfTheSponsorshipsEUR}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.maximum-amount-of-the-sponsorships-usd"/>
		</th>
		<td>
			<acme:print value="${maximumAmountOfTheSponsorshipsUSD}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.maximum-amount-of-the-sponsorships-gbp"/>
		</th>
		<td>
			<acme:print value="${maximumAmountOfTheSponsorshipsGBP}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.average-quantity-of-the-invoices-eur"/>
		</th>
		<td>
			<acme:print value="${averageQuantityOfTheInvoicesEUR}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.average-quantity-of-the-invoices-usd"/>
		</th>
		<td>
			<acme:print value="${averageQuantityOfTheInvoicesUSD}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.average-quantity-of-the-invoices-gbp"/>
		</th>
		<td>
			<acme:print value="${averageQuantityOfTheInvoicesGBP}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.deviation-quantity-of-the-invoices-eur"/>
		</th>
		<td>
			<acme:print value="${deviationQuantityOfTheInvoicesEUR}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.deviation-quantity-of-the-invoices-usd"/>
		</th>
		<td>
			<acme:print value="${deviationQuantityOfTheInvoicesUSD}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.deviation-quantity-of-the-invoices-gbp"/>
		</th>
		<td>
			<acme:print value="${deviationQuantityOfTheInvoicesGBP}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.minimum-quantity-of-the-invoices-eur"/>
		</th>
		<td>
			<acme:print value="${minimumQuantityOfTheInvoicesEUR}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.minimum-quantity-of-the-invoices-usd"/>
		</th>
		<td>
			<acme:print value="${minimumQuantityOfTheInvoicesUSD}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.minimum-quantity-of-the-invoices-gbp"/>
		</th>
		<td>
			<acme:print value="${minimumQuantityOfTheInvoicesGBP}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.maximum-quantity-of-the-invoices-eur"/>
		</th>
		<td>
			<acme:print value="${maximumQuantityOfTheInvoicesEUR}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.maximum-quantity-of-the-invoices-usd"/>
		</th>
		<td>
			<acme:print value="${maximumQuantityOfTheInvoicesUSD}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.maximum-quantity-of-the-invoices-gbp"/>
		</th>
		<td>
			<acme:print value="${maximumQuantityOfTheInvoicesGBP}"/>
		</td>
	</tr>
</table>
