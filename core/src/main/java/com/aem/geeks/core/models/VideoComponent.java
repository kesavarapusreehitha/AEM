package com.aem.geeks.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class VideoComponent {

    @ValueMapValue
    private String videoPath;    

    @ValueMapValue
    private String poster;       

    @ValueMapValue
    private Boolean controls;    

    @ValueMapValue
    private Boolean autoplay;    

    @ValueMapValue
    private Boolean loop;        

    @ValueMapValue
    private Boolean muted;       

    @ValueMapValue
    private String captionPath;  

    public String getVideoPath() {
        return videoPath;
    }

    public String getPoster() {
        return poster;
    }

    public Boolean getControls() {
        return controls == null ? Boolean.TRUE : controls;
    }

    public Boolean getAutoplay() {
        return autoplay == null ? Boolean.FALSE : autoplay;
    }

    public Boolean getLoop() {
        return loop == null ? Boolean.FALSE : loop;
    }

    public Boolean getMuted() {
        return muted == null ? Boolean.FALSE : muted;
    }

    public String getCaptionPath() {
        return captionPath;
    }
}
