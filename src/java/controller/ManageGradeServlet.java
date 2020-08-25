/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.RegistrationDAO;
import entity.Registration;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thao
 */
@WebServlet(name = "ManageGradeServlet", urlPatterns = {"/ManageGradeServlet"})
public class ManageGradeServlet extends HttpServlet {

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
        String message = "";
        String strClazzId = request.getParameter("clazz");
        String[] strStudentId = request.getParameterValues("studentId");
        String[] strMidterm = request.getParameterValues("midterm");
        String[] strFinal = request.getParameterValues("final");
        String[] strAssignment = request.getParameterValues("assignment");
        String btnSave = request.getParameter("btnSave");
        RegistrationDAO regDAO = new RegistrationDAO();

        try {
            if (btnSave != null) {
                int clazzId = Integer.parseInt(strClazzId);
                for (int i = 0; i < strStudentId.length; i++) {
                    int studentId = Integer.parseInt(strStudentId[i]);
                    Registration reg = regDAO.findRegistration(studentId, clazzId);

                    int midterm = Integer.parseInt(strMidterm[i]);
                    int finalExam = Integer.parseInt(strFinal[i]);
                    int assignment = Integer.parseInt(strAssignment[i]);

                    reg.setMidterm(midterm);
                    reg.setFinalExam(finalExam);
                    reg.setAssignment(assignment);

                    regDAO.updateScore(reg);
                }
                message = "Score updated.";
            }
        } catch (Exception e) {
            message = e.toString() + "<br/>" + e.getMessage();
        } finally {
            request.setAttribute("message", message);
            request.getRequestDispatcher("manage_grade.jsp").forward(request, response);
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
