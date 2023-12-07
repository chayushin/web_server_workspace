<%@ page language="java" contentType="text/html; charset=UTF-8"
          pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Menu</title>
</head>
<body>
<h1>Menu</h1>
<%--  client요청(절대주소)contex=path(/menu)부터 시작해야 한다.  --%>
<%--  context-path 이후 src/main/webapp 웹루트 하위에서 찾는다.  --%>
<a href="/menu/menu.jsp">메뉴</a>
</body>
</html>