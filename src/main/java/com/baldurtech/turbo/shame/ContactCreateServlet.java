package com.baldurtech.turbo.shame;

import java.util.Map;
import java.util.HashMap;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContactCreateServlet extends AbstractTurboShameServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        render(request, response, "contact/create", new HashMap<String, Object>(){{
                    put("contact", new Contact());
                }});
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        ContactService contactService = new ContactService();
        final Contact contact = contactService.save(createContactFromRequest(request));

        if(contact.getId() == null) {
            Map<String, Object> dataModel = new HashMap<String, Object>();
            dataModel.put("contact", contact);
            dataModel.put("flash.message", "Cannot save contact!");

            render(request, response, "contact/create", dataModel);
        } else {
            response.sendRedirect("show?id=" + contact.getId());
        }
    }

    public Contact createContactFromRequest(HttpServletRequest request) {
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

        return contact;
    }
}
