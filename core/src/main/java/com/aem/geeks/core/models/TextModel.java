package com.aem.geeks.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.day.cq.wcm.api.designer.Style;

import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TextModel {

    @ValueMapValue
    private String text;

    @ValueMapValue
    private String link;

    @ValueMapValue
    private Boolean openInNewTab;

    @ChildResource(name = "multifield")
    private List<MultifieldItem> multifield;

 
    @ScriptVariable
    private Style currentStyle;

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
    }

    public boolean isOpenInNewTab() {
        return openInNewTab != null && openInNewTab;
    }

    public List<MultifieldItem> getMultifield() {
        return multifield;
    }

    public String getButtonColor() {
        return currentStyle != null ? currentStyle.get("buttonColor", "blue") : "blue";
    }

    public String getAlignment() {
        return currentStyle != null ? currentStyle.get("alignment", "left") : "left";
    }

    public boolean isShadowEnabled() {
        return currentStyle != null && currentStyle.get("enableShadow", false);
    }
}