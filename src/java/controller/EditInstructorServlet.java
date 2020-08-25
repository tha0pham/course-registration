package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import dao.FacultyDAO;
import dao.InstructorDAO;
import entity.Faculty;
import entity.Instructor;
import java.io.File;
import java.util.Calendar;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "EditInstructorServlet", urlPatterns = {"/EditInstructorServlet"})
public class EditInstructorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String message = "";
        try {
            String imageFolder = request.getRealPath("/images");
            MultipartRequest m = new MultipartRequest(request, imageFolder);

            String updateID = m.getParameter("updateID");
            String code = m.getParameter("code");
//            String pid = m.getParameter("pid");
            String lname = m.getParameter("lname");
            String fname = m.getParameter("fname");

            int byear = Integer.parseInt(m.getParameter("byear"));
            int bmonth = Integer.parseInt(m.getParameter("bmonth"));
            int bdate = Integer.parseInt(m.getParameter("bdate"));
            Calendar calendar = Calendar.getInstance();
            calendar.set(byear, bmonth, bdate);

//            String pob = m.getParameter("pob");

            boolean gender = Integer.parseInt(m.getParameter("gender")) == 0;

            int facultyID = Integer.parseInt(m.getParameter("facultyID"));
            FacultyDAO facultyDAO = new FacultyDAO();
            Faculty faculty = facultyDAO.findFacultyByID(facultyID);

            String phone = m.getParameter("phone");
            String email = m.getParameter("email");
            String address = m.getParameter("address");

            String imagePath = null;

            try {
                File image = m.getFile("image");
                File renamedImage = new File(image.getParent() + File.separator + "Ins-" + code + ".jpg");
                image.renameTo(renamedImage);
                image.delete();
                imagePath = "images/" + renamedImage.getName();
            } catch (NullPointerException e) {
            }

            InstructorDAO instructorDAO = new InstructorDAO();
            Instructor instructor;

            if (updateID == null || updateID.isEmpty()) {
                if (instructorDAO.findInstructorByCode(code) == null ) {
                    instructor = new Instructor();
                    instructor.setCode(code);
                    instructor.setLastName(lname);
                    instructor.setFirstName(fname);
                    instructor.setDob(calendar.getTime());
                    instructor.setGender(gender);
                    instructor.setFaculty(faculty);
                    instructor.setPhone(phone);
                    instructor.setEmail(email);
                    instructor.setAddress(address);
                    instructor.setImage(imagePath);

                    instructorDAO.addInstructor(instructor);
                    message = lname + " " + fname + " has been added.";
                } else {
                    message = "Error adding: Instructor code or PID already existed.";
                }
            } else {
                int id = Integer.parseInt(updateID);
                instructor = instructorDAO.findInstructorByID(id);

                if (!instructor.getCode().equals(code) && instructorDAO.findInstructorByCode(code) != null) {
                    throw new Exception("Duplicated Instructor Code!");
                }
                    instructor.setCode(code);
                    instructor.setLastName(lname);
                    instructor.setFirstName(fname);
                    instructor.setDob(calendar.getTime());
                    instructor.setGender(gender);
                    instructor.setFaculty(faculty);
                    instructor.setPhone(phone);
                    instructor.setEmail(email);
                    instructor.setAddress(address);
                    instructor.setImage(imagePath);

                instructorDAO.updateInstructor(instructor);
                message = lname + " " + fname + " has been updated.";
            }
        } catch (Exception e) {
            message = e.getMessage();
        } finally {
            request.setAttribute("message", message);
            request.getRequestDispatcher("edit_instructor.jsp").forward(request, response);
        }
    }
}
