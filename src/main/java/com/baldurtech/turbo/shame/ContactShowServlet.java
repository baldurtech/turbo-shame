package com.baldurtech.turbo.shame;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContactShowServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        ContactService contactService = new ContactService();
        Contact contact = contactService.getContactById(request.getParameter("id"));

        if(contact != null) {
            request.setAttribute("contact", contact);

            getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/contact/show.jsp")
                .forward(request, response);
        } else {
            response.getWriter().println("Contact not found!");
        }
    }
}
