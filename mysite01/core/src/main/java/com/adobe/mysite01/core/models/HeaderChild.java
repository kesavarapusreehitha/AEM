package com.adobe.mysite01.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeaderChild {

    @ValueMapValue
    private String textfield;
    @ValueMapValue
    private String datepicker;

    public String getTextfield() {
        return textfield;
    }

    public String getDatepicker() {
        return datepicker;
    }

}
