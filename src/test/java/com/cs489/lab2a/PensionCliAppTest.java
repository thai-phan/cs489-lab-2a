package com.cs489.lab2a;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PensionCliAppTest {

    @Test
    void employeesCommandPrintsJsonArray() throws Exception {
        String output = runMain("employees");
        assertTrue(output.trim().startsWith("["));
        assertTrue(output.contains("\"firstName\" : \"Carly\""));
    }

    @Test
    void upcomingEnrolleesCommandPrintsQualifyingEmployee() throws Exception {
        String output = runMain("upcoming-enrollees");
        assertTrue(output.contains("\"firstName\" : \"Maya\""));
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
}

