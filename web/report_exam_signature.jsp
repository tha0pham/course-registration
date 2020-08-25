<%@page import="entity.User"%>
<%@page import="entity.Registration"%>
<%@page import="entity.Student"%>
<%@page import="java.util.List"%>
<%@page import="dao.RegistrationDAO"%>
<%@page import="dao.ClazzDAO"%>
<%@page import="entity.Clazz"%>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Untitled Document</title>
         <link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/style.css" type="text/css" media="all">
    </head>
    <%
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            response.sendRedirect("login.jsp");
        }

        int clazzId = 0;
        try {
            clazzId = Integer.parseInt(request.getParameter("clazz"));
        } catch (NumberFormatException e) {
            response.sendRedirect("list_clazz.jsp");
        }
        Clazz clazz = new ClazzDAO().findClazzByID(clazzId);
        RegistrationDAO regDAO = new RegistrationDAO();
        List<Student> students = regDAO.getStudentList(clazz);
        int i = 0;
    %>
    <body>
        <a href="report_redirect.jsp">Back</a>
        <table class="myInfoTable">
            <caption>GRADE SHEET</caption>
            <tr>
                <td>Exam: .........................................................</td>
                <td>Instructor: <%=clazz.getInsId().getLastName() + " " + clazz.getInsId().getFirstName() %> </td>
            </tr>
            <tr>
                <td>Course: <%=clazz.getCourse().getName()%></td>
                <td>Signature: ......................................</td>
            </tr>
            <tr>
                <td>Number of Students: ......................................</td>
                <td>Proctor (name and signature): </td>
            </tr>
            <tr>
                <td>Percentage Ratio of Final Course Grade: ...................</td>
                <td>.........................................................</td>
            </tr>
            <tr>
                <td>Class: <%=clazz.getName()%></td>
                <td>.........................................................</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>Semester/Year: <%=clazz.getSemester().getName()%> / <%=clazz.getYear().getValue()%></td>
            </tr>
        </table>
        <br/><br/>
        <table class="myListTable">
            <tr>
                <th scope="col">No,</th>
                <th scope="col">Student ID</th>
                <th scope="col">Last Name</th>
                <th scope="col">First Name</th>
                <th scope="col">Scores</th>
                <th scope="col">Candidate<br/>Signature</th>
                <th scope="col">Grade Appeal<br/>Signature</th>
                <th scope="col">Notes</th>
            </tr>
            <%
                for (Student each : students) {
                    i++;
                    Registration reg = regDAO.findRegistration(each.getId(), clazz.getId());
            %>
            <tr>
                <td><%=i%></td>
                <td><%=each.getCode()%></td>
                <td><%=each.getLastName()%></td>
                <td><%=each.getFirstName()%></td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <%
                }
            %>
        </table>
    </body>
</html>
