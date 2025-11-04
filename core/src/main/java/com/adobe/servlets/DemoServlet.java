package com.adobe.servlets;

import java.io.IOException;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class, immediate = true, property = { "sling.servlet.paths=/bin/srihitha/karede" })

public class DemoServlet extends SlingSafeMethodsServlet {
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("surge", "soft");
        response.getWriter().write(job.build().toString());
    }

}
