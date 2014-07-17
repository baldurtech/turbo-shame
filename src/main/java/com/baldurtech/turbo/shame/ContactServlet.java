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
            response.getWriter().println("Get all contacts.");

            for(Contact contact: contactService.getAllContacts()) {
                response.getWriter().println("Id: " + contact.getId());
                response.getWriter().println("Name: " + contact.getName());
                response.getWriter().println("Mobile: " + contact.getMobile());
                response.getWriter().println("Vpmn: " + contact.getVpmn());
                response.getWriter().println("Job: " + contact.getJob());
            }
        } else {
            response.getWriter().println("Get contact by id: " + request.getParameter("contactId"));

            Contact contact = contactService.getContactById(request.getParameter("contactId"));

            if(contact != null) {
                response.getWriter().println("Name: "          + contact.getName());
                response.getWriter().println("Mobile: "        + contact.getMobile());
                response.getWriter().println("Vpmn: "          + contact.getVpmn());
                response.getWriter().println("Email: "         + contact.getEmail());
                response.getWriter().println("HomeAddress: "   + contact.getHomeAddress());
                response.getWriter().println("OfficeAddress: " + contact.getOfficeAddress());
                response.getWriter().println("Memo: "          + contact.getMemo());
                response.getWriter().println("Job: "           + contact.getJob());
                response.getWriter().println("JobLevel: "      + contact.getJobLevel());
            } else {
                response.getWriter().println("Contact not found!");
            }
        }
    }

}
