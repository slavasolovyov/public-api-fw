package com.api.framework.tests;

import com.api.framework.base.BaseTest;
import com.api.framework.enums.Gender;
import com.api.framework.enums.Roles;
import com.api.framework.facade.PlayerFacade;
import com.api.framework.model.*;
import com.api.framework.utils.PlayerTestDataBuilder;
import com.api.framework.utils.TestDataFactory;
import com.api.framework.utils.TokenStorage;
import io.qameta.allure.*;

import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

public class PlayerControllerPositiveTests extends BaseTest {

    @Test()
    @Severity(SeverityLevel.BLOCKER)
    @Issue("TMS-0001") //response contains null value for age, gender, role, screenName, password
    public void createPlayer() {
        String screenName = TestDataFactory.randomScreenName();
        String login = TestDataFactory.randomLogin();
        String password = TestDataFactory.randomNumericPassword(7);
        Integer age = TestDataFactory.getMaxValidAge();
        String gender = Gender.MALE.getValue();
        Map<String, Object> queryParams = PlayerTestDataBuilder.defaultPlayer()
                .withScreenName(screenName)
                .withLogin(login)
                .withPassword(password)
                .withAge(age)
                .withGender(gender)
                .withRole(Roles.USER)
                .build();
        PlayerCreateResponse response = PlayerFacade.createPlayer(TokenStorage.getToken(Roles.SUPERVISOR), queryParams);

        SoftAssert soft = new SoftAssert();
        soft.assertEquals(response.getAge(), age);
        soft.assertEquals(response.getGender(), gender);
        soft.assertNotNull(response.getId());
        soft.assertEquals(response.getRole(), Roles.USER.getValue());
        soft.assertEquals(response.getScreenName(), screenName);
        soft.assertEquals(response.getLogin(), login);
        soft.assertEquals(response.getPassword(), password);
        soft.assertAll();
    }


    @Test(dataProvider = "ageProvider", description = "Verify user can be created with valid age")
    @Severity(SeverityLevel.CRITICAL)
    public void createPlayerWithValidAge(Integer age) {
        Map<String, Object> queryParams = PlayerTestDataBuilder.defaultPlayer()
                .withAge(age)
                .build();
        PlayerCreateResponse response = PlayerFacade.createPlayer(TokenStorage.getToken(Roles.SUPERVISOR), queryParams);
        Assert.assertNotNull(response.getId());
    }

    @DataProvider(name = "ageProvider", parallel = true)
    public Object[][] ageProvider() {
        return new Object[][] {
                {17},
                {59}
        };
    }

    @Test(dataProvider = "passwordProvider", description = "Verify user can be created with valid password length")
    @Severity(SeverityLevel.CRITICAL)
    public void createPlayerWithValidPasswordLength(String password) {
        Map<String, Object> queryParams = PlayerTestDataBuilder.defaultPlayer()
                .withAge(TestDataFactory.getMaxValidAge())
                .withPassword(password)
                .build();
        PlayerCreateResponse response = PlayerFacade.createPlayer(TokenStorage.getToken(Roles.SUPERVISOR), queryParams);
        Assert.assertNotNull(response.getId());
    }

    @DataProvider(name = "passwordProvider", parallel = true)
    public Object[][] passwordProvider() {
        return new Object[][] {
                {RandomStringUtils.randomAlphabetic(7)},
                {RandomStringUtils.randomAlphabetic(15)}
        };
    }

    @Test(description = "Verify player deleted successfully")
    @Severity(SeverityLevel.CRITICAL)
    @Issue("TMS-0006") //Get deleted player by id return 200
    public void deletePlayer() {
        Map<String, Object> queryParams = PlayerTestDataBuilder.defaultPlayer().build();
        PlayerCreateResponse response = PlayerFacade.createPlayer(TokenStorage.getToken(Roles.SUPERVISOR), queryParams);
        Integer playerId = response.getId();
        PlayerFacade.deletePlayerById(TokenStorage.getToken(Roles.SUPERVISOR), response.getId());
        ValidatableResponse validatableResponse = PlayerFacade.getPlayerByIdAsValidatableResponse(playerId);
        int statusCode = validatableResponse.extract().statusCode();
        Assert.assertEquals(statusCode, HttpStatus.SC_NOT_FOUND);
    }

    @Test(description = "Verify getting by id return correct response")
    @Severity(SeverityLevel.CRITICAL)
    public void getPlayerById() {
        String screenName = TestDataFactory.randomScreenName();
        String login = TestDataFactory.randomLogin();
        String password = TestDataFactory.randomAlphabeticPassword(5);
        Integer age = TestDataFactory.getMinValidAge();
        String gender = Gender.MALE.getValue();
        String role = Roles.ADMIN.getValue();

        Map<String, Object> queryParams = PlayerTestDataBuilder.defaultPlayer()
                .withScreenName(screenName)
                .withLogin(login)
                .withPassword(password)
                .withAge(age)
                .withGender(gender)
                .withRole(role)
                .build();
        PlayerCreateResponse response = PlayerFacade.createPlayer(TokenStorage.getToken(Roles.SUPERVISOR), queryParams);
        Integer playerId = response.getId();
        PlayerGetByPlayerIdResponse playerByIdResponse = PlayerFacade.getPlayerById(playerId);

        SoftAssert soft = new SoftAssert();
        soft.assertEquals(playerByIdResponse.getAge(), age);
        soft.assertEquals(playerByIdResponse.getGender(), gender);
        soft.assertNotNull(playerByIdResponse.getId());
        soft.assertEquals(playerByIdResponse.getRole(), role);
        soft.assertEquals(playerByIdResponse.getScreenName(), screenName);
        soft.assertEquals(playerByIdResponse.getLogin(), login);
        soft.assertEquals(playerByIdResponse.getPassword(), password);
        soft.assertAll();
    }

    @Test(description = "Verify getting all players return not empty list")
    @Severity(SeverityLevel.CRITICAL)
    public void getAllPlayers() {
        PlayerGetAllResponse allPlayers = PlayerFacade.getAllPlayers();
        List<PlayerItem> players = allPlayers.getPlayers();
        Assert.assertFalse(players.isEmpty());
    }


    @Test(description = "Verify getting all players return just created player")
    @Severity(SeverityLevel.CRITICAL)
    public void createAndGetAllPlayers() {
        Map<String, Object> queryParams = PlayerTestDataBuilder.defaultPlayer()
                .withPassword(TestDataFactory.randomAlphabeticPassword(5))
                .withRole(Roles.ADMIN)
                .build();
        PlayerCreateResponse response = PlayerFacade.createPlayer(TokenStorage.getToken(Roles.SUPERVISOR), queryParams);
        Integer playerId = response.getId();
        PlayerGetAllResponse allPlayers = PlayerFacade.getAllPlayers();
        List<PlayerItem> players = allPlayers.getPlayers();
        Assert.assertListContains(players, (p)-> p.getId().equals(playerId),
                String.format("Not found player with id %s in list", playerId));
    }

    @Test(description = "Verify player update")
    @Severity(SeverityLevel.CRITICAL)
    public void updatePlayer() {
        Map<String, Object> queryParams = PlayerTestDataBuilder.defaultPlayer().build();
        PlayerCreateResponse response = PlayerFacade.createPlayer(TokenStorage.getToken(Roles.SUPERVISOR), queryParams);
        Integer playerId = response.getId();

        Integer newAge = 20;
        PlayerUpdateRequest payload = new PlayerUpdateRequest();
        payload.setAge(newAge);
        PlayerUpdateResponse responseOnUpdate = PlayerFacade.updatePlayer(TokenStorage.getToken(Roles.SUPERVISOR), playerId, payload);
        Assert.assertEquals(responseOnUpdate.getAge(), newAge);
    }

    @Test(description = "Verify player with role USER, able perform update on it's self")
    @Severity(SeverityLevel.NORMAL)
    public void playerUpdateItsSelf() {
        String login = TestDataFactory.randomLogin();
        String newGenderValue = Gender.FEMALE.getValue();

        Map<String, Object> queryParams = PlayerTestDataBuilder.defaultPlayer()
                .withLogin(login)
                .build();

        PlayerCreateResponse response = PlayerFacade.createPlayer(TokenStorage.getToken(Roles.SUPERVISOR), queryParams);
        Integer userPlayerId = response.getId();

        PlayerUpdateRequest payload = new PlayerUpdateRequest();
        payload.setGender(newGenderValue);
        PlayerUpdateResponse playerUpdateResponse = PlayerFacade.updatePlayer(login, userPlayerId, payload);

        Assert.assertEquals(playerUpdateResponse.getGender(), newGenderValue);
    }
}
