package com.aem.geeks.core.services.impl;

import org.osgi.service.component.annotations.ConfigurationPolicy;

import com.aem.geeks.core.services.DemoService;
import com.aem.geeks.core.services.DemoServiceB;
import com.aem.geeks.core.services.OSGiFactoryConfig;

public @interface Component {

    Class<DemoServiceB> service();

    boolean immediate();

    ConfigurationPolicy configurationPolicy();

}
