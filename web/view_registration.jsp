<%-- 
    Document   : view_registration
    Created on : Jan 8, 2015, 1:59:27 AM
    Author     : Thao
--%>

<%@page import="entity.User"%>
<%@page import="entity.Clazz"%>
<%@page import="dao.YearDAO"%>
<%@page import="entity.Year"%>
<%@page import="dao.SemesterDAO"%>
<%@page import="entity.Semester"%>
<%@page import="entity.Student"%>
<%@page import="dao.StudentDAO"%>
<%@page import="dao.RegistrationDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/style.css" type="text/css" media="all">
        <title>Registration Summary</title>
    </head>
    <%
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            response.sendRedirect("login.jsp");
        }

        Student student = null;
        Semester semester = null;
        Year year = null;
        try {
            student = new StudentDAO().findStudentByID(Integer.parseInt(request.getParameter("studentId")));
            semester = new SemesterDAO().findSemesterByID(Integer.parseInt(request.getParameter("semester")));
            year = new YearDAO().findYearByID(Integer.parseInt(request.getParameter("year")));
        } catch (NumberFormatException e) {
            response.sendRedirect("list_student.jsp");
        }
        RegistrationDAO regDAO = new RegistrationDAO();
    %>

    <body>
        <p><a href="report_redirect.jsp">Back</a></p>
        <br/>
        <table class="myInfoTable">
            <tr>
                <td><b>Student ID:</b> <%=student.getCode()%></td>
                <td><b>Student Name:</b> <%=student.getLastName() + " " + student.getFirstName()%></td>
            </tr>
            <tr>
                <td></td>
                <td><b>Semester / Year:</b> <%=semester.getName()%> / <%=year.getValue()%></td>
            </tr>
        </table>
        <br/>
        <table class="myListTable">
            <tr>
                <th>No.</th>
                <th>Course Code</th>
                <th>Course Name</th>
                <th>Class Name</th>
                <th>Instructor</th>
                <th>Number of Hours</th>
                <th>Credits</th>
            </tr>
            <%
                int i = 0;
                for (Clazz each : regDAO.classesRegistered(student, semester, year)) {
                    i++;
            %>
            <tr>
                <td><%=i%></td>
                <td><%=each.getCourse().getCode()%></td>
                <td><%=each.getCourse().getName()%></td>
                <td><%=each.getName()%></td>
                <td><%=each.getInsId().getLastName() + " " + each.getInsId().getFirstName() %></td>
                <td><%=each.getCourse().getHour()%></td>
                <td><%=each.getCourse().getCredit()%></td>
            </tr>
            <%
                }
            %>
        </table>
    </body>
</html>
