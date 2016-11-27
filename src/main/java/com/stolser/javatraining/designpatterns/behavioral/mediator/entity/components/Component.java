package com.stolser.javatraining.designpatterns.behavioral.mediator.entity.components;

import com.stolser.javatraining.designpatterns.behavioral.mediator.mediator.MachineMediator;

public interface Component {
    void setMediator(MachineMediator mediator);
}
