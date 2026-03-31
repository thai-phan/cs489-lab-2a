package com.cs489.lab2a.service;

import com.cs489.lab2a.data.EmployeeRepository;
import com.cs489.lab2a.model.Employee;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;

public class PensionReportService {
  private final EmployeeRepository employeeRepository;
  private final Clock clock;

  public PensionReportService(EmployeeRepository employeeRepository, Clock clock) {
    this.employeeRepository = employeeRepository;
    this.clock = clock;
  }

  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll().stream()
        .sorted(Comparator.comparing(Employee::yearlySalary).reversed()
            .thenComparing(Employee::lastName))
        .toList();
  }

  public List<Employee> getQuarterlyUpcomingEnrollees() {
    LocalDate today = LocalDate.now(clock);
    LocalDate nextQuarterStart = startOfNextQuarter(today);
    LocalDate nextQuarterEnd = nextQuarterStart.plusMonths(3).minusDays(1);

    return employeeRepository.findAll().stream()
        .filter(employee -> employee.pensionPlan() == null)
        .filter(employee -> employee.yearlySalary().compareTo(new java.math.BigDecimal("100000.00")) >= 0)
        .filter(employee -> {
          LocalDate anniversary = employee.employmentDate().plusYears(3);
          return !anniversary.isBefore(nextQuarterStart) && !anniversary.isAfter(nextQuarterEnd);
        })
        .sorted(Comparator.comparing(Employee::employmentDate).reversed()
            .thenComparing(Employee::lastName))
        .toList();
  }

  static LocalDate startOfNextQuarter(LocalDate today) {
    int currentQuarter = ((today.getMonthValue() - 1) / 3) + 1;
    int nextQuarter = currentQuarter == 4 ? 1 : currentQuarter + 1;
    int year = currentQuarter == 4 ? today.getYear() + 1 : today.getYear();
    Month startMonth = Month.of((nextQuarter - 1) * 3 + 1);
    return LocalDate.of(year, startMonth, 1);
  }
}

