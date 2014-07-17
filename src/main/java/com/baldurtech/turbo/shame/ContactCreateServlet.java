package com.baldurtech.turbo.shame;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContactCreateServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/contact/create.jsp")
            .forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        response.getWriter().println("Yeah! Hang Zai has saved.");
        response.getWriter().println(request.getParameter("name"));
        response.getWriter().println(request.getParameter("mobile"));
        response.getWriter().println(request.getParameter("vpmn"));
        response.getWriter().println(request.getParameter("email"));
        response.getWriter().println(request.getParameter("homeAddress"));
        response.getWriter().println(request.getParameter("officeAddress"));
        response.getWriter().println(request.getParameter("memo"));
        response.getWriter().println(request.getParameter("job"));
        response.getWriter().println(request.getParameter("jobLevel"));
    }
}
