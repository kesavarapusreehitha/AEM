package com.adobe.mysite01.core.models.sites;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class FormDataModel {
    @SlingObject
    private Resource resource;

    private List<Map<String, String>> formDataList = new ArrayList<>();

    @PostConstruct
    protected void init() {
        try {
            // Get resource resolver from the current resource
            ResourceResolver resolver = resource.getResourceResolver();

            // Data parent path (where servlet saves data)
            Resource parent = resolver.getResource("/content/userdata");

            // Iterate over each record node under /content/userdata
            if (parent != null) {
                for (Resource child : parent.getChildren()) {
                    Map<String, String> data = new HashMap<>();
                    data.put("id", child.getName());
                    data.put("name", child.getValueMap().get("name", ""));
                    data.put("email", child.getValueMap().get("email", ""));
                    data.put("mobile", child.getValueMap().get("mobile", ""));
                    formDataList.add(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Map<String, String>> getFormDataList() {
        return formDataList;
    }
}
