package com.stolser.javatraining.designpatterns.behavioral.mediator.entity.components;

import com.stolser.javatraining.designpatterns.behavioral.mediator.mediator.MachineMediator;

public class AbstractComponent implements Component {
    protected MachineMediator mediator;

    @Override
    public void setMediator(MachineMediator mediator) {
        this.mediator = mediator;
    }
}
