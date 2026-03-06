package com.aem.geeks.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CtaModel {

    @ValueMapValue
    private String text;

    @ValueMapValue
    private String link;

    @ValueMapValue
    private Boolean openInNewTab;

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
    }

    public String getTarget() {
        return Boolean.TRUE.equals(openInNewTab) ? "_blank" : "_self";
    }

    public boolean isOpenInNewTab() {
        return Boolean.TRUE.equals(openInNewTab);
    }
}
