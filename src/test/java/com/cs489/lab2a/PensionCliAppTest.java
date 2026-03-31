package com.cs489.lab2a;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PensionCliAppTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    void employeesCommandPrintsJsonArray() throws Exception {
        JsonNode employees = readJson(runMain("employees"));

        assertTrue(employees.isArray());
        assertEquals(10, employees.size());
        assertEquals("Carly", employees.get(0).path("firstName").asText());
        assertEquals("SM2307", employees.get(0).path("pensionPlan").path("planReferenceNumber").asText());
        assertEquals("Liam", employees.get(9).path("firstName").asText());
        assertEquals("LB8801", employees.get(9).path("pensionPlan").path("planReferenceNumber").asText());
    }

    @Test
    void upcomingEnrolleesCommandPrintsQualifyingEmployee() throws Exception {
        JsonNode employees = readJson(runMain("upcoming-enrollees"));

        assertTrue(employees.isArray());
        assertEquals(2, employees.size());
        assertEquals("Robert", employees.get(0).path("firstName").asText());
        assertEquals("May", employees.get(1).path("firstName").asText());
        assertFalse(employees.get(0).has("pensionPlan"));
        assertFalse(employees.get(1).has("pensionPlan"));
    }

    @Test
    void bothCommandPrintsEmployeesAndUpcomingEnrollees() throws Exception {
        JsonNode payload = readJson(runMain("both"));

        assertTrue(payload.isObject());
        assertEquals(10, payload.path("employees").size());
        assertEquals(2, payload.path("upcomingEnrollees").size());
        assertEquals("Carly", payload.path("employees").get(0).path("firstName").asText());
        assertEquals("Robert", payload.path("upcomingEnrollees").get(0).path("firstName").asText());
    }

    private String runMain(String... args) throws Exception {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (PrintStream capturing = new PrintStream(out, true, StandardCharsets.UTF_8)) {
            System.setOut(capturing);
            PensionCliApp.main(args);
        } finally {
            System.setOut(originalOut);
        }
        return out.toString(StandardCharsets.UTF_8);
    }

    private JsonNode readJson(String output) throws Exception {
        return MAPPER.readTree(output);
    }
}

