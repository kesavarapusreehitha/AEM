package com.aem.geeks.core.services.impl;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.*;

import org.json.JSONObject;

import java.util.Map;
import java.util.HashMap;

@Component(service = AIContentService.class, immediate = true)
@Designate(ocd = AIContentServiceImpl.Config.class)
public class AIContentServiceImpl implements AIContentService {

    @ObjectClassDefinition(name = "AI Content Service Configuration")
    public @interface Config {

        @AttributeDefinition(name = "OpenAI API Key")
        String apiKey() default "";

    }

    private String apiKey;

    @Activate
    protected void activate(Config config) {
        this.apiKey = config.apiKey();
    }

    @Override
    public Map<String, String> generateContent(String prompt, String type) {

        Map<String, String> result = new HashMap<>();

        // Dummy response (replace with OpenAI logic if needed)
        result.put("title", "Generated title for: " + prompt);
        result.put("description", "This is a professional description about: " + prompt);
        result.put("ctaText", "Explore more about: " + prompt);

        return result;
    }
}