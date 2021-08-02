<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="container container--70">
    <ul class="nav--actions">
        <li class="logged-user">
            Witaj Username
            <ul class="dropdown">
                <li><a href="#">Profil</a></li>
                <li><a href="#">Moje zbiórki</a></li>
                <li><a href="/logout">Wyloguj</a></li>
            </ul>
        </li>
    </ul>

    <%@ include file="/WEB-INF/includes/top-nav-options-common.jsp" %>
</nav>