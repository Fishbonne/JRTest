<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>

    <style>
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
<a href="../../index.jsp">Back to main menu</a>
<c:if test="${empty user.name}">


<h1>Users List</h1>

<c:if test="${!empty listUsers}">
<table class="tg">
    <tr>
        <th width="80">ID</th>
        <th width="120">Name</th>
        <th width="120">Age</th>
        <th width="120">IsAdmin</th>
        <th width="120">CreateDate</th>
        <th width="60">Edit</th>
        <th width="60">Delete</th>
    </tr>
    <c:forEach items="${listUsers}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.age}</td>
            <td>${user.admin}</td>
            <td>${user.createdDate}</td>
            <td><a href="<c:url value='/edit/${user.id}'/>">Edit</a></td>
            <c:if test="${empty searchString}">
                <td><a href="<c:url value='/remove/${user.id}'/>">Delete</a></td>
            </c:if>

            <c:if test="${!empty searchString}">
                <td><a href="<c:url value='/remove/${user.id}/${searchString}'/>">Delete</a></td>
            </c:if>

        </tr>
    </c:forEach>
</table>
    <c:forEach begin="${1}" end="${count}" step="${1}" var="tmp">
        <a href="<c:url value='/users/${tmp}'/>">page${tmp}</a>
    </c:forEach>
</c:if>

<h1>Add User</h1>
</c:if>
    <form:form method="POST" commandName="user" action="/users/add">
         <table>
    <c:if test="${!empty user.name}">
        <tr>
            <td>
                <form:label path="id">
                    <spring:message text="ID"/>
                </form:label>
            </td>
            <td>
                <form:input path="id" readonly="true" size="8" disabled="true"/>
                <form:hidden path="id"/>
            </td>
        </tr>
    </c:if>
    <tr>
        <td>
            <form:label path="name">
                <spring:message text="Name"/>
            </form:label>
        </td>
        <td>
            <form:input path="name"/>
        </td>
    </tr>
    <tr>
        <td>
            <form:label path="age">
                <spring:message text="Age"/>
            </form:label>
        </td>
        <td>
            <form:input path="age"/>
        </td>
    </tr>
    <tr>
        <td>
            <form:label path="admin">
                <spring:message text="IsAdmin"/>
            </form:label>
        </td>
        <td>
            <form:checkbox path="admin"/>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <c:if test="${!empty user.name}">
                <input type="submit"
                       value="<spring:message text="Edit User"/>"/>
            </c:if>
            <c:if test="${empty user.name}">
                <input type="submit"
                       value="<spring:message text="Add User"/>"/>
            </c:if>
        </td>
    </tr>
        </table>
    </form:form>

<c:if test="${empty user.name}">
<form name="user" action="<c:url value="/userdata/search"/>">
    <input type="text" name="search" value="" size="25">
    <input type="submit" value="Search">
</form>
</c:if>

</body>
</html>
