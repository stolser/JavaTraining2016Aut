package com.stolser.javatraining.designpatterns.structural.proxy.virtual.entity;

public class Employee {
    private String employeeName;
    private double employeeSalary;
    private String employeeDesignation;

    public Employee(String employeeName, double employeeSalary, String employeeDesignation) {
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;
        this.employeeDesignation = employeeDesignation;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public double getEmployeeSalary() {
        return employeeSalary;
    }

    public String getEmployeeDesignation() {
        return employeeDesignation;
    }

    public String toString() {
        return String.format("Employee Name: %s, EmployeeDesignation: %s, Employee Salary: %s",
                employeeName, employeeDesignation, employeeSalary);
    }
}
