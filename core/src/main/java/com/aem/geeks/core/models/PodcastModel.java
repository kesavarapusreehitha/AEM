package com.aem.geeks.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PodcastModel {

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String videoLink;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String hostName;

    @ValueMapValue
    private String hostImage;
    
    public String getTitle() {
        return title;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public String getDescription() {
        return description;
    }

    public String getHostName() {
        return hostName;
    }

    public String getHostImage() {
        return hostImage;
    }
}
