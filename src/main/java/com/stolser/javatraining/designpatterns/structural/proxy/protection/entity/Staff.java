package com.stolser.javatraining.designpatterns.structural.proxy.protection.entity;

import com.stolser.javatraining.designpatterns.structural.proxy.protection.proxy.ReportGeneratorProxy;

public interface Staff {
    boolean isOwner();

    void setReportGenerator(ReportGeneratorProxy reportGenerator);
}
