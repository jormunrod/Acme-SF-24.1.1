<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
    <acme:input-textbox code="any.project.form.label.code" path="code"/>
	<acme:input-textbox code="any.project.form.label.manager" path="manager"/>
	<acme:input-textbox code="any.project.form.label.title" path="title"/>
	<acme:input-textarea code="any.project.form.label.abstractText" path="abstractText"/>
	<acme:input-textbox code="any.project.form.label.cost" path="cost"/>
	<acme:input-url code="any.project.form.label.link" path="link"/>
</acme:form>