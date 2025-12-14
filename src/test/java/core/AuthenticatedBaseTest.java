package core;

import model.LoginRequest;
import org.junit.jupiter.api.BeforeAll;
import service.LoginService;
import utils.factory.LoginDataFactory;

import static utils.status.HttpStatus.OK;
import static utils.api.LoginApi.AUTHORIZATION;

public abstract class AuthenticatedBaseTest extends BaseTest {

    @BeforeAll
    static void authenticate() {

        LoginService loginService = new LoginService();
        LoginRequest request = LoginDataFactory.validLogin();

        var response = loginService.performLogin(request);

        if (response.statusCode() != OK) {
            throw new RuntimeException("Authentication failed");
        }

        String token = response.jsonPath().getString(AUTHORIZATION);
        AuthContext.setToken(token);

        if (!AuthContext.hasToken()) {
            throw new RuntimeException("Token was not saved in AuthContext");
        }
    }
}
