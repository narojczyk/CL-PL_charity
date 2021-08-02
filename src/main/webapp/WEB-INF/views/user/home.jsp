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

        <security:authorize access="hasRole('USER')">
            <a href="/user/mypanel">O mnie</a><br>
            <a href="/user/donations">Moje dary</a><br>

            <br><br>

            <c:if test = "${getResource == 'panel'}">
                <%@ include file="/WEB-INF/views/user/panel-info.jsp" %>
            </c:if>

            <c:if test = "${getResource == 'donations'}">
                <%@ include file="/WEB-INF/views/user/donations.jsp" %>
            </c:if>

        </security:authorize>
        <%--<footer> tag contents </footer>--%>
        <%@ include file="/WEB-INF/includes/footer.jsp" %>
    </body>
</html>
