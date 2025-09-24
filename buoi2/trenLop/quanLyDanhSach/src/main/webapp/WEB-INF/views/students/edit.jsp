<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Sửa sinh viên</title>
</head>
<body>
<h2>Sửa thông tin sinh viên</h2>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<form action="${pageContext.request.contextPath}/students/${student.id}/edit" method="post">
    <p>Mã số: <input type="text" name="id" value="${student.id}" readonly></p>
    <p>Họ tên: <input type="text" name="name" value="${student.name}" required></p>
    <p>Điểm: <input type="number" step="0.1" name="gpa" value="${student.gpa}" required></p>
    <button type="submit">Lưu</button>
</form>

<p><a href="${pageContext.request.contextPath}/students">Quay lại danh sách</a></p>
</body>
</html>
