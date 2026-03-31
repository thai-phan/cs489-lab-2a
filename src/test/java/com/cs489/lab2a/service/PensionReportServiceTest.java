package com.cs489.lab2a.service;

import com.cs489.lab2a.data.InMemoryEmployeeRepository;
import com.cs489.lab2a.model.Employee;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PensionReportServiceTest {

    @Test
    void allEmployeesAreSortedBySalaryDescendingThenLastNameAscending() {
        PensionReportService service = new PensionReportService(
                new InMemoryEmployeeRepository(),
                Clock.fixed(Instant.parse("2026-03-31T00:00:00Z"), ZoneOffset.UTC)
        );

        List<Employee> employees = service.getAllEmployees();

        assertEquals(List.of("Carly", "Benard", "Daniel", "Yosef", "Anna", "Wesley", "Maya", "Liam"),
                employees.stream().map(Employee::firstName).toList());
        assertNotNull(employees.get(0).pensionPlan());
        assertEquals("SM2307", employees.get(0).pensionPlan().planReferenceNumber());
    }

    @Test
    void upcomingEnrolleesIncludeTheNewlySeededEmployeeForSeededCurrentDate() {
        PensionReportService service = new PensionReportService(
                new InMemoryEmployeeRepository(),
                Clock.fixed(Instant.parse("2026-03-31T00:00:00Z"), ZoneOffset.UTC)
        );

        List<Employee> employees = service.getQuarterlyUpcomingEnrollees();

        assertEquals(1, employees.size());
        assertEquals("Maya", employees.get(0).firstName());
    }

    @Test
    void upcomingEnrolleesIncludeOnlyEmployeesWhoseThirdAnniversaryFallsInNextQuarter() {
        PensionReportService service = new PensionReportService(
                new InMemoryEmployeeRepository(),
                Clock.fixed(Instant.parse("2025-11-01T00:00:00Z"), ZoneOffset.UTC)
        );

        List<Employee> employees = service.getQuarterlyUpcomingEnrollees();

        assertEquals(1, employees.size());
        assertEquals("Anna", employees.get(0).firstName());
    }

    @Test
    void nextQuarterBoundariesHandleYearEnd() {
        LocalDate start = PensionReportService.startOfNextQuarter(LocalDate.of(2026, 12, 31));
        assertEquals(LocalDate.of(2027, 1, 1), start);
        assertEquals(LocalDate.of(2027, 3, 31), start.plusMonths(3).minusDays(1));
    }
}

