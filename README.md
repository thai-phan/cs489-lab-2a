# CS489 Lab 2A - Employee Pension CLI

A minimal Java CLI application for managing seeded employee pension data and printing the required JSON reports.

## Requirements covered

- JSON list of all employees, including pension plan data when present
- JSON quarterly upcoming enrollees report
- In-memory seeded data matching the provided table
- Sorting rules from the assignment

## Run

```bash
./gradlew test
```

Print all employees:

```bash
./gradlew run --args=employees
```

Print the quarterly upcoming enrollees report:

```bash
./gradlew run --args=upcoming-enrollees
```

Print both reports in one JSON object:

```bash
./gradlew run --args=both
```

