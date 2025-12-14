package core;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public abstract class BaseService {

    protected RequestSpecification authenticatedRequest() {
        return given()
                .header("Authorization", AuthContext.getToken())
                .contentType("application/json");
    }
}
