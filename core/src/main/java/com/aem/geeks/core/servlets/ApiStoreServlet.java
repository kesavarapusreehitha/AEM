package com.aem.geeks.core.servlets;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/storeApi",
        "sling.servlet.methods=GET,POST"
})

public class ApiStoreServlet extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        String apiUrl = "https://jsonplaceholder.typicode.com/posts/1";

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(apiUrl);

        CloseableHttpResponse apiResponse = client.execute(get);
        String result = EntityUtils.toString(apiResponse.getEntity());

        storeInCRXDE(request.getResourceResolver(), result);

        response.getWriter().write("GET API Response Stored Successfully!");
    }

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        String apiUrl = "https://jsonplaceholder.typicode.com/posts";
        String json = "{\"title\":\"AEM API Test\",\"body\":\"Hello\",\"userId\":1}";

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(apiUrl);

        post.setHeader("Content-type", "application/json");
        post.setEntity(new StringEntity(json));

        CloseableHttpResponse apiResponse = client.execute(post);
        String result = EntityUtils.toString(apiResponse.getEntity());

        storeInCRXDE(request.getResourceResolver(), result);

        response.getWriter().write("POST API Response Stored Successfully!");
    }

    private void storeInCRXDE(ResourceResolver resolver, String responseText) {

        try {
            Resource resource = resolver.getResource("/content/apppii");

            if (resource != null) {
                ModifiableValueMap properties = resource.adaptTo(ModifiableValueMap.class);
                properties.put("lastApiResponse", responseText);
                properties.put("updatedTime", System.currentTimeMillis());
                resolver.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
