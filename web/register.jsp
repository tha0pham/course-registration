<%@page import="entity.User"%>
<!DOCTYPE html>
<%@page import="entity.Major"%>
<%@page import="entity.Clazz"%>
<%@page import="dao.RegistrationDAO"%>
<%@page import="dao.YearDAO"%>
<%@page import="dao.ClazzDAO"%>
<%@page import="entity.Year"%>
<%@page import="entity.Semester"%>
<%@page import="dao.SemesterDAO"%>
<%@page import="java.util.List"%>
<%@page import="dao.SemesterDAO"%>
<%@page import="dao.StudentDAO"%>
<%@page import="entity.Student"%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <title>Student Management System</title>
        <link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/style.css" type="text/css" media="all">
        <script type="text/javascript" src="js/jquery-1.4.2.min.js" ></script>
        <script type="text/javascript" src="js/cufon-yui.js"></script>
        <script type="text/javascript" src="js/cufon-replace.js"></script>
        <script type="text/javascript" src="js/Myriad_Pro_300.font.js"></script>
        <script type="text/javascript" src="js/Myriad_Pro_400.font.js"></script>
        <script type="text/javascript" src="js/script.js"></script>
        <script type="text/javascript" src="js/jquery-2.1.3.js"></script>
        <script type="text/javascript" src="js/jquery.validate.js"></script>
        <script type="text/javascript" src="js/validate.js"></script>

        <!--[if lt IE 7]>
             <link rel="stylesheet" href="css/ie/ie6.css" type="text/css" media="screen">
             <script type="text/javascript" src="js/ie_png.js"></script>
             <script type="text/javascript">
                ie_png.fix('.png, footer, header nav ul li a, .nav-bg, .list li img');
             </script>
        <![endif]-->
        <!--[if lt IE 9]>
                <script type="text/javascript" src="js/html5.js"></script>
          <![endif]-->
    </head>
    <%
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            response.sendRedirect("login.jsp");
        }

        SemesterDAO semesterDAO = new SemesterDAO();
        List<Semester> semesters = semesterDAO.findAllSemesters();
        Semester semester;
        YearDAO yearDAO = new YearDAO();
        List<Year> years = yearDAO.findAllYears();
        Year year;
        try {
            semester = semesterDAO.findSemesterByID(Integer.parseInt(request.getParameter("semester")));
        } catch (NumberFormatException e) {
            semester = semesterDAO.findSemesterByID(1);
        }
        try {
            year = yearDAO.findYearByID(Integer.parseInt(request.getParameter("year")));
        } catch (NumberFormatException e) {
            year = yearDAO.findYearByID(1);
        }

        RegistrationDAO regDAO = new RegistrationDAO();

        String strId = request.getParameter("id");
        if (strId != null) {
            int id = Integer.parseInt(strId);
            Student student = new StudentDAO().findStudentByID(id);
    %>




    <body id="page1">
        <div class="wrap">
            <!-- header -->
            <header>
                <div class="container">
                    <h1><a href="index.jsp">Student's site</a></h1>
                    <nav>
                        <ul>
                            <li class="current"><a href="index.jsp" class="m1">Home 

                                    Page</a></li>
                            <li><a href="about-us.jsp" class="m2">About Us</a></li>
                            <li><a href="articles.jsp" class="m3">Our Articles</a></li>
                            <li><a href="contact-us.jsp" class="m4">Contact Us</a></li>
                            <li class="last"><a href="sitemap.jsp" class="m5">Site 

                                    Map</a></li>
                        </ul>
                    </nav>
                    <form action="" id="search-form">
                        <fieldset>
                            <div class="rowElem">
                                <input type="text" name="keyword"/>
                                <a href="#" onClick="document.getElementById('search-

                                                    form').submit()">Search</a></div>
                        </fieldset>
                    </form>
                </div>
            </header>
            <div class="container">
                <input type="button" name="btnLogin" value="Login" onclick="location.href='login.jsp'"/>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <form action="LogoutServlet">
                    <input id="btnLogout" name="btnLogout" type="submit" value="Logout"/>
                </form>
                <!-- aside -->
                <aside>
                    <h3>Categories</h3>
                    <ul class="categories">
                        <li><span><a href="list_faculty.jsp">Faculties</a></span></li>
                        <li><span><a href="list_major.jsp">Majors</a></span></li>
                        <li><span><a href="list_course.jsp">Courses</a></span></li>
                        <li><span><a href="list_clazz.jsp">Classes</a></span></li>
                        <li><span><a href="list_instructor.jsp">Instructors</a></span></li>
                        <li><span><a href="list_student.jsp">Students and 

                                    Registration</a></span></li>
                        <li><span><a href="manage_grade.jsp">Grade Management</a></span></li>
                        <li><span><a href="report_redirect.jsp">Reports</a></span></li>
                        <li><span><a href="list_user.jsp">Users</a></span></li>
                    </ul>
                    <form action="MailServlet" id="newsletter-form">
                        <fieldset>
                            <div class="rowElem">
                                <h2>Newsletter</h2>
                                <input type="email" value="Enter Your Email Here" onFocus="if
                                                            (this.value == 'Enter Your Email Here'){this.value = ''}" onBlur="if (this.value == '')

                                                                                        {this.value = 'Enter Your Email Here'}" >
                                <div class="clear"><a href="#" class="fright" 

                                                      onClick="document.getElementById('newsletter-form').submit()">Submit</a></div>
                            </div>
                        </fieldset>
                    </form>
                    <h2>Fresh <span>News</span></h2>
                    <ul class="news">
                        <li><strong>June 30, 2010</strong>
                            <h4><a href="#">Sed ut perspiciatis unde</a></h4>
                            Sed ut perspiciatis unde omnis iste natus error sit voluptatem 

                            accusantium doloremque. </li>
                        <li><strong>June 14, 2010</strong>
                            <h4><a href="#">Neque porro quisquam est</a></h4>
                            Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit 

                            aut fugit consequuntur magni. </li>
                        <li><strong>May 29, 2010</strong>
                            <h4><a href="#">Minima veniam, quis nostrum</a></h4>
                            Uis autem vel eum iure reprehenderit qui in ea voluptate velit 


                            esse quam nihil molestiae. </li>
                    </ul>
                </aside>
                <!-- content -->
                <section id="content">
                    <div class="inside">        
                        <!-- modification starts-->


                        <form method="post">
                            <input type="hidden" name="studentId" value="<%=student.getId()%>"/>
                            <br/>
                            <label for="semester">Semester:</label>
                            <select name="semester" onchange="submit()">
                                <%
                                    if (semesters != null && semesters.size() != 0 && years != null && years.size() != 0) {
                                        for (Semester each : semesters) {
                                %>
                                <option value="<%=each.getId()%>"
                                        <%
                                            if (each.getId() == semester.getId()) {
                                                out.print(" selected ");
                                            }
                                        %>
                                        ><%=each.getName()%></option>
                                <%
                                    }
                                %>
                            </select>

                            <br/>
                            <label for="year">Year:</label>
                            
                            <select name="year" onchange="submit()">
                                <%
                                    for (Year each : years) {
                                %>
                                <option value="<%=each.getId()%>"
                                        <%
                                            if (each.getId() == year.getId()) {
                                                out.print(" selected ");
                                            }
                                        %>
                                        ><%=each.getValue()%></option>
                                <%
                                        }
                                    }
                                %>
                            </select>
                            <br/>
                            <table class="myInfoTable">
                                <caption>
                                    <strong>Student Information </strong>
                                </caption>
                                <tr>
                                    <th scope="row">Student ID:</th>
                                    <td><%=student.getCode()%></td>
                                </tr>
                                <tr>
                                    <th scope="row">Student Name:</th>
                                    <td><%=student.getLastName() + " " + student.getFirstName()%></td>
                                </tr>
                                <tr>
                                    <th scope="row">Specialization:</th>
                                    <td><%=student.getMajor().getName()%></td>
                                </tr>
                            </table>
                            <br/>
                            <input type="submit" value="View Registration" name="btnViewRegistration"
                                   formaction="view_registration.jsp?studentId=<%=student.getId()%>&semester=<%=semester.getId()%>&year=<%=year.getId()%>">
                            <table class="myListTable">
                                <caption style="font-size: x-large">
                                    Registered Courses
                                </caption>
                                <tr>
                                    <th scope="col">No.</th>
                                    <th scope="col">Del</th>
                                    <th scope="col">Class Code</th>
                                    <th scope="col">Course Code</th>
                                    <th scope="col">Course Name</th>
                                    <th scope="col">#/ Max</th>
                                    <th scope="col">Semester/Year</th>
                                    <th scope="col">Specialization</th>
                                </tr>
                                <%
                                    int i = 0;
                                    for (Clazz each : regDAO.classesRegistered(student, semester, year)) {
                                        i++;
                                %>
                                <tr>
                                    <td><%=i%></td>
                                    <td><input type="checkbox" name="deleteId" value="<%=each.getId()%>"/></td>
                                    <td><%=each.getName()%></td>
                                    <td><%=each.getCourse().getCode()%></td>
                                    <td><%=each.getCourse().getName()%></td>
                                    <td><%=each.getRegistrationList().size()%>/ <%=each.getCapacity()%></td>
                                    <td><%=each.getSemester().getName()%>/ <%=each.getYear().getValue()%></td>
                                    <td><%
                                        String majorList = "";
                                        for (Major major : each.getCourse().getMajorList()) {
                                            majorList += ", " + major.getName();
                                        }
                                        majorList = majorList.substring(2);
                                        out.print(majorList);
                                        %></td>
                                </tr>
                                <%
                                    }
                                %>
                            </table>
                            <p style="text-align: right">
                                <input name="btnUnregister" type="submit" id="btnUnregister" value="Unregister" formaction="RegisterServlet">
                            </p>
                            <table class="myListTable">
                                <caption style="font-size: x-large">
                                    Offered Courses
                                </caption>
                                <tr>
                                    <th scope="col">No.</th>
                                    <th scope="col">Reg</th>
                                    <th scope="col">Class Code</th>
                                    <th scope="col">Course Code</th>
                                    <th scope="col">Course Name</th>
                                    <th scope="col">#/ Max</th>
                                    <th scope="col">Specialization</th>
                                </tr>
                                <%
                                    i = 0;
                                    for (Clazz each : regDAO.classesOffered(student, semester, year)) {
                                        if (student.getMajor().getCourseList().contains(each.getCourse())) {
                                            i++;
                                %>

                                <tr>
                                    <td><%=i%></td>
                                    <td><input type="checkbox" name="registerId" value="<%=each.getId()%>"/></td>
                                    <td><%=each.getName()%></td>
                                    <td><%=each.getCourse().getCode()%></td>
                                    <td><%=each.getCourse().getName()%></td>
                                    <td><%=each.getRegistrationList().size()%>/ <%=each.getCapacity()%></td>
                                    <td><%
                                        String majorList = "";
                                        for (Major major : each.getCourse().getMajorList()) {
                                            majorList += ", " + major.getName();
                                        }
                                        majorList = majorList.substring(2);
                                        out.print(majorList);
                                        %></td>
                                </tr>
                                <%
                                            }
                                        }
                                    } else {
                                        response.sendRedirect("list_student.jsp");
                                    }
                                %>
                            </table>
                            <h2 style="text-align: right">
                                <input name="btnRegister" type="submit" id="btnRegister" value="Register" formaction="RegisterServlet">
                            </h2>
                        </form>
                        <p>${message}</p>



                        <!-- modification ends-->
                    </div>
                </section>
            </div>
        </div>
        <!-- footer -->
        <footer>
            <div class="container">
                <div class="inside">
                    <div class="wrapper">
                        <div class="fleft">24/7 Customer Service 

                            <span>8.800.146.56.7</span></div>
                        <div class="aligncenter"><a rel="nofollow" href="#" 

                                                    class="new_window">Website template</a> designed by TemplateMonster.com<br>
                            <a href="#" class="new_window">3D Models</a> provided by 

                            Templates.com</div>
                    </div>
                </div>
            </div>
        </footer>
        <script type="text/javascript"> Cufon.now();</script>
    </body>
</html>
