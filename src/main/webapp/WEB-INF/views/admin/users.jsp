<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section>
<h3>Lista użytkowników</h3>
<a href="">Dodaj użytkownika</a>
<div>
    <table>
        <tr>
            <th>Login</th>
            <th>Email</th>
            <th>Akcje</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>
                    <a href="">Modyfikuj</a> &nbsp;|&nbsp;
                    <a href="">Usuń</a> &nbsp;|&nbsp;
                    <a href="">Dodaj role admin</a>
                    <a href="">Usuń role admin</a>
                </td>
            </tr>
        </c:forEach>
    </table></div>
</section>
<br><br>