package utils.factory;

import model.UserRequest;

import java.util.UUID;

public class UserDataFactory {

    public static UserRequest validUser() {
        return new UserRequest(
                "QA User",
                "qa_" + UUID.randomUUID() + "@mail.com",
                "123456",
                "true"
        );
    }

    public static UserRequest userWithoutEmail() {
        return new UserRequest(
                "QA User",
                "",
                "123456",
                "true"
        );
    }

    public static UserRequest userWithoutPassword() {
        return new UserRequest(
                "QA User",
                "qa@mail.com",
                "",
                "true"
        );
    }

    public static UserRequest updatedUser() {
        return new UserRequest(
                "QA User Updated",
                "updated_" + UUID.randomUUID() + "@mail.com",
                "123456",
                "true"
        );
    }

}
