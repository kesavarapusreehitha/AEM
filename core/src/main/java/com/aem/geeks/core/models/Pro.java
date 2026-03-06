package com.aem.geeks.core.models;

import javax.annotation.Resource;
import javax.xml.bind.annotation.XmlElement.DEFAULT;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


@Model(adaptables=Resource.class,defaultInjectionStrategy=DefaultInjectionStrategy.OPTIONAL)

public class Pro {
    @ValueMapValue
    private String textfield;
    @ValueMapValue
    private String textarea;
    public String getTextField(){
        return textfield;
    }
    public String getTextArea(){
        return textarea;
    }

    

}
