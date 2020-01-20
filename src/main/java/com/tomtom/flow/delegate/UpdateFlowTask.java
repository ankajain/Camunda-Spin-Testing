package com.tomtom.flow.delegate;

import com.google.gson.JsonObject;
import com.tomtom.flow.client.FlowClient;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class UpdateFlowTask implements JavaDelegate {

    private final FlowClient flowClient;

    public UpdateFlowTask(final FlowClient flowClient) {
        this.flowClient = flowClient;
    }

    @Override
    public void execute(final DelegateExecution execution) {
        final Long taskId = (Long) execution.getVariable("taskId");
        final String triageStatus = (String) execution.getVariable("triageStatus");
        final String triageSubStatus = (String) execution.getVariable("triageSubStatus");
        final String triageDescription = (String) execution.getVariable("triageDescription");
        execution.setVariable("status", triageStatus);
        execution.setVariable("subStatus", triageSubStatus);
        execution.removeVariable("triageStatus");
        execution.removeVariable("triageSubStatus");
        execution.removeVariable("triageDescription");
        final JsonObject taskStatusBody = new JsonObject();
        taskStatusBody.addProperty("description", triageDescription);
        taskStatusBody.addProperty("status", triageStatus);
        taskStatusBody.addProperty("subStatus", triageSubStatus);
        taskStatusBody.addProperty("taskId", taskId);
        flowClient.updateTaskStatus(taskStatusBody.toString());
    }
}
