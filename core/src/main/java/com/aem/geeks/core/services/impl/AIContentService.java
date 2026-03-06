package com.aem.geeks.core.services.impl;

import java.util.Map;

public interface AIContentService {

    Map<String, String> generateContent(String prompt, String type);

}