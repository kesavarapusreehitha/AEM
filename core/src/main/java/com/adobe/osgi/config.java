package com.adobe.osgi;

import org.apache.commons.collections4.Get;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.mysite01.core.schedulers.user;

@Component(service = config.class, immediate = true)
@Designate(ocd = user.class)

public class config implements Runnable {

    private final static Logger Log = LoggerFactory.getLogger("config.class");
    private static final boolean iscanrunconcurrent = false;
    private Scheduler use;

    public void usee(user a) {
        if (a.isenabled()) {
            ScheduleOptions os = use.EXPR(a.getcronexpression());
            os.name(a.getSchedularnameuserid());
            os.canRunConcurrently(iscanrunconcurrent);
            use.schedule(a, os);
        }
    }

    @Activate
    public void activate(user a) {
        this.usee(a);
        Log.info("data is coming");
    }

    @Deactivate
    public void deactivate(user a) {
        Log.info("data is not scheduled");
        use.unschedule(a.getSchedularnameuserid());

    }

    @Override
    public void run() {
        Log.info("data is runnable");
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

}
