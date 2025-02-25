<%@page import="com.training.model.Student"%>
<%@page import="java.util.List"%>
<%@page import="com.training.dao.impl.StudentDaoImpl"%>
<%@page import="com.training.dao.StudentDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
    <jsp:include page="components/headlinks.jsp"></jsp:include>
</head>
<body>

	<%
		StudentDao dao=new StudentDaoImpl();
		List<Student> students=dao.getAllStudents();
		String[] columns=dao.getAllColumns();
	
	%>

	  <jsp:include page="components/navbar.jsp"></jsp:include>
		<div class="container">
		<table class="table table-striped">
			<thead class="table-dark">
				<tr>
				<%
					for(String col:columns)
					{				
				%>
					<th><%=col%></th>
				<%}%>
				</tr>
			</thead>
			<tbody>
				<%
					for(Student stu:students)
					{
				%>
					<tr>
						<td><%=stu.getId() %></td>
						<td><%=stu.getName() %></td>
						<td><%=stu.getMobileNo() %></td>
						<td><%=stu.getGender() %></td>
						<td><%=stu.getGradPerc() %></td>
						<td><%=stu.getFee() %></td>
						<td><%=stu.isMember() %></td>
						<td><%=stu.getDob() %></td>
						<td><%=stu.getJoinedAt() %></td>
					</tr>
				<%}%>
			</tbody>
			<tfoot class="table-dark">
			
			</tfoot>
		</table>
		
		</div>	
	   <jsp:include page="components/footerlink.jsp"></jsp:include>
</body>
</html>