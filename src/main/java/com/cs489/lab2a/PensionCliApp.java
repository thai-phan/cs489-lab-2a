package com.cs489.lab2a;

import com.cs489.lab2a.data.InMemoryEmployeeRepository;
import com.cs489.lab2a.service.PensionReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.Clock;
import java.util.LinkedHashMap;
import java.util.Map;

public class PensionCliApp {
    private static final ObjectMapper MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    public static void main(String[] args) throws Exception {
        PensionReportService service = new PensionReportService(
                new InMemoryEmployeeRepository(),
                Clock.systemDefaultZone()
        );

        if (args.length == 0) {
            printJson(service.getAllEmployees());
            return;
        }

        switch (args[0]) {
            case "employees" -> printJson(service.getAllEmployees());
            case "upcoming-enrollees" -> printJson(service.getQuarterlyUpcomingEnrollees());
            case "both" -> {
                Map<String, Object> payload = new LinkedHashMap<>();
                payload.put("employees", service.getAllEmployees());
                payload.put("upcomingEnrollees", service.getQuarterlyUpcomingEnrollees());
                printJson(payload);
            }
            default -> printUsage();
        }
    }

    private static void printJson(Object value) throws Exception {
        System.out.println(MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(value));
    }

    private static void printUsage() {
        System.out.println("Usage: ./gradlew run --args=employees|upcoming-enrollees|both");
    }
}

