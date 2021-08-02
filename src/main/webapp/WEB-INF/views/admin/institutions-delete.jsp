<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="login-page">
    <h2>${headderAction} fundację</h2>

    <p>${institution.name}</p>
    <p>${institution.description}</p>

    <form action="/admin/institutions/${CRUDAction}" method="post">
        <input type="hidden" name="id" value="${institution.id}">
        <div class="form-group form-group--buttons">
            <input class="btn" type="submit" value="${headderAction} fundację">
        </div>
    </form>

</section>