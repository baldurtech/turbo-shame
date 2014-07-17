package com.baldurtech.turbo.shame;

import java.util.Map;
import java.util.HashMap;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContactListServlet extends AbstractTurboShameServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        ContactService contactService = new ContactService();

        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put("contactList", contactService.getAllContacts());

        render(request, response, "contact/list", dataModel);
    }

}
