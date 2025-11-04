package com.adobe.osgi;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = boat.class, enabled = true, immediate = true)
@Designate(ocd = boarconfig.class)

public class boat {
    private static final Logger LOG = LoggerFactory.getLogger("boat.class");
    private String username;

    private int userid;

    @Activate
    public void activate(boarconfig Boarconfig) {
        update(Boarconfig);
        LOG.info("data coming");
    }

    @Deactivate
    public void deactivate(boarconfig Boarconfig) {
        LOG.info("data coming");

    }

    @Modified
    public void modified(boarconfig Boarconfig) {
        LOG.info("data coming modified");

    }

    public void update(boarconfig Boarconfig) {
        this.username = Boarconfig.username();
        this.userid = Boarconfig.userid();
        LOG.info("username{}, userId{}", username, userid);

    }

}
