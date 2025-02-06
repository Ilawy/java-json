package com.mycompany.models;

public class Employee {
    public int id;
    public String name;
    public int age;
    public String position;
    public int salary;
    public EmployeeContact contact;

    public Employee(){}
    public Employee(int id, String name, int age, String position, int salary, String email, String phone){
        this.id = id;
        this.name = name;
        this.age = age;
        this.position = position;
        this.salary = salary;
        this.contact = new EmployeeContact(email, phone);
    }

    public int getId(){
        return this.id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }

    public int getSalary() {
        return salary;
    }

    public EmployeeContact getContact() {
        return contact;
    }
    
}
