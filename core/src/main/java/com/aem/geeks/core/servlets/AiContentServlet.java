package com.aem.geeks.core.servlets;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;

import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import java.io.IOException;
import java.util.Map;

import org.json.JSONObject;
import com.aem.geeks.core.services.impl.AIContentService;

@Component(service = Servlet.class)
@SlingServletPaths("/bin/ai/generate-content")
public class AiContentServlet extends SlingAllMethodsServlet {

    @Reference
    private AIContentService aiContentService;

    @Override
    protected void doPost(SlingHttpServletRequest req,
            SlingHttpServletResponse resp)
            throws IOException {

        String prompt = req.getParameter("prompt");

        Map<String, String> result = aiContentService.generateContent(prompt, "teaser");

        resp.setContentType("application/json");
        resp.getWriter().write(new JSONObject(result).toString());
    }
}