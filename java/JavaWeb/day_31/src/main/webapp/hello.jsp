<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h4>Hello Page</h4>

	学习 Java!

	<br><br>

	<%
		request.setCharacterEncoding("UTF-8");
		String bb = URLEncoder.encode("我@&?/:.-*_ +");
		String bbb = URLEncoder.encode(bb);
		System.out.println(bbb);
		String cc = URLEncoder.encode("我@?/&:.-*_ +", "UTF-8");
		String ccc = URLEncoder.encode(cc, "UTF-8");
		System.out.println(ccc);

		String ttt = request.getParameter("ttt");
//		ttt = URLDecoder.decode(ttt,"UTF-8");
	%>

	ttt: <%= ttt %>

	username: <%= request.getParameter("username") %>

	<br><br>


<%--	<%--%>
<%--		String val = request.getParameter("username");--%>
<%--		String username = new String(val.getBytes("ISO-8859-1"), "UTF-8");--%>
<%--		out.print(username);--%>
<%--	%>--%>

</body>
</html>