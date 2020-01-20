package com.tomtom.flow.delegate;

import com.google.gson.JsonObject;
import com.tomtom.flow.client.FlowClient;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.spin.impl.json.jackson.JacksonJsonNode;
import org.springframework.stereotype.Service;

@Service
public class UpdateFlowLead implements JavaDelegate {

    private final FlowClient flowClient;

    public UpdateFlowLead(final FlowClient flowClient) {
        this.flowClient = flowClient;
    }

    @Override
    public void execute(final DelegateExecution execution) {
        final Long leadId = (Long) execution.getVariable("leadId");
        final String status = (String) execution.getVariable("status");
        final String subStatus = (String) execution.getVariable("subStatus");
        final String description = (String) execution.getVariable("description");
        final String targetAction = (String) execution.getVariable("targetAction");
        final String additionalJsonInfoObject = ((JacksonJsonNode) execution.getVariable("additionalJsonInfoObject")).toString();
        final JsonObject leadStatusBody = new JsonObject();
        final JSONObject jsonObject = new JSONObject();
        leadStatusBody.addProperty("leadId", leadId);
        leadStatusBody.addProperty("status", status);
        leadStatusBody.addProperty("subStatus", subStatus);
        leadStatusBody.addProperty("description", description);
        leadStatusBody.addProperty("targetAction", targetAction);
        leadStatusBody.addProperty("additionalJsonInfoObject", additionalJsonInfoObject);
        flowClient.updateLeadStatusUsingLeadId(leadId, leadStatusBody.toString());
    }
}
