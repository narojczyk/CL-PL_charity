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

        <section>
            <c:if test = "${getResource == 'institutions'}">
                <%@ include file="/WEB-INF/views/admin/insitutions.jsp" %>
            </c:if>

            <c:if test = "${getResource == 'users'}">
                <%@ include file="/WEB-INF/views/admin/users.jsp" %>
            </c:if>
        </section>

    </security:authorize>
        <%--<footer> tag contents </footer>--%>
        <%@ include file="/WEB-INF/includes/footer.jsp" %>
    </body>
</html>
