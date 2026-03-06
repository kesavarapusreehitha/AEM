package com.aem.geeks.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ExternalLinkModel {

    @ValueMapValue
    private String linkURL;

    @ValueMapValue
    private String openInNewTab;

    public String getLinkURL() {
        return linkURL;
    }

    public boolean isOpenInNewTab() {
        return "true".equalsIgnoreCase(openInNewTab);
    }
}
