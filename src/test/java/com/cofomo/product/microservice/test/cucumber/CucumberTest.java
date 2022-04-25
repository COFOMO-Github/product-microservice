package com.cofomo.product.microservice.test.cucumber;

import com.cofomo.product.microservice.test.cucumber.definitions.SpringIntegrationTest;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(glue = "com.cofomo.product.microservice",features = "src/test/resources/cucumber/features")
public class CucumberTest extends SpringIntegrationTest {

}
