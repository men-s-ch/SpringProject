<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        table{
            border-collapse: collapse;
        }
        th,td{
            padding: 5px;
        }
    </style>
</head>
<body>
<form action="/upload" enctype="multipart/form-data" method="post"> <%--업로드는 post방식--%>
    <table border="1">
        <tr>
            <th>상품명</th>
            <td>
                <input type="text" name="imageName" size="35">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <textarea name="imageContent" cols="50" rows="10"></textarea>
            </td>
        </tr>

        <%--
        <tr>
            <td colspan="2">
                <input type="file" name="img">
            </td>
        </tr>
        <!-- 다중 업로드할 때는 name 속성의 이름이 같아야 한다. => 서버에서 배열로 받는다-->
        <tr>
            <td colspan="2">
                <input type="file" name="img">
            </td>
        </tr>
        --%>
        <!-- 한 번데 1개 또는 여러개(드래그)를 선택 => 서버에서 List로 받는다.-->
        <tr>
            <td colspan="2">
                <input type="file" name="img[]" multiple="multiple">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="이미지 업로드">
                <input type="reset" value="취소">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
