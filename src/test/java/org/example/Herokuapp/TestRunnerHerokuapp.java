package org.example.Herokuapp;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/scenariosHerokuapp", glue = "org.example/Herokuapp")
public class TestRunnerHerokuapp {
}