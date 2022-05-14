package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(publish = true,
        monochrome = true,
        plugin = {"pretty"},
        features="src/test/java/features/TeledocChallenge.feature",
        glue={"stepDefinitions"},
        tags= "@User"
)

public class Runner
{

}
