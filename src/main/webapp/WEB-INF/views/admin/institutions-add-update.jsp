<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="login-page">
    <h2>${headderAction} fundację</h2>
    <form action="/admin/institutions/${CRUDAction}" method="post">
        <div class="form-group">
            <input type="text" name="name" placeholder="${institution.name}" />
        </div>
        <div class="form-group">
            <input type="text" name="description" placeholder="${institution.description}" />
        </div>
        <input type="hidden" name="id" value="${institution.id}">
        <div class="form-group form-group--buttons">
            <input class="btn" type="submit" value="${headderAction} fundację">
        </div>
    </form>
</section>