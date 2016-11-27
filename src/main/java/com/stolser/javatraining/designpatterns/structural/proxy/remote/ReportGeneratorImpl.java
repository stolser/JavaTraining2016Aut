package com.stolser.javatraining.designpatterns.structural.proxy.remote;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class ReportGeneratorImpl extends UnicastRemoteObject implements ReportGenerator {
    private static final long serialVersionUID = 777L;

    public ReportGeneratorImpl() throws RemoteException {
    }

    @Override
    public String generateDailyReport() throws RemoteException {
        StringBuilder sb = new StringBuilder();
        sb.append("********************Location X Daily Report********************");
        sb.append("\\n Location ID: 012");
        sb.append(String.format("\\n Today’s Date: %s", new Date()));
        sb.append("\\n Total Pizza’s Sell: 112");
        sb.append("\\n Total Price: $2534");
        sb.append("\\n Net Profit: $1985");
        sb.append("\\n***************************************************************");
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            ReportGenerator reportGenerator = new ReportGeneratorImpl();
            Naming.rebind("PizzaCoRemoteGenerator", reportGenerator);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}