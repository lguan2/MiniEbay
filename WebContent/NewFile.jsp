<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" import="test.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Title</title>
</head>
<body>
	<form action="NewFile.jsp">
		First name: <input type="text" name="UserName"><br>
		Last name: <input type="text" name="Password"><br> <input
			type="submit" value="Submit">
	</form>

	<%
		test.Controller a = new test.Controller();
		List<test.Item> list = a.getAllItems();
		test.User user = null;
		if (request.getParameter("UserName") != null) {
			user = a.gerUser(request.getParameter("UserName"),
					request.getParameter("Password"));
		}
	%>
	<table>
		<%
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=list.get(i).getName()%></td>
			<td><%=list.get(i).getPrice()%></td>
		</tr>
		<%
			}
		%>
	</table>
	
</body>
</html>