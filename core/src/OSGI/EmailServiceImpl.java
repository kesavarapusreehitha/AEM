package com.aem.geeks.core.services.impl;

import com.aem.geeks.core.services.EmailService;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component(service = EmailService.class, immediate = true)
@Designate(ocd = EmailServiceImpl.Config.class)
public class EmailServiceImpl implements EmailService {

    private static final Logger LOG = LoggerFactory.getLogger(EmailServiceImpl.class);

    @ObjectClassDefinition(name = "Email Service Configuration")
    public @interface Config {
        @AttributeDefinition(name = "SMTP Host")
        String smtp_host() default "smtp.gmail.com";

        @AttributeDefinition(name = "SMTP Port")
        int smtp_port() default 587;

        @AttributeDefinition(name = "SMTP Username")
        String smtp_user() default "";

        @AttributeDefinition(name = "SMTP Password")
        String smtp_password() default "";

        @AttributeDefinition(name = "From Email")
        String from_email() default "no-reply@example.com";

        @AttributeDefinition(name = "Enable STARTTLS")
        boolean starttls() default true;

        @AttributeDefinition(name = "Default Recipient (optional)")
        String default_to() default "";
    }

    private volatile String host;
    private volatile int port;
    private volatile String username;
    private volatile String password;
    private volatile String from;
    private volatile boolean starttls;
    private volatile String defaultTo;

    private ExecutorService executor;

    @Activate
    @Modified
    protected void activate(Config config) {
        this.host = config.smtp_host();
        this.port = config.smtp_port();
        this.username = config.smtp_user();
        this.password = config.smtp_password();
        this.from = config.from_email();
        this.starttls = config.starttls();
        this.defaultTo = config.default_to();

        if (executor == null || executor.isShutdown()) {
            executor = Executors.newFixedThreadPool(2);
        }

        LOG.info("EmailService activated. Host: {}, Port: {}, From: {}", host, port, from);
    }

    @Deactivate
    protected void deactivate() {
        if (executor != null) {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
        LOG.info("EmailService deactivated");
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        final String recipient = (to == null || to.isEmpty()) ? defaultTo : to;
        if (recipient == null || recipient.isEmpty()) {
            LOG.warn("No recipient configured for email. Subject: {}", subject);
            return;
        }

        executor.submit(() -> {
            try {
                SimpleEmail email = new SimpleEmail();
                email.setHostName(host);
                email.setSmtpPort(port);
                if (username != null && !username.isEmpty()) {
                    email.setAuthentication(username, password);
                }
                email.setStartTLSEnabled(starttls);
                email.setFrom(from);
                email.addTo(recipient);
                email.setSubject(subject);
                email.setMsg(body);
                email.send();

                LOG.info("Email sent to {} with subject={}", recipient, subject);
            } catch (EmailException e) {
                LOG.error("Failed to send email to {} subject={}", recipient, subject, e);
            }
        });
    }
}
