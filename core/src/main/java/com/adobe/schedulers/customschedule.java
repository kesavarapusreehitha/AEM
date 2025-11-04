package com.adobe.schedulers;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.models.mysite01.core.osgi.configuration;

@Component(service = customschedule.class, immediate = true)
@Designate(ocd = configuration.class)
public class customschedule implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(customschedule.class);
    private Scheduler schedule;

    public void schedule(configuration config) {
        if (config.isenabled()) {
            ScheduleOptions os = schedule.EXPR(config.getcronexpression());
            os.name(config.getSchedularname());
            os.canRunConcurrently(config.iscanrunconcurrent());
            schedule.schedule(config, os);
            
        }
    }

    @Activate
    @Modified
    public void activate(configuration config) {
        log.info("schedule is activated");
    }

    @Deactivate
    public void deactivate(configuration config) {

        log.info("schedule is deactivated");
        schedule.unschedule(config.getSchedularname());

    }

    @Override
    public void run() {
        log.info("schedule is done");
    }

}
