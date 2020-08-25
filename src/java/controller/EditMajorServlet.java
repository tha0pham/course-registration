/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FacultyDAO;
import dao.MajorDAO;
import entity.Major;
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
@WebServlet(name = "EditMajorServlet", urlPatterns = {"/EditMajorServlet"})
public class EditMajorServlet extends HttpServlet {

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
        String btnSave = request.getParameter("btnSave");
        String message = "";

        if (btnSave != null) {
            try {
                int faculty = Integer.parseInt(request.getParameter("faculty"));
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                String updateID = request.getParameter("updateID");

                FacultyDAO facultyDAO = new FacultyDAO();
                MajorDAO majorDAO = new MajorDAO();

                if (updateID == null || updateID.isEmpty()) {
                    if (majorDAO.findMajorByName(name) == null) {
                        Major newMajor = new Major();

                        newMajor.setFaculty(facultyDAO.findFacultyByID(faculty));
                        newMajor.setName(name);
                        newMajor.setDescription(description);

                        majorDAO.addMajor(newMajor);
                        message = name + " successfully added!";
                    } else {
                        message = name + " already existed.<br/>Update ID: " + updateID;
                    }
                } else {
                    int id = Integer.parseInt(updateID);
                    Major updateMajor = majorDAO.findMajorByID(id);

                    updateMajor.setFaculty(facultyDAO.findFacultyByID(faculty));
                    updateMajor.setName(name);
                    updateMajor.setDescription(description);

                    majorDAO.updateMajor(updateMajor);
                    message = name + " successfully updated!";
                }
            } catch (Exception e) {
                message = e.toString() + "<br/>" + e.getMessage();
            } finally {
                request.setAttribute("message", message);
                request.getRequestDispatcher("edit_major.jsp").forward(request, response);
            }
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
