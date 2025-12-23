package com.api.framework.facade;

import com.api.framework.client.PlayerApiClient;
import com.api.framework.model.*;
import com.api.framework.specs.RequestSpecificationFactory;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import java.util.Map;


public class PlayerFacade {

    @Step("CREATE player")
    public static PlayerCreateResponse createPlayer(String editorLogin, Map<String, Object> queryParams) {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addRequestSpecification(RequestSpecificationFactory.getDefaultRequestSpecification())
                .addPathParam("editor", editorLogin)
                .addQueryParams(queryParams)
                .build();

        return PlayerApiClient.createPlayer(requestSpecification)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response()
                .as(PlayerCreateResponse.class);
    }

    @Step("CREATE player")
    public static ValidatableResponse createPlayerWithValidatableResponse(String editorLogin, Map<String, Object> queryParams) {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addRequestSpecification(RequestSpecificationFactory.getDefaultRequestSpecification())
                .addPathParam("editor", editorLogin)
                .addQueryParams(queryParams)
                .build();

        return PlayerApiClient.createPlayer(requestSpecification)
                .then();

    }

    @Step("DELETE player by id")
    public static void deletePlayerById(String editorLogin, Integer id) {
        PlayerDeleteRequest payload = new PlayerDeleteRequest(id);

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addRequestSpecification(RequestSpecificationFactory.getDefaultRequestSpecification())
                .addPathParam("editor", editorLogin)
                .setBody(payload)
                .build();

        PlayerApiClient
                .deletePlayer(requestSpecification)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Step("DELETE player by id")
    public static void deletePlayerById(String editorLogin, Integer id, Integer statusCode) {
        PlayerDeleteRequest payload = new PlayerDeleteRequest(id);

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addRequestSpecification(RequestSpecificationFactory.getDefaultRequestSpecification())
                .addPathParam("editor", editorLogin)
                .setBody(payload)
                .build();

        PlayerApiClient
                .deletePlayer(requestSpecification)
                .then()
                .statusCode(statusCode);
    }

    @Step("GET player by id")
    public static PlayerGetByPlayerIdResponse getPlayerById(Integer id) {
        PlayerGetByPlayerIdRequest payload = new PlayerGetByPlayerIdRequest(id);

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addRequestSpecification(RequestSpecificationFactory.getDefaultRequestSpecification())
                .setBody(payload)
                .build();

        return PlayerApiClient
                .getPlayerById(requestSpecification)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response()
                .as(PlayerGetByPlayerIdResponse.class);
    }

    @Step("GET player by id")
    public static ValidatableResponse getPlayerByIdAsValidatableResponse(Integer id) {
        PlayerGetByPlayerIdRequest payload = new PlayerGetByPlayerIdRequest(id);

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addRequestSpecification(RequestSpecificationFactory.getDefaultRequestSpecification())
                .setBody(payload)
                .build();

        return PlayerApiClient
                .getPlayerById(requestSpecification)
                .then();
    }

    @Step("GET all players")
    public static PlayerGetAllResponse getAllPlayers() {

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addRequestSpecification(RequestSpecificationFactory.getDefaultRequestSpecification())
                .build();

        return PlayerApiClient
                .getAllPlayer(requestSpecification)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response()
                .as(PlayerGetAllResponse.class);
    }

    @Step("GET all players")
    public static ValidatableResponse getAllPlayersAsValidatableResponse() {

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addRequestSpecification(RequestSpecificationFactory.getDefaultRequestSpecification())
                .build();

        return PlayerApiClient
                .getAllPlayer(requestSpecification)
                .then();
    }

    @Step("PATCH player")
    public static PlayerUpdateResponse updatePlayer(String editorLogin, Integer id, PlayerUpdateRequest payload) {

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addRequestSpecification(RequestSpecificationFactory.getDefaultRequestSpecification())
                .addPathParam("editor", editorLogin)
                .addPathParam("id", id)
                .setBody(payload)
                .build();

        return PlayerApiClient
                .updatePlayer(requestSpecification)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response()
                .as(PlayerUpdateResponse.class);
    }
}

