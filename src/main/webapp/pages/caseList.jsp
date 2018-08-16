<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/caseList.css">
    <title>to do list</title>
</head>
<body>
	<h1>to do list</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Description</th>
            <th>Action</th>
            <th>Button</th>
        </tr>
        <c:forEach items="${caseList}" var="aCase">
        <tr>
            <td>${aCase.id}</td>
            <td>${aCase.description}</td>
            <td><a href="/delete/${aCase.id}">Delete this item</a></td>
            <td>
                <form action="/delete" method="Delete">
                    <button type="button">delete</button>
                </form>
            </td>
        </tr>
        </c:forEach>
    </table>
    <br><a href="/add-new-case">Add new case</a>
</body>
</html>
