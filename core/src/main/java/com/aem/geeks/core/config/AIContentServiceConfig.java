package com.aem.geeks.core.config;

import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.AttributeDefinition;

@ObjectClassDefinition(name = "AI Content Service Configuration")
public @interface AIContentServiceConfig {

    @AttributeDefinition(name = "API Key")
    String apiKey();

    @AttributeDefinition(name = "Model")
    String model();

    @AttributeDefinition(name = "Endpoint")
    String endpoint();
}