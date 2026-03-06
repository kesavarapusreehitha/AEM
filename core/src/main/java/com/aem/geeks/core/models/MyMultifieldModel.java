package com.aem.geeks.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.List;

@Model(adaptables = Resource.class)
public class MyMultifieldModel {

    @ChildResource(name = "items")
    public List<MyItem> items;

    @Model(adaptables = Resource.class)
    public static class MyItem {

        @ValueMapValue(name = "title")
        public String title;

        @ValueMapValue(name = "pathfield")
        public String pathfield;
    }
}
