package com.adobe.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeaderModel {
    @ValueMapValue
    private String pathfield;
    @ValueMapValue
    private String checkbox;
    @ValueMapValue
    private String text;
    @ChildResource
    private List<HeaderChild> multifield;

    public List<HeaderChild> getMultifield() {
        return multifield;
    }

    public String getPathfield() {
        return pathfield;
    }

    public String getCheckbox() {
        return checkbox;
    }

    public String getText() {
        return text;
    }
}
