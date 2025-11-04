package com.adobe.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.xfa.Int;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class name {
    @ValueMapValue
    private String name;
    @ValueMapValue
    private Int number;

    public String getname() {
        return name;
    }

    public Int getnumber() {
        return number;
    }
}
