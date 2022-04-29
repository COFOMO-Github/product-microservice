package com.cofomo.product.microservice.test.cucumber.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.hc.core5.http.HttpResponse;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductDeleteDefs extends SpringIntegrationTest {

    HttpResponse response;
    HttpResponse response2;

    @Given("on a bien un produit {word}")
    public void givenWeHaveOneSquelette(String fileName) throws IOException, ParseException {
        executePost( fileName);
    }

    @When("l'utilisateur fait un appel DELETE {int}")
    public void deleteRestApiTest(int code) throws IOException {
        response = executeDelete(code);
    }

    @Then("le produit en question doit etre supprimé {int}")
    public void squeletteShouldBeDeleted(int code) {
        assertEquals(code, response.getCode());
    }

    /**
     *
     */

    @When("l'utilisateur supprime un produit non existante {int}")
    public void getRestApiTestGetNotFOundWhen(int code) throws IOException {
        response2 = executeDeleteNotFound(code);
    }

    @Then("une exception est generée {int}")
    public void getRestApiTestGetNotFOundThen(int code) {
        assertEquals(code, response2.getCode());
    }
}
