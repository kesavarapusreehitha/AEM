package com.aem.geeks.core.servlets;

import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Component(service = Servlet.class, property = {
        "sling.servlet.methods=" + HttpConstants.METHOD_POST,
        "sling.servlet.paths=" + "/bin/rapid/voice"
})
public class VoiceApiServlet extends SlingAllMethodsServlet {

    @Override
    protected void doPost(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws IOException {

        resp.setContentType("application/json");

        // Read JSON from request
        StringBuilder body = new StringBuilder();
        String line;
        try (BufferedReader reader = req.getReader()) {
            while ((line = reader.readLine()) != null) {
                body.append(line);
            }
        }

        String text = "";
        try {
            JSONObject inputJson = new JSONObject(body.toString());
            text = inputJson.optString("text", "");
        } catch (JSONException e) {
            resp.getWriter().write("{\"error\":\"Invalid JSON\"}");
            return;
        }

        if (text == null || text.trim().isEmpty()) {
            resp.getWriter().write("{\"error\":\"Text is required\"}");
            return;
        }

        try {
            // Call Node API
            JSONObject json = new JSONObject();
            json.put("text", text);

            URL url = new URL("http://localhost:3000/api/voice/tts");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.toString().getBytes());
            }

            StringBuilder responseData = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                while ((line = br.readLine()) != null) {
                    responseData.append(line);
                }
            }

            conn.disconnect();

            resp.getWriter().write(responseData.toString());

        } catch (Exception e) {
            JSONObject errorJson = new JSONObject();
            try {
                errorJson.put("error", "Failed to call Node API");
                errorJson.put("details", e.getMessage());
            } catch (JSONException ignored) {
            }
            resp.getWriter().write(errorJson.toString());
        }
    }
}
