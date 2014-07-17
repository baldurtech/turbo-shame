package com.baldurtech.turbo.shame;

import java.util.Map;
import java.util.HashMap;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContactShowServlet extends AbstractTurboShameServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        ContactService contactService = new ContactService();
        Contact contact = contactService.getContactById(request.getParameter("id"));

        if(contact != null) {
            String page = "contact/show";

            Map<String, Object> dataModel = new HashMap<String, Object>();
            dataModel.put("contact", contact);

            render(request, response, page, dataModel);
        } else {
            render(request, response, "contact/not_found");
        }
    }
}
