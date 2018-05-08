
<%@ page import="com.hvcc.vo.SongInfor"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'index.jsp' starting page</title>
</head>
<body>
	<form id="form1" name="form1" method="post" action="WYYServlet">
		<p align="center">
			<label for="IWannaSay"></label> 你的歌单 <input type="text"
				name="IWannaSay" id="IWannaSay" />
		</p>
	</form>
</body>
</html>
