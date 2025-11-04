package com.adobe.mysite01.core.listeners;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.replication.ReplicationAction;

@Component(
    service = EventHandler.class,
    immediate = true,
    property = {
        EventConstants.EVENT_TOPIC + "=" + ReplicationAction.EVENT_TOPIC,
        EventConstants.EVENT_FILTER + "=(type=ACTIVATE)"
    }
)
public class eventlistener implements EventHandler {

    private static final Logger log = LoggerFactory.getLogger(eventlistener.class);

    @Override
    public void handleEvent(Event event) {
        log.info("Replication event triggered");

        String[] propertyNames = event.getPropertyNames();
        for (String property : propertyNames) {
            log.info("Key: {} | Value: {}", property, event.getProperty(property));
        }
    }
}


