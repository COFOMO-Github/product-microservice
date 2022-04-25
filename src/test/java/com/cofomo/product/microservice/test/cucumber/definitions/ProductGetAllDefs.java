package com.cofomo.product.microservice.test.cucumber.definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.hc.core5.http.HttpResponse;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductGetAllDefs extends SpringIntegrationTest {


    HttpResponse response;

    @When("l'utilisateur fait un appel GET All {word}")
    public void getRestApiTest(String fileName) throws IOException, ParseException {
        response = executeGetAll(fileName);
    }

    @Then("le serveur retourne l'ensemble des données {int}")
    public void the_client_receives_status_code_of(int code) {
        assertEquals(code, response.getCode());
    }

    /**
     * on a aucune données
     */

    @When("l'utilisateur fait un appel GET All alors que la bdd est vide {word}")
    public void getRestApiTestGetWhen(String fileName) throws IOException, ParseException {
        response = executeGetVide(fileName);
    }

    @Then("le serveur retourne rien {int}")
    public void getRestApiTestGetThen(int code) {
        assertEquals(code, response.getCode());
    }
}
