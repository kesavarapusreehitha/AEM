package com.adobe.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SideBar {

    @ValueMapValue
    private String logopath;

    @ValueMapValue
    private String logomobile;

    @ValueMapValue
    private String logolink;

    @ValueMapValue
    private String enableswitch;

    @ValueMapValue
    private String country;

    public String getLogopath() {
        return logopath;
    }

    public String getEnableswitch() {
        return enableswitch;
    }

    public String getLogomobile() {
        return logomobile;
    }

    public String getLogolink() {
        return logolink;
    }

    public String isEnableswitch() {
        return enableswitch;
    }

    @ChildResource
    private List<SideBar1> multifield;
    @ChildResource
    private List<SideBar2> multifield1;

    public List<SideBar1> getMultifield() {
        return multifield;
    }

    public List<SideBar2> getMultifield1() {
        return multifield1;
    }

    public String getCountry() {
        return country;
    }

}
