package com.stolser.javatraining.designpatterns.structural.proxy.protection.proxy;

import com.stolser.javatraining.designpatterns.structural.proxy.protection.entity.Staff;
import com.stolser.javatraining.designpatterns.structural.proxy.remote.ReportGenerator;
import com.stolser.javatraining.designpatterns.structural.proxy.remote.ReportGeneratorImpl;

public class ReportGeneratorProtectionProxy implements ReportGeneratorProxy {
    ReportGenerator reportGenerator;
    Staff staff;

    public ReportGeneratorProtectionProxy(Staff staff) {
        this.staff = staff;
    }

    @Override
    public String generateDailyReport() {
        if (staff.isOwner()) {
            ReportGenerator reportGenerator;
            try {
//                reportGenerator = (ReportGenerator) Naming.lookup("rmi://127.0.0.1/PizzaCoRemoteGenerator");
                reportGenerator = new ReportGeneratorImpl();
                return reportGenerator.generateDailyReport();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        } else {
            return "Not Authorized to view report.";
        }
    }
}