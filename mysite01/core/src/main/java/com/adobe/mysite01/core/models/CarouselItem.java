package com.adobe.mysite01.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CarouselItem {

    @ValueMapValue
    private String textfield;

    @ValueMapValue
    private String pathfield;

    @ChildResource(name = "multifield1") // nested multifield
    private List<NestedItem> multifield1;

    public String getTextfield() {
        return textfield;
    }

    public String getPathfield() {
        return pathfield;
    }

    public List<NestedItem> getMultifield1() {
        return multifield1;
    }
}
