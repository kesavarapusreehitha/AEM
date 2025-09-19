package com.adobe.mysite01.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class carouselChild {

    @ValueMapValue
    private String textfield;
    @ValueMapValue
    private String pathfield;

    public String getPathfield() {
        return pathfield;
    }

    public String getTextfield() {
        return textfield;
    }

    @ChildResource
    private List<caroselChild1> multifield1;

    public List<caroselChild1> getMultifield1() {
        return multifield1;
    }
}