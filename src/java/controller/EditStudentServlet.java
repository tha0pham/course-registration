package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import dao.MajorDAO;
import dao.StudentDAO;
import entity.Major;
import entity.Student;
import java.io.File;
import java.util.Calendar;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "EditStudentServlet", urlPatterns = {"/EditStudentServlet"})
public class EditStudentServlet extends HttpServlet {

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
            String imageFolder = request.getRealPath("/myImage");
            MultipartRequest m = new MultipartRequest(request, imageFolder);

            String updateID = m.getParameter("updateID");
            String code = m.getParameter("code");
            String pid = m.getParameter("pid");
            String lname = m.getParameter("lname");
            String fname = m.getParameter("fname");

            int byear = Integer.parseInt(m.getParameter("byear"));
            int bmonth = Integer.parseInt(m.getParameter("bmonth"));
            int bdate = Integer.parseInt(m.getParameter("bdate"));
            Calendar calendar = Calendar.getInstance();
            calendar.set(byear, bmonth, bdate);

            String pob = m.getParameter("pob");

            boolean gender = Integer.parseInt(m.getParameter("gender")) == 0;

            int majorID = Integer.parseInt(m.getParameter("majorID"));
            MajorDAO majorDAO = new MajorDAO();
            Major major = majorDAO.findMajorByID(majorID);

            String phone = m.getParameter("phone");
            String email = m.getParameter("email");
            String address = m.getParameter("address");

            String imagePath = null;

            try {
                File image = m.getFile("image");
                File renamedImage = new File(image.getParent() + File.separator + code + "-Image" + ".jpg");
                image.renameTo(renamedImage);
                image.delete();
                imagePath = "myImage/" + renamedImage.getName();
            } catch (NullPointerException e) {
            }

            StudentDAO studentDAO = new StudentDAO();
            Student student;

            if (updateID == null || updateID.isEmpty()) {
                if (studentDAO.findStudentByCode(code) == null
                        && studentDAO.findStudentByPID(pid) == null) {
                    student = new Student();
                    student.setCode(code);
                    student.setPid(pid);
                    student.setLastName(lname);
                    student.setFirstName(fname);
                    student.setDob(calendar.getTime());
                    student.setPob(pob);
                    student.setGender(gender);
                    student.setMajor(major);
                    student.setPhone(phone);
                    student.setEmail(email);
                    student.setAddress(address);
                    student.setImage(imagePath);

                    studentDAO.addStudent(student);
                    message = lname + " " + fname + " has been added.";
                } else {
                    message = "Error adding: Student code or PID already existed.";
                }
            } else {
                int id = Integer.parseInt(updateID);
                student = studentDAO.findStudentByID(id);

                if (!student.getCode().equals(code) && studentDAO.findStudentByCode(code) != null) {
                    throw new Exception("Duplicated Student Code!");
                }
                if (!student.getPid().equals(pid) && studentDAO.findStudentByPID(pid) != null) {
                    throw new Exception("Duplicated Student PID!");
                }
                student.setCode(code);
                student.setPid(pid);
                student.setLastName(lname);
                student.setFirstName(fname);
                student.setDob(calendar.getTime());
                student.setPob(pob);
                student.setGender(gender);
                student.setMajor(major);
                student.setPhone(phone);
                student.setEmail(email);
                student.setAddress(address);
                student.setImage(imagePath);

                studentDAO.updateStudent(student);
                message = lname + " " + fname + " has been updated.";
            }
        } catch (Exception e) {
            message = e.getMessage();
        } finally {
            request.setAttribute("message", message);
            request.getRequestDispatcher("edit_student.jsp").forward(request, response);
        }
    }
}
