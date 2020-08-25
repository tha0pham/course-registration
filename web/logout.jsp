<!DOCTYPE html>



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




    <body id="page1">
        <div class="wrap">
            <!-- header -->
            <header>
                <div class="container">
                    <h1><a href="index.jsp">Student's site</a></h1>
                    <nav>
                        <ul>
                            <li><a href="index.jsp" class="m1">Home 

                                    Page</a></li>
                            <li class="current"><a href="about-us.jsp" class="m2">About Us</a></li>
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
                                <a href="#" onClick="document.getElementById('search-form').submit()">Search</a></div>
                        </fieldset>
                    </form>
                </div>
            </header>
            <div class="container">
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
                    <div id="banner">
                        <h2>Professional <span>Online Education <span>Since 1992</span></span></h2>
                    </div><div class="ic">More Website Templates at TemplateMonster.com!</div>
                    <div class="inside">
                        <p>You have been logged out.</p>
                        <p><a href="index.jsp">Back to Home Page</a></p>
                    </div>
                </section>
            </div>
        </div>
        <!-- footer -->
        <footer>
            <div class="container">
                <div class="inside">
                    <div class="wrapper">
                        <div class="fleft">24/7 Customer Service <span>8.800.146.56.7</span></div>
                        <div class="aligncenter"><a rel="nofollow" href="http://www.templatemonster.com" class="new_window">Website template</a> designed by TemplateMonster.com<br>
                            <a href="http://www.templates.com/product/3d-models/" class="new_window">3D Models</a> provided by Templates.com</div>
                    </div>
                </div>
            </div>
        </footer>
        <script type="text/javascript"> Cufon.now();</script>
    </body>
</html>
