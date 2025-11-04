package com.adobe.mysite01.core.handler;

import com.day.cq.replication.ReplicationAction;
import com.day.cq.replication.ReplicationActionType;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = EventHandler.class, immediate = true, property = {
    EventConstants.EVENT_TOPIC + "=" + ReplicationAction.EVENT_TOPIC })
public class PulishPageEventHandlerPath implements EventHandler {

  private static final Logger LOG = LoggerFactory.getLogger(PulishPageEventHandlerPath.class);

  @Reference
  private ResourceResolverFactory resourceResolverFactory;

  @Override
  public void handleEvent(Event event) {
    try {
      ReplicationAction replicationAction = ReplicationAction.fromEvent(event);

      if (replicationAction != null && ReplicationActionType.ACTIVATE.equals(replicationAction.getType())) {
        // Log the path of the published page
        String publishedPagePath = replicationAction.getPath();
        LOG.info("Page published: {}", publishedPagePath);

      }
    } catch (Exception e) {
      LOG.error("Error in handling replication event: {}", e.getMessage());
    }
  }
}
