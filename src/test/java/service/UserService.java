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
    public Response updateUser(String userId, UserRequest request) {
        return authenticatedRequest()
                .body(request)
                .when()
                .put(ENDPOINT + "/" + userId)
                .then()
                .extract()
                .response();
    }

    public Response deleteUser(String userId) {
        return authenticatedRequest()
                .when()
                .delete(ENDPOINT + "/" + userId)
                .then()
                .extract()
                .response();
    }

}
