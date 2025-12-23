package com.api.framework.tests;

import com.api.framework.facade.PlayerFacade;
import com.api.framework.utils.SchemaValidator;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlayerControllerSchemaTest {

    @Test(description = "Validate get all player response schema")
    @Severity(SeverityLevel.MINOR)
    public void getAllPlayers() {
        ValidatableResponse allPlayers = PlayerFacade.getAllPlayersAsValidatableResponse();
        boolean isValid = SchemaValidator.validateSchema(allPlayers.extract().response(), "schemas/player-list-schema.json");
        Assert.assertTrue(isValid, "Schema validation failed for player list response");
    }
}
