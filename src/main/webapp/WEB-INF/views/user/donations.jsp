<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section>
    <h3>Lista darów</h3>
    <a href="/donations/add">Przekaż dary</a>
    <div>
        <table>
            <tr>
                <th>Odebrana</th>
                <th>Ilość worków</th>
                <th>Przekazane dla</th>
            </tr>
            <c:forEach items="${donations}" var="don">
                <tr>
                    <td>${don.pickUpDate}</td>
                    <td>${don.quantity}</td>
                    <td>${don.institution}</td>
<%--                    <td>--%>
<%--                        <a href="/admin/institutions/modify?id=${don.id}">Modyfikuj</a> &nbsp;|&nbsp;--%>
<%--                        <a href="/admin/institutions/delete?id=${don.id}">Usuń</a>--%>
<%--                    </td>--%>
                </tr>
            </c:forEach>
        </table></div>
</section>
<br><br>