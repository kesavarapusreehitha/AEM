package com.adobe.mysite01.core.listeners;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = EventHandler.class, immediate = true, property = {
        EventConstants.EVENT_TOPIC + "=com/day/cq/replication",
        EventConstants.EVENT_FILTER + "=(type=*)"
})
public class PagePublishUnpublishListener implements EventHandler {

    private static final Logger log = LoggerFactory.getLogger(PagePublishUnpublishListener.class);

    @Override
    public void handleEvent(Event event) {
        String path = (String) event.getProperty("path");
        String type = (String) event.getProperty("type");

        if (type != null && path != null) {
            if (type.equalsIgnoreCase("ACTIVATE")) {
                log.info(" Page Published: {}", path);
            } else if (type.equalsIgnoreCase("DEACTIVATE")) {
                log.info(" Page Unpublished: {}", path);
            }
        }
    }
}