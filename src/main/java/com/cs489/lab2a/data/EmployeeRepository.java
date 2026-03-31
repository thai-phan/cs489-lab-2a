package com.cs489.lab2a.data;

import com.cs489.lab2a.model.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> findAll();
}

