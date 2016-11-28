<%@include file="/WEB-INF/includes/general.jsp" %>
<%@include file="/WEB-INF/includes/header.jsp" %>
<fmt:setBundle basename="webProject.i18n.admin.general" var="general"/>

<div class="row">
    <h1><fmt:message key="accessDenied" bundle="${general}"/></h1>
    <div class="col-md-8 col-md-offset-2">
        <p>Sorry, ${sessionScope.thisUser.userName}, access denied! You don't have necessary permissions.</p>


    </div>

</div>

<%@include file="/WEB-INF/includes/footer.jsp" %>