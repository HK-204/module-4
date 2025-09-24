<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Chi tiết sinh viên</title>
</head>
<body>
<h2>Thông tin sinh viên</h2>

<p><b>Mã số:</b> ${student.id}</p>
<p><b>Họ tên:</b> ${student.name}</p>
<p><b>Điểm:</b> ${student.gpa}</p>
<p><b>Hạng:</b> ${student.rank}</p>

<p>
    <a href="${pageContext.request.contextPath}/students/${student.id}/edit">Sửa</a> |
    <form action="${pageContext.request.contextPath}/students/${student.id}/delete"
          method="post" style="display:inline">
        <button type="submit">Xóa</button>
    </form>
</p>

<p><a href="${pageContext.request.contextPath}/students">Quay lại danh sách</a></p>
</body>
</html>
