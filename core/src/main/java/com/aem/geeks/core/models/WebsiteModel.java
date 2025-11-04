package com.aem.geeks.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class WebsiteModel {

    @ValueMapValue
    private String uploadimage;

    @ValueMapValue
    private String datepicker;

    @ValueMapValue
    private String textfield; 

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String textarea; 

    @ValueMapValue
    private String pathfield; 

    public String getUploadimage() {
        return uploadimage;
    }

    public String getDatepicker() {
        return datepicker;
    }

    public String getTextfield() {
        return textfield;
    }

    public String getDescription() {
        return description;
    }

    public String getTextarea() {
        return textarea;
    }
    public String getPathfield() {
        return pathfield;
    }
}
