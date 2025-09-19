package com.adobe.mysite01.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Carousel {

    @ChildResource(name = "multifield") 
    private List<CarouselItem> multifield;

    public List<CarouselItem> getMultifield() {
        return multifield;
    }
}
