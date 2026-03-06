package com.aem.geeks.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, adapters = LinkModel.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class LinkModel {

    @ValueMapValue
    private String linkURL;

    @ValueMapValue
    private String openInNewTab;

    public String getLinkURL() {
        return linkURL;
    }

    public String getOpenInNewTab() {
        return openInNewTab;
    }
}
