package com.aem.geeks.core.listeners;

import org.osgi.service.component.annotations.Component;
import com.day.cq.mcm.emailprovider.EmailService;

import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.mcm.emailprovider.EmailService;

@Component(service = EventHandler.class, immediate = true, property = {
        EventConstants.EVENT_TOPIC + "=com/day/cq/wcm/core/page"
})
public class EmailNotificationEventListener implements EventHandler {

    private static final Logger LOG = LoggerFactory.getLogger(EmailNotificationEventListener.class);

    @Reference
    private String emailService;

    @Override
    public void handleEvent(Event event) {
        try {
            String topic = event.getTopic();
            String path = (String) event.getProperty("path");
            String user = (String) event.getProperty("user");

            String subject = "AEM Event: " + topic;
            String message =
                    "Event: " + topic + "\n" +
                    "Path: " + path + "\n" +
                    "User: " + user + "\n";

            if (emailService != null) {
;
                LOG.info("Email sent successfully!");
            } else {
                LOG.error("EmailService is NULL — cannot send email!");
            }

        } catch (Exception e) {
            LOG.error("Error handling event", e);
        }
    }
}
