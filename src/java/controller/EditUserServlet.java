package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import entity.User;
import java.util.Calendar;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "EditUserServlet", urlPatterns = {"/EditUserServlet"})
public class EditUserServlet extends HttpServlet {
	private final String DEFAULT_PASSWORD = "abc123";
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
            String updateID = request.getParameter("updateID");            
            String username = request.getParameter("username");
            String lname = request.getParameter("lname");
            String fname = request.getParameter("fname");

            int cyear = Integer.parseInt(request.getParameter("cyear"));
            int cmonth = Integer.parseInt(request.getParameter("cmonth"));
            int cdate = Integer.parseInt(request.getParameter("cdate"));
            Calendar calendar = Calendar.getInstance();
            calendar.set(cyear, cmonth, cdate);

            boolean gender = Integer.parseInt(request.getParameter("gender")) == 0;

            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
			
            UserDAO userDAO = new UserDAO();
            User user;

            if (updateID == null || updateID.isEmpty()) {
                if (userDAO.findUserByUsername(username) == null) {
                    user = new User();
                    user.setUsername(username);
                    user.setPassword(DEFAULT_PASSWORD);
                    user.setLastName(lname);
                    user.setFirstName(fname);
                    user.setDateCreated(calendar.getTime());                    
                    user.setGender(gender);                    
                    user.setPhone(phone);
                    user.setEmail(email);
                    user.setAddress(address);                    

                    userDAO.addUser(user);
                    message = lname + " " + fname + " has been added.";
                } else {
                    message = "Error adding: User code or PID already existed.";
                }
            } else {
                int id = Integer.parseInt(updateID);
                user = userDAO.findUserByID(id);

                if (!user.getUsername().equals(username) && userDAO.findUserByUsername(username) != null) {
                    throw new Exception("Duplicated User Name!");
                }                
                user.setUsername(username);
                    user.setPassword(DEFAULT_PASSWORD);
                    user.setLastName(lname);
                    user.setFirstName(fname);
                    user.setDateCreated(calendar.getTime());                    
                    user.setGender(gender);                    
                    user.setPhone(phone);
                    user.setEmail(email);
                    user.setAddress(address);         
                userDAO.updateUser(user);
                message = lname + " " + fname + " has been updated.";
            }
        } catch (Exception e) {
            message = e.getMessage();
        } finally {
            request.setAttribute("message", message);
            request.getRequestDispatcher("edit_user.jsp").forward(request, response);
        }
    }
}
