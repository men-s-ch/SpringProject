
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <tr>
        <td rowspan="3">
            <img src="https://kr.object.ncloudstorage.com/bitcamp-9th-bucket-90/storage/${userUploadDTO.imageFileName }"
            				    	 alt="userUploadDTO.imageName" width="200" height="200" />
        </td>
        <td width="250" style="text-indent: 10pt;">번호 : ${userUploadDTO.seq }</td>
    </tr>
    <tr>
        <td style="text-indent: 10pt;">상품명 : ${userUploadDTO.imageName }</td>
    </tr>
    <tr>
        <td style="text-indent: 10pt;">파일명 : ${userUploadDTO.imageOriginalFileName }</td>
    </tr>
    <tr>
        <td colspan="2" height="100">
            <pre>${userUploadDTO.imageContent }</pre>
        </td>
    </tr>
</table>
<div style="margin-top: 5px;">
    <input type="button" value="목록" onclick="location.href='/uploadList'" />
    <input type="button" value="수정" onclick="location.href='/uploadUpdateForm?seq=${userUploadDTO.seq}'" />
</div>
</body>
</html>
