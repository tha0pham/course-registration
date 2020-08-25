<%@page import="entity.User"%>
<!DOCTYPE html>

<%@page import="java.util.Calendar"%>
<%@page import="dao.InstructorDAO"%>
<%@page import="entity.Instructor"%>
<%@page import="dao.FacultyDAO"%>
<%@page import="java.util.List"%>
<%@page import="entity.Faculty"%>

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

        InstructorDAO instructorDAO = new InstructorDAO();
        Instructor instructor = null;
        Calendar calendar = Calendar.getInstance();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            instructor = instructorDAO.findInstructorByID(id);
            calendar.setTime(instructor.getDob());
        } catch (Exception e) {
        }

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



                        <h1> Instructor Information</h1>
                        <form action="EditInstructorServlet" method="post" enctype="multipart/form-data" id="instructor" class="fmpeople">
                            <input type="hidden" name="updateID" value="<%=instructor == null ? "" : instructor.getId()%>"/>
                            <table class="myEditTable">
                                <tr><th>Instructor ID:</th>
                                    <td><input type="text" width="100%" name="code" value="<%=instructor == null ? "" : instructor.getCode()%>"/></td>
                                    <td rowspan="5">
                                        <img width ="120px" height="150px" 
                                             src="<%=instructor == null ? "myImage/person_default.jpg" : instructor.getImage() == null ? "myImage/person_default.jpg" : instructor.getImage()%>">
                                    </td>
                                </tr>
                                <!--                    <tr><th>Personal ID:</th>
                                                        <td><input type="text" width="100%"  name="pid" value=""/></td>
                                                    </tr>-->
                                <tr><th>Last Name:</th>
                                    <td><input type="text" width="100%"  name="lname" value="<%=instructor == null ? "" : instructor.getLastName()%>"/></td>
                                </tr>
                                <tr><th>First Name:</th>
                                    <td><input type="text" width="100%"  name="fname" value="<%=instructor == null ? "" : instructor.getFirstName()%>"/></td>
                                </tr>
                                <tr>
                                    <th>Date of Birth:</th>
                                    <td>
                                        <input type="text" width="5%"  name="bdate" placeholder="DD" value="<%=instructor == null ? "" : calendar.get(Calendar.DATE)%>"/>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        <select name="bmonth">
                                            <%  String[] months = {"January", "February", "March", "April", "May",
                                                    "June", "July", "August", "September", "October", "November", "December"};
                                                for (int i = 0; i < months.length; i++) {
                                            %>
                                            <option value="<%=i%>" <%=instructor == null ? "" : calendar.get(Calendar.MONTH) != i ? "" : "selected"%>><%=months[i]%></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        <input type="text" width="10%"  name="byear" placeholder="YYYY" value="<%=instructor == null ? "" : calendar.get(Calendar.YEAR)%>"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Gender: </th>
                                    <td>
                                        <input type="radio" name="gender" value="0" <%=instructor == null ? "" : instructor.getGender() ? "checked" : ""%>/>Female
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        <input type="radio" name="gender" value="1" <%=instructor == null ? "" : instructor.getGender() ? "" : "checked"%>/>Male
                                    </td>
                                </tr>
                                <tr>
                                    <th>Faculty: </th>
                                    <td>
                                        <select name="facultyID">
                                            <%
                                                FacultyDAO facultyDAO = new FacultyDAO();
                                                List<Faculty> faculties = facultyDAO.findAllFaculties();
                                                if (faculties != null) {
                                                    for (Faculty each : faculties) {
                                            %>
                                            <option value="<%=each.getId()%>" 
                                                    <%=instructor == null ? "" : instructor.getId() == each.getId() ? "selected" : ""%>>
                                                <%=each.getName()%></option>
                                                <%
                                                        }
                                                    }
                                                %>
                                        </select>
                                    </td>
                                    <td>&nbsp;</td>
                                </tr>
                                <tr><th>Phone:</th><td><input type="text" width="100%"  name="phone" value="<%=instructor == null ? "" : instructor.getPhone()%>"/></td><td>&nbsp;</td></tr>
                                <tr><th>Email:</th><td><input type="text" width="100%"  name="email" value="<%=instructor == null ? "" : instructor.getEmail()%>"/></td><td>&nbsp;</td></tr>
                                <tr><th>Address:</th><td><input type="text" width="100%"  name="address" value="<%=instructor == null ? "" : instructor.getAddress()%>"/></td><td>&nbsp;</td></tr>
                                <tr><th>Image:</th><td><input type="file" name="image"/></td><td>&nbsp;</td></tr>
                                <tr><td>&nbsp;</td>
                                    <td><input type="submit" value="Save Instructor" name="btnSave">
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        <input type="reset" value="Cancel" name="btnCancel"></td>
                                    <td>&nbsp;</td>
                                </tr>
                            </table>
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
