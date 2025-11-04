package com.adobe.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SideBar2 {

    @ValueMapValue
    private String desktopicon;

    @ValueMapValue
    private String mobileicon;

    public String getDesktopIcon() {
        return desktopicon;
    }

    public String getMobileIcon() {
        return mobileicon;
    }

    @ChildResource
    private List<SideBar3> multifield2;

    public List<SideBar3> getMultifield2() {
        return multifield2;
    }

}
