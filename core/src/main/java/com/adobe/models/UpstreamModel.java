// package com.adobe.mysite01.core.models;

// import org.apache.sling.api.resource.Resource;
// import org.apache.sling.api.resource.ValueMap;
// import org.apache.sling.models.annotations.DefaultInjectionStrategy;
// import org.apache.sling.models.annotations.Model;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// import java.util.ArrayList;
// import java.util.Iterator;
// import java.util.List;

// @Model(adaptables = Resource.class, defaultInjectionStrategy =
// DefaultInjectionStrategy.OPTIONAL)
// public class UpstreamModel {

// private static final Logger log =
// LoggerFactory.getLogger(UpstreamModel.class);

// private final List<String> missingFields = new ArrayList<>();

// public UpstreamModel(Resource resource) {
// ValueMap properties = resource.getValueMap();
// Iterator<String> keys = properties.keySet().iterator();
// int authoredCount = 0;

// while (keys.hasNext()) {
// String key = keys.next();
// Object value = properties.get(key);
// if (value == null || value.toString().trim().isEmpty()) {
// missingFields.add(key);
// } else {
// authoredCount++;
// }
// }

// if (authoredCount == 0) {
// log.warn(" None of the fields are authored in the cq:dialog.");
// } else if (!missingFields.isEmpty()) {
// log.warn(" {} field(s) are not authored in the cq:dialog: {}",
// missingFields.size(), missingFields);
// } else {
// log.info("All fields are properly authored.");
// }
// }

// public List<String> getMissingFields() {
// return missingFields;
// }
// }
