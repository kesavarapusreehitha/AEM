package com.aem.geeks.core.servlets;

import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.aem.geeks.core.services.impl.AIService;
import com.aem.geeks.core.services.impl.AIService1;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = { Servlet.class }, property = {
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths=bin/ai/generate"
})
public class AIContentGeneratorServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;

    @Reference
    private AIService aiService;

    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws IOException {
        // Match the actual parameter name from your dialog
        String prompt = req.getParameter("prompt");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        if (prompt != null && !prompt.isEmpty()) {
            String jsonResult = aiService.generateContent(prompt);
            resp.getWriter().write(jsonResult);
        } else {
            resp.setStatus(400);
            resp.getWriter().write("{\"error\": \"Prompt is required\"}");
        }
    }
}