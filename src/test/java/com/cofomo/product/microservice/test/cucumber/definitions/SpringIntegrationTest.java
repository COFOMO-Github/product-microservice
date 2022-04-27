package com.cofomo.product.microservice.test.cucumber.definitions;

import com.cofomo.product.microservice.ProductMicroserviceApplication;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

import static com.cofomo.product.microservice.utils.Constants.JSON_TEST_DATA_FILE_PATH;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductMicroserviceApplication.class)
public class SpringIntegrationTest {

    static String excpectedValue;

    WireMockServer wireMockServer = new WireMockServer(options().dynamicPort());
    CloseableHttpClient httpClient = HttpClients.createDefault();

    HttpResponse executeGet(String filename,int code) throws IOException, ParseException {
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
        wireMockServer.stubFor(
                get(urlEqualTo("/api/v1/product/"+code))
                        .willReturn(
                                aResponse().withBody(
                                        retrieveFromFile(filename)))
        );
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://localhost:" + wireMockServer.port() + "/api/v1/product/"+code);
        HttpResponse httpResponse = httpClient.execute(request);
        wireMockServer.stop();
        return httpResponse;
    }

    HttpResponse executeGetAll(String filename) throws IOException, ParseException {
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
        wireMockServer.stubFor(
                get(urlEqualTo("/api/v1/product/list"))
                        .willReturn(
                                aResponse().withBody(
                                        retrieveFromFiletoList(filename)))
        );
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://localhost:" + wireMockServer.port() + "/api/v1/product/list");
        HttpResponse httpResponse = httpClient.execute(request);
        wireMockServer.stop();
        return httpResponse;
    }

    HttpResponse executeGetVide(String filename) throws IOException, ParseException {
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
        wireMockServer.stubFor(
                get(urlEqualTo("/api/v1/product/1"))
                        .willReturn(
                                aResponse().withBody(
                                        retrieveFromFiletoList(filename)))
        );
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://localhost:" + wireMockServer.port() + "/api/v1/product/5");
        HttpResponse httpResponse = httpClient.execute(request);
        wireMockServer.stop();
        return httpResponse;
    }

    HttpResponse executeDelete(int code) throws IOException {
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
        wireMockServer.stubFor(
                delete(urlEqualTo("/api/v1/product/delete/"+code))
                        .willReturn(
                                aResponse().withBody(""))
        );
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete request = new HttpDelete("http://localhost:" + wireMockServer.port() + "/api/v1/product/delete/"+code);
        HttpResponse httpResponse = httpClient.execute(request);
        wireMockServer.stop();
        return httpResponse;
    }

    HttpResponse executeGetNotFound(String filename,int code) throws IOException, ParseException {
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
        wireMockServer.stubFor(
                get(urlEqualTo("/api/v1/product/1"))
                        .willReturn(
                                aResponse().withBody(
                                        retrieveFromFile(filename)))
        );
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://localhost:" + wireMockServer.port() + "/api/v1/product/"+code);
        HttpResponse httpResponse = httpClient.execute(request);
        wireMockServer.stop();
        return httpResponse;
    }

    HttpResponse executeDeleteNotFound(int code) throws IOException {
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
        wireMockServer.stubFor(
                delete(urlEqualTo("/api/v1/product/delete/1"))
                        .willReturn(
                                aResponse().withBody(""))
        );
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete request = new HttpDelete("http://localhost:" + wireMockServer.port() + "/api/v1/product/delete/"+code);
        HttpResponse httpResponse = httpClient.execute(request);
        wireMockServer.stop();
        return httpResponse;
    }

    HttpResponse executePost(String entree) throws IOException, ParseException {
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
        wireMockServer.stubFor(
                post(urlEqualTo("/api/v1/product/"))
                        .willReturn(
                                aResponse().withBody(
                                        retrieveFromFile("sortie/payloadSortie_attendu.json")))
        );
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost("http://localhost:" + wireMockServer.port() + "/api/v1/product/");
        request.setEntity(new StringEntity(retrieveFromFile(entree)));
        HttpResponse httpResponse = httpClient.execute(request);
        wireMockServer.stop();
        return httpResponse;
    }

    public String retrieveFromFiletoList(String fileName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(JSON_TEST_DATA_FILE_PATH + fileName));
        JSONArray jsonObject = (JSONArray) obj;
        return jsonObject.toString();
    }

    public String retrieveFromFile(String fileName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(JSON_TEST_DATA_FILE_PATH + fileName));
        JSONObject jsonObject = (JSONObject) obj;
        return mapJsonToSquelete(jsonObject).toString();
    }

    public void retrieveExpected(String fileName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(JSON_TEST_DATA_FILE_PATH + fileName));
        JSONObject jsonObject = (JSONObject) obj;
        excpectedValue = mapJsonToSquelete(jsonObject).toString();
    }

    private Product mapJsonToSquelete(JSONObject jsonObject) {
        Product product = new Product();
        product.setId(jsonObject.get("id").toString());
        product.setName(jsonObject.get("name").toString());
        product.setPrice(BigDecimal.valueOf(Double.parseDouble(jsonObject.get("price").toString())));
        return product;
    }


}
