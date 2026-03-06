package com.aem.geeks.core.services.impl;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = AIService.class, immediate = true)
public class AIService1 implements AIService {
    private static final Logger LOG = LoggerFactory.getLogger(AIService1.class);

    public String generateContent(String prompt) {
        LOG.info("Generating AI content for: {}", prompt);

        String title, description, cta;

        if (prompt.toLowerCase().contains("shop")) {
            title = "Online Shopping Store";
            description = "Welcome to our shopping store...";
            cta = "Shop Now";
        } else {
            title = "General " + prompt;
            description = "Find information about " + prompt;
            cta = "Explore More";
        }

        return String.format("{\"title\":\"%s\", \"description\":\"%s\", \"ctaText\":\"%s\"}",
                title, description, cta);
    }
}