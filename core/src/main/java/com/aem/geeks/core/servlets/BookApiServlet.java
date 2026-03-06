package com.aem.geeks.core.servlets;

import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.osgi.service.component.annotations.Component;
import javax.servlet.Servlet;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

@Component(service = { Servlet.class }, property = {
        "sling.servlet.methods=GET",
        "sling.servlet.paths=/bin/bookapi"
})
public class BookApiServlet extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");
        JSONObject json = new JSONObject();
        try {
            json.put("book", "Effective Java");
        } catch (JSONException e) {

            e.printStackTrace();
        }
        try {
            json.put("author", "Joshua Bloch");
        } catch (JSONException e) {

            e.printStackTrace();
        }
        response.getWriter().write(json.toString());
    }
}