package service;

import core.BaseService;
import io.restassured.response.Response;
import model.UserRequest;

import static utils.api.UserApi.ENDPOINT;

public class UserService extends BaseService {

    // GET /usuarios (autenticado)
    public Response getUsers() {
        return authenticatedRequest()
                .when()
                .get(ENDPOINT)
                .then()
                .extract()
                .response();
    }

    // POST /usuarios (criação de usuário)
    public Response createUser(UserRequest request) {
        return authenticatedRequest()
                .body(request)
                .when()
                .post(ENDPOINT)
                .then()
                .extract()
                .response();
    }
}
