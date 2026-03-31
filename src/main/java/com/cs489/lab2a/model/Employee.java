package com.cs489.lab2a.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Employee(
    long employeeId,
    String firstName,
    String lastName,
    LocalDate employmentDate,
    BigDecimal yearlySalary,
    PensionPlan pensionPlan
) {
}

