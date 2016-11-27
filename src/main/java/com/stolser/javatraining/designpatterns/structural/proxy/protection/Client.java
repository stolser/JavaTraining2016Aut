package com.stolser.javatraining.designpatterns.structural.proxy.protection;

import com.stolser.javatraining.designpatterns.structural.proxy.protection.entity.Employee;
import com.stolser.javatraining.designpatterns.structural.proxy.protection.entity.Owner;
import com.stolser.javatraining.designpatterns.structural.proxy.protection.proxy.ReportGeneratorProtectionProxy;
import com.stolser.javatraining.designpatterns.structural.proxy.protection.proxy.ReportGeneratorProxy;

public class Client {
    public static void main(String[] args) {
        Owner owner = new Owner();
        ReportGeneratorProxy reportGenerator = new ReportGeneratorProtectionProxy(owner);
        owner.setReportGenerator(reportGenerator);

        Employee employee = new Employee();
        reportGenerator = new ReportGeneratorProtectionProxy(employee);
        employee.setReportGenerator(reportGenerator);

        System.out.println("For owner:");
        System.out.println(owner.generateDailyReport());
        System.out.println("For employee:");
        System.out.println(employee.generateDailyReport());
    }
}
