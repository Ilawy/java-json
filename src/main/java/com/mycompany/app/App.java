/*----------------------------------------------------------------------------------------
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 *---------------------------------------------------------------------------------------*/

package com.mycompany.app;

import java.io.StringReader;
import java.util.ArrayList;

import javax.json.*;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import com.mycompany.models.Company;
import com.mycompany.models.Employee;

public class App {
    public static void main(String[] args) {
        // MARK: class to json
        ArrayList<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee(1, "Mohammed", 25, "Junior Developer", 45000, "next.mohammed.amr@gmail.com",
                "+201113006113"));
        employees.add(new Employee(1, "Ahmed", 25, "Project manager", 70000, "ahmed@gmail.com", "+201011047456"));

        Company myCompany = new Company("ITI", "Cairo, Egypt", employees);

        var employeesArray = Json.createArrayBuilder();
        employees.forEach(employee -> {
            var employeeObject = Json.createObjectBuilder();
            employeeObject.add("id", employee.getId());
            employeeObject.add("name", employee.getName());
            employeeObject.add("age", employee.getAge());
            employeeObject.add("position", employee.getPosition());
            employeeObject.add("salary", employee.getSalary());
            var contactObject = Json.createObjectBuilder().add("email", employee.getContact().getEmail()).add("phone",
                    employee.getContact().getPhone());
            employeeObject.add("contact", contactObject);
            employeesArray.add(employeeObject);
        });

        var finalObject = Json.createObjectBuilder()
                .add("name", myCompany.getName())
                .add("location", myCompany.getLocation())
                .add("employees", employeesArray);
        var rawJsonString = finalObject.build().toString();
        System.out.println(rawJsonString);

        System.out.println("---------------");
        // MARK: json to class
        
        var reader = Json.createReader(new StringReader(rawJsonString)).readObject();
        var loadedEmployees = reader.getJsonArray("employees");
        var loadedEmployeesList = new ArrayList<Employee>();
        for(int i = 0; i < loadedEmployees.size(); i++){
            var object = loadedEmployees.getJsonObject(i);
            loadedEmployeesList.add(new Employee(
                object.getInt("id"),
                object.getString("name"),
                object.getInt("age"),
                object.getString("position"),
                object.getInt("salary"),
                object.getJsonObject("contact").getString("email"),
                object.getJsonObject("contact").getString("phone")
            ));
        }
        var loadedCompany = new Company(reader.getString("name"), reader.getString("location"), loadedEmployeesList);
        System.out.println(loadedCompany.getName());
        System.out.println(loadedCompany.getLocation());
        loadedCompany.getEmployees().forEach(employee->{
            System.out.println("----------");
            System.out.println(employee.getId());
            System.out.println(employee.getName());
            System.out.println(employee.getPosition());
            System.out.println(employee.getAge());
        });

        // ------------- binding -------------
        Jsonb jsonb = JsonbBuilder.create();
        String jsonbResult = jsonb.toJson(myCompany);
        System.out.println(jsonbResult);

        System.out.println("----------------");

        Jsonb jsonb2 = JsonbBuilder.create();
        Company companyFromBinding = jsonb2.fromJson(jsonbResult, Company.class);
       
        System.out.println(companyFromBinding.getName());
        System.out.println(companyFromBinding.getLocation());
        
        System.out.println(companyFromBinding.getEmployees().get(0).getName());
        System.out.println(companyFromBinding.getEmployees().get(1).getName());
        
        System.out.println(companyFromBinding.getEmployees().get(1).getContact().getEmail());

    }
}
