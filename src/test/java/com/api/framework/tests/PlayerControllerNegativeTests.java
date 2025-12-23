package com.api.framework.tests;

import com.api.framework.base.BaseTest;
import com.api.framework.enums.Gender;
import com.api.framework.enums.Roles;
import com.api.framework.facade.PlayerFacade;
import com.api.framework.model.PlayerCreateResponse;
import com.api.framework.utils.TestDataFactory;
import com.api.framework.utils.TokenStorage;
import io.qameta.allure.*;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class PlayerControllerNegativeTests extends BaseTest {


    @Test(description = "Verify role USER not able to create new player")
    @Severity(SeverityLevel.CRITICAL)
    public void userNotAbleToCreateNewPlayer() {
        String editorLogin = TestDataFactory.randomLogin();
        Map<String, Object> queryParams = Map.of(
                "age", TestDataFactory.getMinValidAge(),
                "gender", Gender.MALE.getValue(),
                "login", editorLogin,
                "password", TestDataFactory.randomAlphabeticPassword(7),
                "role", Roles.USER.getValue(),
                "screenName", TestDataFactory.randomScreenName());
        PlayerFacade.createPlayer(TokenStorage.getToken(Roles.SUPERVISOR), queryParams);

        queryParams = Map.of(
                "age", TestDataFactory.getMinValidAge(),
                "gender", Gender.MALE.getValue(),
                "login", TestDataFactory.randomLogin(),
                "password", TestDataFactory.randomAlphabeticPassword(7),
                "role", Roles.USER.getValue(),
                "screenName", TestDataFactory.randomScreenName());

        ValidatableResponse response = PlayerFacade.createPlayerWithValidatableResponse(editorLogin, queryParams);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, HttpStatus.SC_FORBIDDEN);
    }

    @Test(dataProvider = "ageProvider", description = "Verify age restriction")
    @Severity(SeverityLevel.CRITICAL)
    @Issue("TMS-0003") // Player with age > 59 && age < 17 allowed to be created but should not
    public void createPlayerWithInvalidAge(Integer age) {
        String screenName = TestDataFactory.randomScreenName();
        String login = TestDataFactory.randomLogin();
        String password = TestDataFactory.randomAlphabeticPassword(7);

        Map<String, Object> queryParams = Map.of(
                "age", age,
                "gender", Gender.MALE.getValue(),
                "login", login,
                "password", password,
                "role", Roles.USER.getValue(),
                "screenName", screenName);

        ValidatableResponse response = PlayerFacade.createPlayerWithValidatableResponse(TokenStorage.getToken(Roles.SUPERVISOR), queryParams);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, HttpStatus.SC_BAD_REQUEST);
    }

    @DataProvider(name = "ageProvider")
    public Object[][] ageProvider() {
        return new Object[][] {
                {16},
                {60}
        };
    }

    @Test(description = "Create player with invalid role")
    public void createPlayerWithInvalidRole() {
        String screenName = TestDataFactory.randomScreenName();
        String login = TestDataFactory.randomLogin();
        String password = TestDataFactory.randomAlphabeticPassword(7);
        String invalidRole = "Test";
        Integer age = TestDataFactory.getMinValidAge();
        Map<String, Object> queryParams = Map.of(
                "age", age,
                "gender", Gender.MALE.getValue(),
                "login", login,
                "password", password,
                "role", invalidRole,
                "screenName", screenName);

        ValidatableResponse response = PlayerFacade.createPlayerWithValidatableResponse(TokenStorage.getToken(Roles.SUPERVISOR), queryParams);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, HttpStatus.SC_BAD_REQUEST);
    }

    @Test(description = "Create player with missing password field")
    @Severity(SeverityLevel.CRITICAL)
    @Issue("TMS-0002") //missing validation on required password field
    public void createPlayerWithMissingRequiredField() {
        String screenName = TestDataFactory.randomScreenName();
        String login = TestDataFactory.randomLogin();
        String password = TestDataFactory.randomAlphabeticPassword(7);
        Integer age = TestDataFactory.getMinValidAge();

        Map<String, Object> queryParams = Map.of(
                "age", age,
                "gender", Gender.MALE.getValue(),
                "login", login,
                "password", password,
                "role", Roles.USER.getValue(),
                "screenName", screenName);

        ValidatableResponse response = PlayerFacade.createPlayerWithValidatableResponse(TokenStorage.getToken(Roles.SUPERVISOR), queryParams);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, HttpStatus.SC_BAD_REQUEST);
    }

    @Test(description = "Verify invalid gender")
    @Severity(SeverityLevel.CRITICAL)
    @Issue("TMS-0001")
    public void createPlayerWithInvalidGender() {
        String screenName = TestDataFactory.randomScreenName();
        String login = TestDataFactory.randomLogin();
        String password = TestDataFactory.randomAlphabeticPassword(7);
        Integer age = TestDataFactory.getMinValidAge();
        String invalidGender = "Other";

        Map<String, Object> queryParams = Map.of(
                "age", age,
                "gender", invalidGender,
                "login", login,
                "password", password,
                "role", Roles.USER.getValue(),
                "screenName", screenName);

        ValidatableResponse response = PlayerFacade.createPlayerWithValidatableResponse(TokenStorage.getToken(Roles.SUPERVISOR), queryParams);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, HttpStatus.SC_BAD_REQUEST);
    }


    @Test(dataProvider = "passwordProvider", description = "Verify password validation")
    @Severity(SeverityLevel.CRITICAL)
    @Issue("TMS-0004")// No validation on contain latin letters and numbers (min 7 max 15 characters)
    public void createPlayerWithInvalidPasswordLength(Integer length) {
        String screenName = TestDataFactory.randomScreenName();
        String login = TestDataFactory.randomLogin();
        String password = TestDataFactory.randomNumericPassword(length);
        Integer age = TestDataFactory.getMinValidAge();

        Map<String, Object> queryParams = Map.of(
                "age", age,
                "gender", Gender.MALE.getValue(),
                "login", login,
                "password", password,
                "role", Roles.USER.getValue(),
                "screenName", screenName);

        ValidatableResponse response = PlayerFacade.createPlayerWithValidatableResponse(TokenStorage.getToken(Roles.SUPERVISOR), queryParams);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, HttpStatus.SC_BAD_REQUEST);
    }

    @DataProvider(name = "passwordProvider", parallel = true)
    public Object[][] passwordProvider() {
        return new Object[][] {
                {6},
                {16}
        };
    }

    @Test(description = "Verify how application handle duplicate")
    @Severity(SeverityLevel.CRITICAL)
    @Issue("TMS-0005")// Allow to use duplicate login, screenName. Previous Entity will be updated
    public void createPlayerWithDuplicateFields() {
        String screenName = TestDataFactory.randomScreenName();
        String login = TestDataFactory.randomLogin();
        String password = TestDataFactory.randomAlphabeticPassword(7);
        Integer age = TestDataFactory.getMinValidAge();

        Map<String, Object> queryParams = Map.of(
                "age", age,
                "gender", Gender.FEMALE.getValue(),
                "login", login,
                "password", password,
                "role", Roles.USER.getValue(),
                "screenName", screenName);

        PlayerFacade.createPlayer(TokenStorage.getToken(Roles.SUPERVISOR), queryParams);
        ValidatableResponse response = PlayerFacade.createPlayerWithValidatableResponse(TokenStorage.getToken(Roles.SUPERVISOR), queryParams);
        int statusCode = response.extract().response().statusCode();
        Assert.assertEquals(statusCode, HttpStatus.SC_BAD_REQUEST);
    }

    @Test(description = "Verify user can not delete other user")
    @Severity(SeverityLevel.CRITICAL)
    @Issue("TMS-0006")// Player able to delete other player
    public void deleteOtherUser() {
        Map<String, Object> queryParams = Map.of(
                "age", TestDataFactory.getMinValidAge(),
                "gender", Gender.MALE.getValue(),
                "login", TestDataFactory.randomLogin(),
                "password", TestDataFactory.randomAlphabeticPassword(7),
                "role", Roles.USER.getValue(),
                "screenName", TestDataFactory.randomScreenName());
        PlayerCreateResponse response = PlayerFacade.createPlayer(TokenStorage.getToken(Roles.SUPERVISOR), queryParams);
        Integer firstPlayerId = response.getId();

        String secondPlayerLogin = TestDataFactory.randomLogin();
        queryParams = Map.of(
                "age", TestDataFactory.getMinValidAge(),
                "gender", Gender.MALE.getValue(),
                "login", secondPlayerLogin,
                "password", TestDataFactory.randomAlphabeticPassword(7),
                "role", Roles.USER.getValue(),
                "screenName", TestDataFactory.randomScreenName());
        PlayerFacade.createPlayer(TokenStorage.getToken(Roles.SUPERVISOR), queryParams);
        PlayerFacade.deletePlayerById(secondPlayerLogin, firstPlayerId, HttpStatus.SC_FORBIDDEN);
    }


    @Test(description = "Verify response on delete not existed user")
    @Severity(SeverityLevel.MINOR)
    @Issue("TMS-0007") // Wrong response status code 403 but should be 404
    public void deleteNotExistedPlayer() {
        PlayerFacade.deletePlayerById(TokenStorage.getToken(Roles.SUPERVISOR), 99999, HttpStatus.SC_NOT_FOUND);
    }
}
