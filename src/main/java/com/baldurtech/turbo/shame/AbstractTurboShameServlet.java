package com.baldurtech.turbo.shame;

import java.util.Map;
import java.util.HashMap;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractTurboShameServlet extends HttpServlet {

    public void render(HttpServletRequest request, HttpServletResponse response,
                       String page)
        throws IOException, ServletException {

        render(request, response, page, new HashMap<String, Object>());
    }

    public void render(HttpServletRequest request, HttpServletResponse response,
                       String page, Map<String, Object> dataModel)
        throws IOException, ServletException {

        for(String key: dataModel.keySet()) {
            request.setAttribute(key, dataModel.get(key));
        }

        getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/" + page + ".jsp")
            .forward(request, response);
    }
}
