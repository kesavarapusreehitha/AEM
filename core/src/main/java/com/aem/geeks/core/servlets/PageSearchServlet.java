package com.aem.geeks.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import org.osgi.service.component.annotations.Component;

import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/pagesearch",
        "sling.servlet.methods=GET"
})
public class PageSearchServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws IOException {

        String keyword = request.getParameter("keyword");
        response.setContentType("application/json");

        ResourceResolver resolver = request.getResourceResolver();
        QueryBuilder qb = resolver.adaptTo(QueryBuilder.class);

        Map<String, String> map = new HashMap<>();
        map.put("path", "/content/project");
        map.put("type", "cq:Page");
        map.put("fulltext", keyword);

        Query query = qb.createQuery(PredicateGroup.create(map), resolver.adaptTo(Session.class));
        SearchResult result = query.getResult();

        JsonArray arr = new JsonArray();
        for (Hit hit : result.getHits()) {
            JsonObject obj = new JsonObject();
            try {
                obj.addProperty("path", hit.getPath());
            } catch (RepositoryException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            arr.add(obj);
        }

        response.getWriter().write(arr.toString());
    }
}