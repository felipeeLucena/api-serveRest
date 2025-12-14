package utils.factory;

import model.LoginRequest;

public class LoginDataFactory {

    public static LoginRequest validLogin() {
        return new LoginRequest(
                "fulano@qa.com",
                "teste"
        );
    }

    public static LoginRequest invalidLogin() {
        return new LoginRequest(
                "email@invalido.com",
                "senhaerrada"
        );
    }

    public static LoginRequest onlyEmailFilled() {
        return new LoginRequest(
                "fulano@qa.com",
                ""
        );
    }

    public static LoginRequest onlyPasswordFilled() {
        return new LoginRequest(
                "",
                "teste"
        );
    }

    public static LoginRequest emptyEmailAndPassword() {
        return new LoginRequest(
                "",
                ""
        );
    }
}
