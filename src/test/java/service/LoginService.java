package service;

import io.restassured.response.Response;
import model.LoginRequest;

import static io.restassured.RestAssured.given;
import static utils.api.LoginApi.ENDPOINT;

public class LoginService {

    public Response performLogin(LoginRequest request) {

        return given()
                .contentType("application/json")
                .body(request)
                .when()
                .post(ENDPOINT)
                .then()
                .extract()
                .response();
    }
}
