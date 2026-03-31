package com.cs489.lab2a.data;

import com.cs489.lab2a.model.Employee;
import com.cs489.lab2a.model.PensionPlan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class InMemoryEmployeeRepository implements EmployeeRepository {


  private final List<Employee> employees = List.of(
      new Employee(1L, "Daniel", "Agar", LocalDate.of(2023, 1, 17), new BigDecimal("105945.50"), new PensionPlan("EX1089", null, new BigDecimal("100.00"))),
      new Employee(2L, "Benard", "Shaw", LocalDate.of(2022, 9, 3), new BigDecimal("197750.00"), new PensionPlan("", LocalDate.of(2025, 9, 3), null)),
      new Employee(3L, "Carly", "Agar", LocalDate.of(2014, 5, 16), new BigDecimal("842000.75"), new PensionPlan("SM2307", LocalDate.of(2017, 5, 17), new BigDecimal("1555.50"))),
      new Employee(7L, "Maya", "Patel", LocalDate.of(2023, 4, 1), new BigDecimal("70000.00"), null),
      new Employee(9L, "May", "Moana", LocalDate.of(2023, 5, 1), new BigDecimal("80000.00"), null),
      new Employee(8L, "Liam", "Brooks", LocalDate.of(2021, 8, 12), new BigDecimal("65000.00"), new PensionPlan("LB8801", LocalDate.of(2024, 8, 12), new BigDecimal("250.00"))),
      new Employee(4L, "Wesley", "Schneider", LocalDate.of(2023, 7, 21), new BigDecimal("74500.00"), null),
      new Employee(5L, "Anna", "Wiltord", LocalDate.of(2023, 3, 15), new BigDecimal("85750.00"), null),
      new Employee(6L, "Yosef", "Tesfalem", LocalDate.of(2024, 10, 31), new BigDecimal("100000.00"), null)
  );

  @Override
  public List<Employee> findAll() {
    return employees;
  }
}

