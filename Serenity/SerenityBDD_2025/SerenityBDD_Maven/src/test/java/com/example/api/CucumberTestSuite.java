package com.example.api;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty", // Prints the output in a readable format in the console
                "html:target/cucumber-reports", // HTML report in the target folder
                "json:target/cucumber-reports/cucumber.json", // JSON report in the target folder
                "junit:target/cucumber-reports/cucumber.xml" // JUnit report in the target folder
        },
        features = "src/test/resources/features", // Path to your feature files
        glue = "com.example.api.steps", // Path to your step definitions
        tags = "@smoke", // Optional: specify tags to run specific tests
        monochrome = true // Clean console output (no colors)
        //strict = true // Fail the test suite if there are undefined or pending steps
)
public class CucumberTestSuite {
    // Empty class for running Cucumber tests
}