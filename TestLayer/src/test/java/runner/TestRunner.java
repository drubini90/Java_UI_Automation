package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(glue = "stepDefinitions", 
features = "src/test/resources/features", 
tags = "@signUp",
plugin = { "html:target/cucumber-htmlreport", "json:target/cucumber-report.json" }, 
monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests{
	

}
