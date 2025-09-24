<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách sinh viên</title>
</head>
<body>
<h2>Danh sách sinh viên</h2>
<c:if test="${not empty message}">
    <p style="color:green">${message}</p>
</c:if>

<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th><a href="?q=${q}&sort=id&dir=${dir eq 'asc' ? 'desc' : 'asc'}">Mã số</a></th>
        <th><a href="?q=${q}&sort=name&dir=${dir eq 'asc' ? 'desc' : 'asc'}">Họ tên</a></th>
        <th><a href="?q=${q}&sort=gpa&dir=${dir eq 'asc' ? 'desc' : 'asc'}">Điểm</a></th>
        <th>Hạng</th>
        <th>Thao tác</th>
    </tr>
    <c:forEach var="s" items="${students}">
        <tr>
            <td>${s.id}</td>
            <td>${s.name}</td>
            <td>${s.gpa}</td>
            <td>${s.rank}</td>
            <td>
                <a href="${pageContext.request.contextPath}/students/${s.id}">Chi tiết</a> |
                <a href="${pageContext.request.contextPath}/students/${s.id}/edit">Sửa</a> |
                <form action="${pageContext.request.contextPath}/students/${s.id}/delete"
                      method="post" style="display:inline">
                    <button type="submit">Xóa</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<div>
    <c:forEach begin="1" end="${totalPages}" var="i">
        <c:choose>
            <c:when test="${i == page}">
                <b>[${i}]</b>
            </c:when>
            <c:otherwise>
                <a href="?q=${q}&sort=${sort}&dir=${dir}&page=${i}&size=${size}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>

<p><a href="${pageContext.request.contextPath}/students/add">Thêm sinh viên</a></p>
</body>
</html>
