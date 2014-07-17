package com.baldurtech.turbo.shame;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContactListServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        ContactService contactService = new ContactService();

        request.setAttribute("contactList", contactService.getAllContacts());

        getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/contact/list.jsp")
            .forward(request, response);
    }

}
