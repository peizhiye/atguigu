<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" import="java.util.Date" %>
<%@ page session="false" isELIgnored="false" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

	<script type="application/javascript">
		function clickHref() {
			var urlStr = "hello.jsp?ttt=" + encodeURI(encodeURI("我a <>#%{}|\\^~[]`;/?:@=&$-_.+!*'(),\""));
			var urlStr2 = "hello.jsp?ttt=" + encodeURIComponent(encodeURIComponent("我a <>#%{}|\\^~[]`;/?:@=&$-_.+!*'(),\""));
			var urlStr3 = encodeURIComponent(encodeURIComponent("hello.jsp?ttt=我a@:.-*_ +"));
			// var urlStr = "hello.jsp?ttt=" + encodeURI("我");
			// var urlStr = "hello.jsp?ttt=" + "我";
			document.getElementsByTagName("a")[0].href = urlStr2;

		}

	</script>
</head>
<body>
	
	<% 
		Date date = new Date();
		DateFormat dateFormat = null;
		
		HttpServletRequest req = null;
		
		//int i = 10 / 0;
	%>
	 <%= application.getContextPath() %>

	<a href="hello.jsp" onclick="clickHref()">测试</a>
	<br/>
	<a href="hello.jsp?ttt=%E6%88%91">测试GET</a>
	<br/>
	<a href="hello.jsp?ttt= :/?[]@!$'()*+,;-_.*~%<>&quot;&=">测试GET2</a>
	<br/>
	<a href="hello.jsp?ttt=我">测试GET3</a>

	<form action="hello.jsp" method="post">
		
		username: <input type="text" name="username" value="我a <>#%{}|\^~[]`;/?:@=&$-_.+!*'(),&quot;"/>
		text: <textarea name="lala">我a&#32;&ensp;&#160;<>#%{}|\^~[]`;/?:@=&$-_.+!*'(),"</textarea>
		<input type="submit" value="Submit"/>
	
	</form>
	
		
</body>
</html>