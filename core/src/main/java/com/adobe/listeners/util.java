package com.adobe.mysite01.core.listeners;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.LoginException;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = util.class)
public class util {

    @Reference
    private ResourceResolverFactory resolverFactory;

    public ResourceResolver getResourceResolver() throws org.apache.sling.api.resource.LoginException, LoginException {
        ResourceResolver resolver = null;
        Map<String, Object> param = new HashMap<>();
        param.put(ResourceResolverFactory.SUBSERVICE, "demo");

        resolver = resolverFactory.getServiceResourceResolver(param);
        return resolver;
    }
}
