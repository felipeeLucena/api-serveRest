import core.BaseTest;
import model.LoginRequest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import service.LoginService;
import utils.factory.LoginDataFactory;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static utils.api.LoginApi.*;
import static utils.messages.LoginMessages.*;
import static utils.status.HttpStatus.*;

public class LoginTest extends BaseTest {

    private final LoginService loginService = new LoginService();

    // =========================
    // SUCCESS SCENARIOS
    // =========================
    @Nested
    class SuccessfulLogin {

        @Test
        void shouldLoginSuccessfully() {

            LoginRequest request = LoginDataFactory.validLogin();
            var response = loginService.performLogin(request);

            assertThat(response.statusCode(), is(OK));
            assertThat(response.jsonPath().getString(AUTHORIZATION), notNullValue());
        }

        @Test
        void shouldReturnBearerTokenWhenLoginIsSuccessful() {

            LoginRequest request = LoginDataFactory.validLogin();
            var response = loginService.performLogin(request);

            String token = response.jsonPath().getString(AUTHORIZATION);

            assertThat(token, notNullValue());
            assertThat(token, not(isEmptyString()));
            assertThat(token, startsWith("Bearer "));
        }
    }

    // =========================
    // FAILURE SCENARIOS
    // =========================
    @Nested
    class FailedLogin {

        // 401 - credentials are wrong but request is valid
        @ParameterizedTest(name = "{index} => invalid credentials")
        @MethodSource("unauthorizedLoginScenarios")
        void shouldReturnUnauthorizedWhenCredentialsAreInvalid(LoginRequest request) {

            var response = loginService.performLogin(request);

            assertThat(response.statusCode(), is(UNAUTHORIZED));
            assertThat(response.jsonPath().getString(MESSAGE),
                    containsString(INVALID_LOGIN));
        }

        // 400 - request is invalid (missing required fields)
        @ParameterizedTest(name = "{index} => missing required fields")
        @MethodSource("badRequestLoginScenarios")
        void shouldReturnBadRequestWhenRequiredFieldsAreMissing(LoginRequest request) {

            var response = loginService.performLogin(request);

            // For 400, API does NOT guarantee response body
            assertThat(response.statusCode(), is(BAD_REQUEST));
        }

        private static Stream<LoginRequest> unauthorizedLoginScenarios() {
            return Stream.of(
                    LoginDataFactory.invalidLogin()
            );
        }

        private static Stream<LoginRequest> badRequestLoginScenarios() {
            return Stream.of(
                    LoginDataFactory.onlyEmailFilled(),
                    LoginDataFactory.onlyPasswordFilled(),
                    LoginDataFactory.emptyEmailAndPassword()
            );
        }
    }
}
