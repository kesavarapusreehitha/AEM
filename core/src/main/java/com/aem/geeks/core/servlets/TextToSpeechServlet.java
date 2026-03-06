package com.aem.geeks.core.servlets;

import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.osgi.service.component.annotations.Component;
import javax.servlet.Servlet;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Component(service = { Servlet.class }, property = {
        "sling.servlet.paths=/bin/tts",
        "sling.servlet.methods=GET,POST"
})
public class TextToSpeechServlet extends SlingAllMethodsServlet {

    private static final String RAPIDAPI_URL = "https://example-tts.p.rapidapi.com/speech";
    private static final String RAPIDAPI_KEY = "YOUR_RAPIDAPI_KEY";
    private static final String RAPIDAPI_HOST = "example-tts.p.rapidapi.com";

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws IOException {

        String text = request.getParameter("text");
        if (text == null || text.trim().isEmpty()) {
            response.setStatus(400);
            response.getWriter().write("Text is required");
            return;
        }

        String payload = "{\"text\":\"" + text.replace("\"", "'") + "\"}";

        HttpURLConnection conn = null;
        try {
            URL url = new URL(RAPIDAPI_URL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("content-type", "application/json");
            conn.setRequestProperty("x-rapidapi-key", RAPIDAPI_KEY);
            conn.setRequestProperty("x-rapidapi-host", RAPIDAPI_HOST);
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload.getBytes(StandardCharsets.UTF_8));
            }

            response.setContentType("audio/mpeg");
            response.setStatus(conn.getResponseCode());

            try (InputStream is = conn.getInputStream();
                    OutputStream out = response.getOutputStream()) {

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
                out.flush();
            }

        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("Error: " + e.getMessage());
        } finally {
            if (conn != null)
                conn.disconnect();
        }
    }
}
