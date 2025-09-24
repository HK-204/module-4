<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Thêm sinh viên</title>
</head>
<body>
<h2>Thêm sinh viên mới</h2>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<form action="${pageContext.request.contextPath}/students/add" method="post">
    <p>Mã số: <input type="text" name="id" value="${student.id}" required></p>
    <p>Họ tên: <input type="text" name="name" value="${student.name}" required></p>
    <p>Điểm: <input type="number" step="0.1" name="gpa" value="${student.gpa}" required></p>
    <button type="submit">Thêm</button>
</form>

<p><a href="${pageContext.request.contextPath}/students">Quay lại danh sách</a></p>
</body>
</html>
