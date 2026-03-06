package com.aem.geeks.core.workflows;

import java.util.Collections;
import java.util.Map;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;

@Component(service = WorkflowProcess.class, property = { "process.label=Custom Page Owner Emailer" })
public class Custom implements WorkflowProcess {

    private static final Logger LOG = LoggerFactory.getLogger(Custom.class);

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Reference
    private MessageGatewayService messageGatewayService;

    private static final String SUBSERVICE = "workflow-sub";

    @Override
    public void execute(WorkItem workItem, WorkflowSession wfSession, MetaDataMap args) throws WorkflowException {
        String payload = workItem.getWorkflowData().getPayload().toString();
        ResourceResolver resolver = null;

        try {
            Map<String, Object> authInfo = Collections.singletonMap(
                    ResourceResolverFactory.SUBSERVICE, (Object) SUBSERVICE);
            resolver = resolverFactory.getServiceResourceResolver(authInfo);

            String pageContentPath = payload.endsWith("/jcr:content") ? payload : payload + "/jcr:content";
            Resource pageContent = resolver.getResource(pageContentPath);

            if (pageContent == null) {
                LOG.warn("Payload content not found: {}", pageContentPath);
                return;
            }

            String pageOwner = pageContent.getValueMap().get("pageOwner", String.class);
            if (pageOwner == null) {
                LOG.info("pageOwner not set for {}", payload);
                return;
            }

            String to;
            switch (pageOwner.toLowerCase()) {
                case "marketing":
                    to = "marketing@company.com";
                    break;
                case "content":
                    to = "content@company.com";
                    break;
                default:
                    to = "webmaster@company.com";
                    break;
            }

            // ✅ Create email
            Email email = new SimpleEmail();
            email.setSubject("Page Published: " + payload);
            email.setMsg("Page " + payload + " was published. pageOwner = " + pageOwner);
            email.addTo(to);
            email.setFrom("no-reply@company.com", "AEM Workflow Notification");

            // ✅ Get gateway and send
            MessageGateway<Email> gateway = messageGatewayService.getGateway(SimpleEmail.class);
            if (gateway != null) {
                gateway.send((Email) email);
                LOG.info("✅ Email sent successfully to {}", to);
            } else {
                LOG.error(" MessageGateway is null — check AEM mail service configuration.");
            }

        } catch (Exception e) {
            LOG.error("Failed in Custom Workflow for payload {}: {}", payload, e.getMessage(), e);
            throw new WorkflowException(e);
        } finally {
            if (resolver != null && resolver.isLive()) {
                resolver.close();
            }
        }
    }
}
