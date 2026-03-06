package com.aem.geeks.core.services.impl;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Basic AI Service Implementation for Demo.
 * Provides direct, relevant content based on user prompts.
 */
@Component(service = a.class, immediate = true)
public class MockAIServiceImpl implements a {
    private static final Logger LOG = LoggerFactory.getLogger(MockAIServiceImpl.class);

    @Override
    public String generateContent(String prompt) {
        LOG.info("Generating simple AI content for: {}", prompt);

        // Brief delay to simulate processing
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
        }

        String p = prompt.toLowerCase();
        String title, description, cta;

        if (p.contains("shop") || p.contains("store") || p.contains("order") || p.contains("buy")) {
            title = "Online Shopping Store";
            description = "Welcome to our shopping store. Find high quality products, great prices, and fast shipping for all your needs. Shop now for the latest deals.";
            cta = "Shop Now";
        } else if (p.contains("hospital") || p.contains("medical") || p.contains("health")
                || p.contains("clinic")) {
            title = "City Health Hospital";
            description = "Our hospital provides complete healthcare services. We have expert doctors and professional medical care for your health and wellness.";
            cta = "Learn More";
        } else if (p.contains("cinema") || p.contains("movie") || p.contains("theatre")) {
            title = "Grand Movie Cinema";
            description = "Enjoy the best movie experience at our local cinema. Book your tickets for the latest blockbusters and enjoy high quality sound and seats.";
            cta = "Book Tickets";
        } else if (p.contains("camp") || p.contains("nature") || p.contains("outdoor")) {
            title = "Summer Nature Camp";
            description = "Join our outdoor nature camp for extreme fun! We offer camping, hiking, and wildlife activities for kids and adults during the summer season.";
            cta = "Join Camp";
        } else if (p.contains("bike") || p.contains("race") || p.contains("racing")) {
            title = "Professional Bike Racing";
            description = "Experience the excitement of professional bike racing events. Watch fast racing action and top riders competing on the track.";
            cta = "See Events";
        } else if (p.contains("eaton") || p.contains("tech") || p.contains("software")) {
            title = capitalize(prompt) + " Technology";
            description = "Discover our latest " + prompt
                    + " solutions. We offer modern software and technology services to help you with your digital needs.";
            cta = "View Details";
        } else {
            // General basic generation for any other prompt
            title = capitalize(prompt) + " Information";
            description = "Find the most relevant information and details about " + prompt
                    + " right here. We provide updated content and resources for your interest.";
            cta = "Explore More";
        }

        return String.format("{\"title\": \"%s\", \"description\": \"%s\", \"cta\": \"%s\"}", title,
                description, cta);
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty())
            return "General";
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
