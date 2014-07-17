package com.baldurtech.turbo.shame;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContactServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        ContactService contactService = new ContactService();

        if(request.getParameter("contactId") == null) {

            request.setAttribute("contactList", contactService.getAllContacts());

            getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/contact/list.jsp")
                .forward(request, response);

        } else {
            Contact contact = contactService.getContactById(request.getParameter("contactId"));

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

}
