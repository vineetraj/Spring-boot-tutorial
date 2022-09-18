package com.raj.vineet.springboot.tutorial.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* this class is to create a custom actuator endpoint. Here we will create a management
 * endpoint where we can see which features are there & enabled for our application */
@Component
@Endpoint(id = "features")
public class FeatureEndpoint {

    private final Map<String, Feature> featureMap = new ConcurrentHashMap<>();


    public FeatureEndpoint() {
        featureMap.put("Department", new Feature(true));
        featureMap.put("Users", new Feature(false));
        featureMap.put("Authentication", new Feature(false));
    }

    //this func will send back the list of features available
    @ReadOperation
    public Map<String, Feature> features() {
        return featureMap;
    }

    @ReadOperation
    //to gather the details of particular endpoint we are selecting
    public Feature feature(@Selector String featureName) {
        return featureMap.get(featureName);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Feature {
        private Boolean isEnabled;
    }
}
