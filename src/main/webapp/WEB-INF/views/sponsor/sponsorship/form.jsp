<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="sponsor.sponsorship.form.label.code" path="code" placeholder = "AAA-000 ([A-Z]{1,3}-[0-9]{3})"/>
	<acme:input-select code="sponsor.sponsorship.form.label.project" path="project" choices="${projects}"/>
	<acme:input-select code="sponsor.sponsorship.form.label.sponsorshipType" path="sponsorshipType" choices="${sponsorshipTypes}"/>
			<acme:input-moment code="sponsor.sponsorship.form.label.moment" path="moment" readonly="true" placeholder=""/>
			<acme:input-moment code="sponsor.sponsorship.form.label.startDate" path="startDate"/>
			<acme:input-moment code="sponsor.sponsorship.form.label.endDate" path="endDate" />
			<acme:input-email code="sponsor.sponsorship.form.label.contactEmail" path="contactEmail"/>
			<acme:input-money code="sponsor.sponsorship.form.label.amount" path="amount" />
			<acme:input-url code="sponsor.sponsorship.form.label.link" path="link" />
			
			<jstl:choose> 
        <jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && draftMode == true}">
        	
            <acme:submit code="sponsor.sponsorship.list.button.update" action="/sponsor/sponsorship/update"/>
            <acme:submit code="sponsor.sponsorship.list.button.delete" action="/sponsor/sponsorship/delete"/>
            <acme:submit code="sponsor.sponsorship.list.button.publish" action="/sponsor/sponsorship/publish"/>
            <acme:button code="sponsor.sponsorship.list.button.inovices" action="/sponsor/invoice/list?id=${id}"/>
        </jstl:when>
        
	       <jstl:when test="${draftMode == false}">
        	
            <acme:button code="sponsor.sponsorship.list.button.inovices" action="/sponsor/invoice/list?id=${id}"/>
        </jstl:when>
        

        <jstl:when test="${_command == 'create'}">
            <acme:submit code="sponsor.sponsorship.list.button.create" action="/sponsor/sponsorship/create"/>
        </jstl:when>
    </jstl:choose>
			
</acme:form>
