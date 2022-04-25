package com.cofomo.product.microservice.test.cucumber.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.apache.hc.core5.http.HttpResponse;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@CucumberContextConfiguration
public class ProductGetDefs extends SpringIntegrationTest {

    String entreeFileName;
    HttpResponse response;

    @Given("on a un produit {word}")
    public void givenWeHaveOneProduct(String fileName) throws IOException, ParseException {
        entreeFileName = "entree/" + fileName;
        executePost(entreeFileName);

    }

    @When("l'utilisateur fait un appel GET {int}")
    public void getRestApiTest(int code) throws IOException, ParseException {
        response = executeGet(entreeFileName, code);
    }

    @Then("le serveur gere l'appel GET avec success {int}")
    public void the_client_receives_status_code_of(int code) {
        assertEquals(code, response.getCode());
    }

    /**
     *
     */

    @Given("on a un produit en entree {word}")
    public void givenWeHaveOneProduct2(String fileName) throws IOException, ParseException {
        entreeFileName = "entree/" + fileName;
        executePost(entreeFileName);
    }

    @When("l'utilisateur fait un appel GET avec id non valide {int}")
    public void getRestApiTestGetWhen(int code) throws IOException, ParseException {
        response = executeGetNotFound(entreeFileName, code);
    }

    @Then("le serveur gere l'appel GET avec Not_Found excpetion {int}")
    public void getRestApiTestGetThen(int code) {
        assertEquals(code, response.getCode());
    }


}
