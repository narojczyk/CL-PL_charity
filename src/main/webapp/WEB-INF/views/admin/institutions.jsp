<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section>
<h3>Lista fundacji</h3>
<a href="/admin/institutions/add">Dodaj fundację</a>
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
                    <a href="/admin/institutions/modify?id=${inst.id}">Modyfikuj</a> &nbsp;|&nbsp;
                    <a href="/admin/institutions/delete?id=${inst.id}">Usuń</a>
                </td>
            </tr>
        </c:forEach>
    </table></div>
</section>
<br><br>