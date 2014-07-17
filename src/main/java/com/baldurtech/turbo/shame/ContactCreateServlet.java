package com.baldurtech.turbo.shame;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContactCreateServlet extends AbstractTurboShameServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        render(request, response, "contact/create");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        Contact contact = new Contact();
        contact.setName(request.getParameter("name"));
        contact.setMobile(request.getParameter("mobile"));
        contact.setVpmn(request.getParameter("vpmn"));
        contact.setEmail(request.getParameter("email"));
        contact.setHomeAddress(request.getParameter("homeAddress"));
        contact.setOfficeAddress(request.getParameter("officeAddress"));
        contact.setMemo(request.getParameter("memo"));
        contact.setJob(request.getParameter("job"));
        contact.setJobLevel(Integer.parseInt(request.getParameter("jobLevel")));

        ContactService contactService = new ContactService();
        contact = contactService.save(contact);

        if(contact.getId() == null) {
            response.getWriter().println("Not saved: " + contact);
        } else {
            response.getWriter().println(contact.getId() + ": " + contact);
        }
    }
}
