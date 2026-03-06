package com.aem.geeks.core.servlets;

import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.LoginException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component(
        service = Servlet.class,
        property = {
        "sling.servlet.methods=POST",
        "sling.servlet.paths=/bin/mylogin"
}

)
public class MyLoginServlet extends SlingAllMethodsServlet {

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");

        Map<String, Object> param = new HashMap<>();
        param.put(ResourceResolverFactory.SUBSERVICE, "readService");

        try {
            ResourceResolver resolver = resourceResolverFactory.getServiceResourceResolver(param);
        } catch (org.apache.sling.api.resource.LoginException e) {
            e.printStackTrace();
        }

        response.getWriter().write("{\"status\":\"success\", \"message\":\"Login servlet working\"}");
    }
}

