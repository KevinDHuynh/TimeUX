package edu.wit.mobileapp.TimeUX.ui.login;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import java.io.IOException;
import java.util.UUID;

import edu.wit.mobileapp.TimeUX.model.LoggedInUser;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            HttpResponse<String> response =
                    Unirest.get("137.135.120.16:8080/users")
                            .basicAuth(username, password)
                            .asString();
            if (response.getCode() == 200) {
                LoggedInUser fakeUser =
                        new LoggedInUser(
                                UUID.randomUUID().toString(),
                                "Jane Doe");
                return new Result.Success<>(fakeUser);
            } else throw new IOException();
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
