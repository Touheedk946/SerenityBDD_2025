package com.example.api.employeemodals.reqeusts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(setterPrefix = "set")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private String name;
    private int age;
    private String jobTitle;
    private String department;
}
