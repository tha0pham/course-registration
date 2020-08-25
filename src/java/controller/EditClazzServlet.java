/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ClazzDAO;
import dao.CourseDAO;
import dao.InstructorDAO;
import dao.SemesterDAO;
import dao.YearDAO;
import entity.Year;
import entity.Clazz;
import entity.Course;
import entity.Instructor;
import entity.Semester;
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
@WebServlet(name = "EditClazzServlet", urlPatterns = {"/EditClazzServlet"})
public class EditClazzServlet extends HttpServlet {

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
                Semester semester = new SemesterDAO().findSemesterByID(Integer.parseInt(request.getParameter("semester")));
                Year year = new YearDAO().findYearByID(Integer.parseInt(request.getParameter("year")));
                Course course = new CourseDAO().findCourseByID(Integer.parseInt(request.getParameter("course")));
                Instructor instructor = new InstructorDAO().findInstructorByID(Integer.parseInt(request.getParameter("instructor")));
                String name = request.getParameter("name");
                int week = Integer.parseInt(request.getParameter("week"));
                int capacity = Integer.parseInt(request.getParameter("capacity"));
                float maxabsent = Float.parseFloat(request.getParameter("maxabsent"));
                int midterm = Integer.parseInt(request.getParameter("midterm"));
                int finalexam = Integer.parseInt(request.getParameter("final"));
                int assignment = Integer.parseInt(request.getParameter("assignment"));                
                String updateID = request.getParameter("updateID");

                ClazzDAO clazzDAO = new ClazzDAO();

                if (updateID == null || updateID.isEmpty()) {
                    if (clazzDAO.findClazzByName(name) == null) {
                        Clazz newClazz = new Clazz();
                        newClazz.setWeek(week);
                        newClazz.setName(name);
                        newClazz.setCapacity(capacity);
                        newClazz.setMaxAbsent(maxabsent);
                        newClazz.setMidterm(midterm);
                        newClazz.setFinalExam(finalexam);
                        newClazz.setAssignment(assignment);
                        newClazz.setCapacity(capacity);
                        newClazz.setYear(year);
                        newClazz.setSemester(semester);
                        newClazz.setCourse(course);
                        newClazz.setInsId(instructor);
                        clazzDAO.addClazz(newClazz);
                        message = name + " successfully added!";
                    } else {
                        message = name + " already existed.";
                    }
                } else {
                    int id = Integer.parseInt(updateID);
                    Clazz updateClazz = clazzDAO.findClazzByID(id);
                    if (!updateClazz.getName().equals(name) && clazzDAO.findClazzByName(name) != null) {
                    throw new Exception("Duplicated Class Name!");
                }
                        updateClazz.setWeek(week);
                        updateClazz.setName(name);
                        updateClazz.setCapacity(capacity);
                        updateClazz.setMaxAbsent(maxabsent);
                        updateClazz.setMidterm(midterm);
                        updateClazz.setFinalExam(finalexam);
                        updateClazz.setAssignment(assignment);
                        updateClazz.setCapacity(capacity);
                        updateClazz.setYear(year);
                        updateClazz.setSemester(semester);
                        updateClazz.setCourse(course);
                        updateClazz.setInsId(instructor);
                        clazzDAO.updateClazz(updateClazz);
                        message = name + " successfully updated!";
                }
            } catch (Exception e) {
                message = e.toString() + "<br/>" + e.getMessage();
            } finally {
                request.setAttribute("message", message);
                request.getRequestDispatcher("list_clazz.jsp").forward(request, response);
            }
        } else 
            response.sendRedirect("list_clazz.jsp");
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
