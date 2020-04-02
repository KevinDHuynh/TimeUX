package edu.wit.mobileapp.TimeUX.ui.login;

import java.io.IOException;

import edu.wit.mobileapp.TimeUX.model.LoggedInUser;
import kong.unirest.Unirest;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            Unirest.get("137.135.120.16:8080")
                    .basicAuth(username,password)
                    .asString();
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
