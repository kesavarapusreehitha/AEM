package com.adobe.osgi;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "urhu", description = "yjugfhysj")
public @interface configuration {

    @AttributeDefinition(name = "schedular name", description = "schedular name", type = AttributeType.STRING)
    public String getSchedularname() default "schedular";

    @AttributeDefinition(name = "cron expression", description = "schedular  cron expression", type = AttributeType.STRING)
    public String getcronexpression() default "*/5 * * * *";

    @AttributeDefinition(name = "can run concurrent", description = "is can run concurrent", type = AttributeType.BOOLEAN)
    public boolean iscanrunconcurrent() default true;

    @AttributeDefinition(name = "schedular enabled", description = "schedular enabled name", type = AttributeType.BOOLEAN)
    public boolean isenabled() default true;

}
