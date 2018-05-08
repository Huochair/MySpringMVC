<%@ page import="com.hvcc.vo.SongInfor"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML >
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'MyJsp.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


</head>
<%
	List<SongInfor> sInfors = (List<SongInfor>) session.getAttribute("sInfor1");
	String src = (String)session.getAttribute("src");
%>
<body>
	<form id="form1" name="form1" method="post" action="WYYServlet">
		<p>
			<label for="IWannaSay"></label> 你的歌单 <input type="text"
				name="IWannaSay" id="IWannaSay" />
		</p>
		<p align="center"> <%=src%></p>
		<div align="center">
			<table width="608" border="1">
				<tr>
					<th width="203" scope="col">歌名</th>
					<th width="389" scope="col">网址</th>
				</tr>
				<%for (SongInfor deName : sInfors) {%>
				<tr>
					<td>&nbsp;<%=deName.getSongName()%></td>
					<td> &nbsp;<%=deName.getUrl()%>
                    	<a href=<%=deName.getUrl()%>>
                       		去看看
                        </a>
                    </td>
				</tr>
				<%}%>
			</table>
		</div>
		<p>&nbsp;</p>
	</form>
</body>
</html>
