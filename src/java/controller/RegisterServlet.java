/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.RegistrationDAO;
import dao.SemesterDAO;
import dao.StudentDAO;
import dao.YearDAO;
import entity.Semester;
import entity.Student;
import entity.Year;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thao
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String btnUnregister = request.getParameter("btnUnregister");
        String btnRegister = request.getParameter("btnRegister");
        String strId = request.getParameter("studentId");
        String message = "";
        try {
//            StudentDAO studentDAO = new StudentDAO();
            SemesterDAO semesterDAO = new SemesterDAO();
            YearDAO yearDAO = new YearDAO();

            int studentId = Integer.parseInt(request.getParameter("studentId"));
            int semesterId = Integer.parseInt(request.getParameter("semester"));
            int yearId = Integer.parseInt(request.getParameter("year"));

//            Student student = studentDAO.findStudentByID(studentId);
            Semester semester = semesterDAO.findSemesterByID(semesterId);
            Year year = yearDAO.findYearByID(yearId);

            RegistrationDAO regDAO = new RegistrationDAO();

            if (btnRegister != null) {
                String[] strRegisterId = request.getParameterValues("registerId");
                for (String each : strRegisterId) {
                    int regClazz = Integer.parseInt(each);
                    regDAO.register(studentId, regClazz);
                }
                message = "Registered!";
            } else if (btnUnregister != null) {
                String[] strDeleteId = request.getParameterValues("deleteId");
                for (String each : strDeleteId) {
                    int delClazz = Integer.parseInt(each);
                    regDAO.unregister(studentId, delClazz);
                }
                message = "Class(es) removed!";
            }
        } catch (Exception e) {
            message = e.toString() + "<br/>" + e.getMessage();
        } finally {
            request.setAttribute("message", message);
            request.getRequestDispatcher("register.jsp?id="+strId).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
