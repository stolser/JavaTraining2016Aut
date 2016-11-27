package com.stolser.javatraining.designpatterns.structural.proxy.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ReportGenerator extends Remote {
    String generateDailyReport() throws RemoteException;
}
