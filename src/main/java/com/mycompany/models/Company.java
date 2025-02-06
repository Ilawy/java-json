package com.mycompany.models;

import java.util.ArrayList;


public class Company {
    String name;
    String location;
    ArrayList<Employee> employees;

    

    public String getName(){
        return this.name;
    }
    public String getLocation(){
        return this.location;
    }

    public Company(){}

    public Company(String name, String location, ArrayList<Employee> employees){
        this.name = name;
        this.location = location;
        this.employees = employees;
    }
    public ArrayList<Employee> getEmployees() {
        return employees;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    
}
