package com.aem.geeks.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import java.io.IOException;

import static org.apache.sling.api.servlets.ServletResolverConstants.SLING_SERVLET_PATHS;

@Component(
        service = Servlet.class,
        property = {
                SLING_SERVLET_PATHS + "=/bin/content-fragment"
        }
)
public class ContentFragmentServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request,
                         SlingHttpServletResponse response) throws IOException {

        
        String fragmentPath = "/content/dam/my-fragments/sample-fragment";

        Resource resource = request.getResourceResolver().getResource(fragmentPath + "/jcr:content/data/master");

        if (resource != null) {

            ValueMap properties = resource.getValueMap();

            String title = properties.get("fieldLabel", String.class);
            String description = properties.get("description", String.class);
            String date = properties.get("date", String.class);

            response.setContentType("application/json");

            response.getWriter().write("{");
            response.getWriter().write("\"title\":\"" + title + "\",");
            response.getWriter().write("\"description\":\"" + description + "\",");
            response.getWriter().write("\"date\":\"" + date + "\"");
            response.getWriter().write("}");

        } else {
            response.getWriter().write("Content Fragment not found");
        }
    }
}



