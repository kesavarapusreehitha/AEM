package com.aem.geeks.core.models;

import javax.annotation.Resource;

import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FeaturedVideoTextModel {

    @ValueMapValue
    private String headline;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String video;

    @ValueMapValue
    private String ctaText;

    @ValueMapValue
    private String ctaLink;

    public String getHeadline() {
        return headline;
    }

    public String getDescription() {
        return description;
    }

    public String getVideo() {
        return video;
    }

    public String getCtaText() {
        return ctaText;
    }

    public String getCtaLink() {
        return ctaLink;
    }
}
