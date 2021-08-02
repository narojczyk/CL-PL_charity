<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>

<!DOCTYPE html>
<html lang="pl">

    <%@ include file="/WEB-INF/includes/header.jsp" %>

    <body>
        <header>
            <%--<headder> tag contents </headder>--%>
            <security:authorize access="isAuthenticated()">
                <%@ include file="/WEB-INF/includes/top-nav-options-auth.jsp" %>
            </security:authorize>

            <security:authorize access="isAnonymous()">
                <%@ include file="/WEB-INF/includes/top-nav-options.jsp" %>
            </security:authorize>
        </header>

        <section class="login-page">
            <h2>Załóż konto</h2>
            <form action="/registration" method="post">
                <div class="form-group">
                    <input type="text" name="username" placeholder="Login" />
                </div>
                <div class="form-group">
                    <input type="email" name="email" placeholder="Email" />
                </div>
                <div class="form-group">
                    <input type="password" name="passwd" placeholder="Hasło" />
                </div>
                <div class="form-group">
                    <input type="password" name="passwdConfirmed" placeholder="Powtórz hasło" />
                </div>

                <div class="form-group form-group--buttons">
                    <input class="btn" type="submit" value="Załóż konto">
                    <a href="login.html" class="btn btn--without-border">Zaloguj się</a>
                </div>
            </form>
        </section>

        <%--<footer> tag contents </footer>--%>
        <%@ include file="/WEB-INF/includes/footer.jsp" %>
    </body>
</html>
