package com.adobe.osgi;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "hjkkkk", description = "hjkl")

public @interface configship {
    @AttributeDefinition(name = "hbnj", description = "hjk", defaultValue = "hj", type = AttributeType.STRING)
    public String userName();

    @AttributeDefinition(name = "gghhhj", description = "njmm", defaultValue = "9999", type = AttributeType.INTEGER)
    public int userid();

}
