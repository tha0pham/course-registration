/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CourseDAO;
import dao.MajorDAO;
import entity.Course;
import entity.Major;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thao
 */
@WebServlet(name = "EditCourseServlet", urlPatterns = {"/EditCourseServlet"})
public class EditCourseServlet extends HttpServlet {

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
        String message = "Nothing happened.";
        if (btnSave != null) {
            try {
                String code = request.getParameter("code");
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                float cost = Float.parseFloat(request.getParameter("cost"));
                int credit = Integer.parseInt(request.getParameter("credit"));
                int hour = Integer.parseInt(request.getParameter("hour"));
                int passingScore = Integer.parseInt(request.getParameter("passingScore"));      
                String updateID = request.getParameter("updateID");

                MajorDAO majorDAO = new MajorDAO();                        
                String[] majorIds = request.getParameterValues("majorId");
                List<Major> majors = new ArrayList<>();
                for (String each: majorIds) {
                    int id = Integer.parseInt(each);
                    Major temp = majorDAO.findMajorByID(id);
                    majors.add(temp);
                }
                
                CourseDAO courseDAO = new CourseDAO();

                if (updateID == null || updateID.isEmpty()) {
                    if (courseDAO.findCourseByName(name) == null) {
                        Course newCourse = new Course();
                        newCourse.setCode(code);
                        newCourse.setName(name);
                        newCourse.setDescription(description);
                        newCourse.setCost(cost);
                        newCourse.setCredit(credit);
                        newCourse.setHour(hour);
                        newCourse.setPassingScore(passingScore);
                        newCourse.setDescription(description);
                        newCourse.setMajorList(majors);
                        courseDAO.addCourse(newCourse);
                        message = name + " successfully added!";
                    } else {
                        message = name + " already existed.";
                    }
                } else {
                    int id = Integer.parseInt(updateID);
                    Course updateCourse = courseDAO.findCourseByID(id);
                    if (!updateCourse.getCode().equals(code) && courseDAO.findCourseByCode(code) != null) {
                    throw new Exception("Duplicated Course Code!");
                }
                        updateCourse.setCode(code);
                        updateCourse.setName(name);
                        updateCourse.setDescription(description);
                        updateCourse.setCost(cost);
                        updateCourse.setCredit(credit);
                        updateCourse.setHour(hour);
                        updateCourse.setPassingScore(passingScore);
                        updateCourse.setDescription(description);
                        updateCourse.setMajorList(majors);
                        courseDAO.updateCourse(updateCourse);
                        message = name + " successfully updated!";
                }
            } catch (Exception e) {
                message = e.toString() + "<br/>" + e.getMessage();
            } finally {
                request.setAttribute("message", message);
                request.getRequestDispatcher("edit_course.jsp").forward(request, response);
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
