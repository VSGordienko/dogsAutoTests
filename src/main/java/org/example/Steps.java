package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.util.PojoMarker;

import static org.example.util.TestConfiguration.*;
import static org.hamcrest.Matchers.equalTo;

public class Steps {
    private static final Logger log = LogManager.getLogger(Steps.class);

    private static final String URL = getDogApiUrl();
    private static final String KEY = getDogApiKey();
    private static final String VALUE = getDogApiValue();

    public static void loggingSetup() {
        RestAssured.requestSpecification = new RequestSpecBuilder().
                setBaseUri("api").
                setContentType(ContentType.JSON).
                build().
                log().all();
    }

    public static ValidatableResponse getDataFromResource(String pathSegment) {
        log.info("The user receives data through {} endpoint", pathSegment);
        loggingSetup();
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .header(KEY, VALUE).when()
                .get(URL + pathSegment).then().log().all();
    }

    public static <T extends PojoMarker> ValidatableResponse postDataOnResource(String pathSegment, T responseTemplate) {
        log.info("The user sends data through {} endpoint", pathSegment);
        loggingSetup();
        return RestAssured.given().body(responseTemplate)
                .contentType(ContentType.JSON)
                .header(KEY, VALUE).when()
                .post(URL + pathSegment).then().log().all();
    }

    public static ValidatableResponse deleteDataFromResource(String pathSegment) {
        log.info("The user deletes data through {} endpoint", pathSegment);
        loggingSetup();
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .header(KEY, VALUE).when()
                .delete(URL + pathSegment)
                .then().log().all();
    }

    public static void checkThatBodyIsEmpty(String pathSegment) {
        log.info("The user checks that there is no data at {} endpoint", pathSegment);
        loggingSetup();
        getDataFromResource(pathSegment)
                .statusCode(200)
                .body(equalTo("[]"));
    }
}
