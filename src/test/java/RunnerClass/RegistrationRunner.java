package RunnerClass;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions
			   (
		       features = "C:\\Users\\Parthasarathy\\eclipse-workspace\\MynthraMavenCucumber\\src\\main\\java\\FeatureFiles\\ProductWrite.feature",
		       glue = {"StepDefinition"}
		       //format= { "pretty","html:target/site/cucumber-pretty","json:target/cucumber.json"},
		       //monochrome = true 
		       )	



public class RegistrationRunner
{

	

}
