<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>*** 메인화면 ***</h2>
<h3>
	<p><a href="/writeForm">입력</a></p>
	<p><a href="/list">출력</a></p> <!-- pg=1 일때는 생략가능 -->
	<br>
	<p><a href="/uploadForm">파일 업로드</a></p>
	<p><a href="/uploadAJaxForm">파일 업로드 AJax</a></p>
	<br>
	<p><a href="/uploadList">이미지 출력</a></p>

</h3>
</body>
</html>

<!--
Spring Framework + Maven + MySQL + MyBatis(@Mapper 사용) + JSP(jQuery) + NCP

Project : SpringProject
	src/main/java
		spring.conf
			SpringConfiguration.java
			NaverConfiguration.java

		com.controller.SpringProject
			MainController.java

		user.controller
			UserController.java
			UserUploadController.java

		user.service
			UserService.java (Interface)
			ObjectStorageService.java (Interface)
		user.service.impl
			UserServiceImpl.java
			NCPObjectStorageService.java

		user.dao
			UserDAO.java (Interface)
		user.dao.impl
			UserMybatisDAO.java =====> 제거
		user.bean
			UserDTO.java
			UserPaging.java

	src/main/resources
		mapper

		spring
			mybatis-config.xml =====> 제거
			db.properties
			naver.properties

	src/main/webapp
		WEB-INF
			css
				write.css
			js
				write.js
			image
				*.jpg

			storage (가상주소, 업로드)

			user

			upload
				uploadForm.jsp

			spring
				appServlet
					servlet-context.xml (웹과 관련된 WAC)
				root-context.xml (웹과 독립된 WAC)
 -->


