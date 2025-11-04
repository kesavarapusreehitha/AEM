package com.adobe.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "vbnm", description = "ghjk")

public @interface user {
    @AttributeDefinition(name = "bnnnn", description = "hjjk", type = AttributeType.STRING)
    public String getSchedularnameuserid();

    @AttributeDefinition(name = "hjklkl", description = "fggmg", type = AttributeType.STRING)
    public String getcronexpression();

    @AttributeDefinition(name = "nnkk", description = "hkmk", type = AttributeType.BOOLEAN)
    public boolean getiscanrunconcurrent();

    @AttributeDefinition(name = "kkkkkkkkk", description = "bnm", type = AttributeType.BOOLEAN)
    public boolean isenabled();


}
