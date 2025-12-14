import core.AuthenticatedBaseTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import service.UserService;
import model.UserRequest;
import utils.factory.UserDataFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static utils.status.HttpStatus.*;

public class UserTest extends AuthenticatedBaseTest {

    private final UserService userService = new UserService();

    // =========================
    // SUCCESS SCENARIOS
    // =========================
    @Nested
    class Success {

        @Nested
        class GetUsers {

            @Test
            void shouldReturnUsersWhenAuthenticated() {

                var response = userService.getUsers();

                assertThat(response.statusCode(), is(OK));
                assertThat(response.jsonPath().getList("usuarios"), not(empty()));
            }
        }

        @Nested
        class CreateUser {

            @Test
            void shouldCreateUserSuccessfully() {

                UserRequest request = UserDataFactory.validUser();

                var response = userService.createUser(request);

                assertThat(response.statusCode(), is(CREATED));
                assertThat(response.jsonPath().getString("_id"), notNullValue());
            }
        }
    }

    // =========================
    // FAILURE SCENARIOS
    // =========================
    @Nested
    class Failure {

        @Nested
        class CreateUser {

            @Test
            void shouldReturnBadRequestWhenEmailIsMissing() {

                UserRequest request = UserDataFactory.userWithoutEmail();

                var response = userService.createUser(request);

                assertThat(response.statusCode(), is(BAD_REQUEST));
            }

            @Test
            void shouldReturnBadRequestWhenPasswordIsMissing() {

                UserRequest request = UserDataFactory.userWithoutPassword();

                var response = userService.createUser(request);

                assertThat(response.statusCode(), is(BAD_REQUEST));
            }
        }
    }
}
