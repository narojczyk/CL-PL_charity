<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h3>Lista fundacji</h3>
<a href="">Dodaj fundację</a>
<div>
    <table>
        <tr>
            <th>Nazwa</th>
            <th>Opis</th>
            <th>Akcje</th>
        </tr>
        <c:forEach items="${institutions}" var="inst">
            <tr>
                <td>${inst.name}</td>
                <td>${inst.description}</td>
                <td>
                    <a href="">Modyfikuj</a> &nbsp;|&nbsp;
                    <a href="">Usuń</a>
                </td>
            </tr>
        </c:forEach>
    </table></div>

<br><br>