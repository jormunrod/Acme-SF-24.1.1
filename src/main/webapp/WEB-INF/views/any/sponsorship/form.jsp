<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="sponsor.sponsorship.form.label.code" path="code" />
	<acme:input-select code="sponsor.sponsorship.form.label.project" path="project" choices="${projects}"/>
	<acme:input-select code="sponsor.sponsorship.form.label.sponsorshipType" path="sponsorshipType" choices="${sponsorshipTypes}"/>
			<acme:input-moment code="sponsor.sponsorship.form.label.moment" path="moment" />
			<acme:input-moment code="sponsor.sponsorship.form.label.startDate" path="startDate"/>
			<acme:input-moment code="sponsor.sponsorship.form.label.endDate" path="endDate" />
			<acme:input-email code="sponsor.sponsorship.form.label.contactEmail" path="contactEmail"/>
			<acme:input-money code="sponsor.sponsorship.form.label.amount" path="amount" />
			<acme:input-url code="sponsor.sponsorship.form.label.link" path="link" />
			
</acme:form>
