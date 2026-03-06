package com.aem.geeks.core.servlets;

import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/showApiOutput",
        "sling.servlet.methods=GET"
})
public class ShowApiOutputServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws IOException {

        ResourceResolver resolver = request.getResourceResolver();
        Resource resource = resolver.getResource("/content/apppii");

        response.setContentType("text/plain");

        if (resource == null) {
            response.getWriter().write("Node /content/apppii NOT FOUND");
            return;
        }

        ValueMap map = resource.getValueMap();

        String apiResponse = map.get("lastApiResponse", "No Data Found");
        Long updatedTime = map.get("updatedTime", 0L);

        response.getWriter().write("lastApiResponse: " + apiResponse + "\n");
        response.getWriter().write("updatedTime: " + updatedTime);
    }
}
