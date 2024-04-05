<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="sponsor.sponsorship.form.label.code" path="code" />
			<acme:input-textbox code="sponsor.sponsorship.form.label.moment" path="moment" />
			<acme:input-textbox code="sponsor.sponsorship.form.label.startDate" path="startDate"/>
			<acme:input-textbox code="sponsor.sponsorship.form.label.endDate" path="endDate" />
			<acme:input-textbox code="sponsor.sponsorship.form.label.contactEmail" path="contactEmail"/>
			<acme:input-textbox code="sponsor.sponsorship.form.label.amount" path="amount" />
			<acme:input-textbox code="sponsor.sponsorship.form.label.link" path="link" />
</acme:form>
