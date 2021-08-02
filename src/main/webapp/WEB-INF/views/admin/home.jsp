<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>

<!DOCTYPE html>
<html lang="pl">

    <%@ include file="/WEB-INF/includes/header.jsp" %>

    <body>
        <header>
            <%--<headder> tag contents </headder>--%>
            <%@ include file="/WEB-INF/includes/top-nav-options-auth.jsp" %>
        </header>

    <security:authorize access="hasRole('ADMIN')">


        <c:if test = "${getResource == 'institutions'}">
            <%@ include file="/WEB-INF/views/admin/institutions.jsp" %>
        </c:if>

        <c:if test = "${getResource == 'modify-inst-form'}">
            <%@ include file="/WEB-INF/views/admin/institutions-add-update.jsp" %>
        </c:if>

        <c:if test = "${getResource == 'add-inst-form'}">
            <%@ include file="/WEB-INF/views/admin/institutions-add-update.jsp" %>
        </c:if>

        <c:if test = "${getResource == 'delete-inst-form'}">
            <%@ include file="/WEB-INF/views/admin/institutions-delete.jsp" %>
        </c:if>

        <c:if test = "${getResource == 'users'}">
            <%@ include file="/WEB-INF/views/admin/users.jsp" %>
        </c:if>


    </security:authorize>
        <%--<footer> tag contents </footer>--%>
        <%@ include file="/WEB-INF/includes/footer.jsp" %>
    </body>
</html>
