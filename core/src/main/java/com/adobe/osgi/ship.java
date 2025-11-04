package com.adobe.osgi;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = ship.class, enabled = true, immediate = true)
@Designate(ocd = configship.class)

public class ship {
    private static final Logger Log = LoggerFactory.getLogger("ship.class");
    private String userName;
    private int userid;

    @Activate
    public void activate(configship a) {
        update(a);
        Log.info("data");
    }

    @Deactivate
    public void deactivate(configship a) {
        Log.info("data");
    }

    @Modified
    public void modified(configship a) {
        Log.info("data");
    }

    public void update(configship a) {
        this.userName = a.userName();
        this.userid = a.userid();
        Log.info("userName{},userid{}", userName, userid);

    }

}
