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

        @Nested
        class UpdateUser {

            @Test
            void shouldUpdateUserSuccessfully() {

                var createResponse = userService.createUser(UserDataFactory.validUser());
                String userId = createResponse.jsonPath().getString("_id");

                UserRequest updatedRequest = UserDataFactory.updatedUser();

                var updateResponse = userService.updateUser(userId, updatedRequest);

                assertThat(updateResponse.statusCode(), is(OK));
                assertThat(updateResponse.jsonPath().getString("message"),
                        containsString("Registro alterado com sucesso"));
            }
        }

        @Nested
        class DeleteUser {

            @Test
            void shouldDeleteUserSuccessfully() {

                var createResponse = userService.createUser(UserDataFactory.validUser());
                String userId = createResponse.jsonPath().getString("_id");

                var deleteResponse = userService.deleteUser(userId);

                assertThat(deleteResponse.statusCode(), is(OK));
                assertThat(deleteResponse.jsonPath().getString("message"),
                        containsString("Registro excluído com sucesso"));
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

        @Nested
        class UpdateUser {

            @Test
            void shouldReturnBadRequestWhenUpdatingWithInvalidPayload() {

                var createResponse = userService.createUser(UserDataFactory.validUser());
                String userId = createResponse.jsonPath().getString("_id");

                UserRequest invalidRequest = UserDataFactory.userWithoutEmail();

                var response = userService.updateUser(userId, invalidRequest);

                assertThat(response.statusCode(), is(BAD_REQUEST));
            }
        }

        @Nested
        class DeleteUser {

            @Test
            void shouldReturnOkWhenDeletingNonExistingUser() {

                var response = userService.deleteUser("invalid-id");

                assertThat(response.statusCode(), is(OK));
            }
        }
    }
}
