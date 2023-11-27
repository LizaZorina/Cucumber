package org.example.Booking;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/scenariosBooking", glue = "org.example/Booking")
public class TestRunnerBooking {
}