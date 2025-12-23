package com.api.framework.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class PlayerApiClient {
    public static Response getPlayerById(RequestSpecification specification) {
        return RestAssured.given()
                .spec(specification)
                .request()
                .when()
                .post("/player/get"); //get with POST not Rest
    }

    public static Response getAllPlayer(RequestSpecification specification) {
        return RestAssured.given()
                .spec(specification)
                .request()
                .when()
                .get("/player/get/all");
    }

    public static Response updatePlayer(RequestSpecification specification) {
        return RestAssured.given()
                .spec(specification)
                .request()
                .when()
                .patch("/player/update/{editor}/{id}");
    }

    public static Response deletePlayer(RequestSpecification specification) {
        return RestAssured.given()
                .spec(specification)
                .request()
                .when()
                .delete("/player/delete/{editor}");
    }

    public static Response createPlayer(RequestSpecification specification) {
        return RestAssured.given()
                .spec(specification)
                .request()
                .when()
                .get("/player/create/{editor}"); //create with GET not Rest
    }
}

