package com.tomtom.flow.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RemoveUnnecessaryVariables implements ExecutionListener {

    @Value("${variablesToRemove}")
    private String variablesToRemove;


    @Override
    public void notify(final DelegateExecution execution) {
        final String[] variables = variablesToRemove.split(",");
        for (final String variable : variables) {
            execution.removeVariable(variable.trim());
        }
    }
}
