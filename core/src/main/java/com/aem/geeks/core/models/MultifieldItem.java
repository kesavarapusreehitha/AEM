package com.aem.geeks.core.models;

import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.api.resource.Resource;

@Model(adaptables = Resource.class)
public class MultifieldItem {

    @ValueMapValue
    private String textfield;

    @ValueMapValue
    private String pathfield;

    public String getTextfield() {
        return textfield;
    }

    public String getPathfield() {
        return pathfield;
    }
}
