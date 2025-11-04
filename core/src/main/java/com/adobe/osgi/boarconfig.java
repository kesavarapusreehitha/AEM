package com.adobe.osgi;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "dudiu", description = "kruhiw")
public @interface boarconfig {

    @AttributeDefinition(name = "User name", description = "about description", defaultValue = "raju", type = AttributeType.STRING, required = true)
    public String username();

    @AttributeDefinition(name = "user id", description = "about description", defaultValue = "9999", type = AttributeType.INTEGER, required = true)
    public int userid();

}
