package com.tomtom.flow.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${flow-service.url}/flow", name = "flowClient")
@Component
public interface FlowClient {

    @PutMapping(value = "/task/updateTaskStatus", produces = "application/json", consumes = "application" +
            "/json")
    void updateTaskStatus(@RequestBody final String taskStatus);

    @PutMapping(value = "/lead/updateLeadStatusUsingLeadId/{leadId}",produces = "application/json", consumes = "application" +
            "/json")
    public void updateLeadStatusUsingLeadId(@PathVariable(name = "leadId") final Long leadId,
    @RequestBody final String leadStatus);
}

