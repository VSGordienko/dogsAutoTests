package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.example.util.PojoMarker;

import static org.example.util.TestConfiguration.*;
import static org.hamcrest.Matchers.equalTo;

public class Steps {

    private static final String URL = getDogApiUrl();
    private static final String KEY = getDogApiKey();
    private static final String VALUE = getDogApiValue();

    public static void loggingSetup(){
        RestAssured.requestSpecification = new RequestSpecBuilder().
                setBaseUri("api").
                setContentType(ContentType.JSON).
                build().
                log().all();
    }

    public static ValidatableResponse getDataFromResource(String pathSegment) {
        loggingSetup();
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .header(KEY, VALUE)
                .get(URL + pathSegment).then().log().all();
    }

    public static <T extends PojoMarker> ValidatableResponse postDataOnResource(String pathSegment, T responseTemplate) {
        loggingSetup();
        return RestAssured.given().body(responseTemplate)
                .contentType(ContentType.JSON)
                .header(KEY, VALUE)
                .post(URL + pathSegment).then().log().all();
    }

    public static ValidatableResponse deleteDataFromResource(String pathSegment) {
        loggingSetup();
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .header(KEY, VALUE)
                .delete(URL + pathSegment)
                .then().log().all();
    }

    public static void checkThatBodyIsEmpty(String pathSegment) {
        loggingSetup();
        getDataFromResource(pathSegment)
                .statusCode(200)
                .body(equalTo("[]"));
    }
}
