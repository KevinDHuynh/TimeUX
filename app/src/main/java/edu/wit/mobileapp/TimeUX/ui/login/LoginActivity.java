package edu.wit.mobileapp.TimeUX.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.wit.mobileapp.TimeUX.MainActivity;
import edu.wit.mobileapp.TimeUX.R;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static volatile Boolean LoginSuccess = Boolean.FALSE;
    
    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_login) Button _loginButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void submit(View view) {

            login();

    }
    public void login() {
        Log.d(TAG, "Login");
        //if (!validate()) {
        //    onLoginFailed();
        //    return;
        //}

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        new Thread(() -> {

        try {

            HttpResponse<String> response =
                    Unirest.get("http://137.135.120.16:8080/users/")
                            .basicAuth("admin", "secret")
                            .header("content-type", "application/json")
                            .asString();


            if (response.getCode() == 200) {
                Log.d("LOGINEXCEPTION","Login info: " + response.getBody());
                if(response.getBody().contains("\"username\":\""+email+"\"")){
                    String s = response.getBody().substring(0, response.getBody().indexOf(email));
                    String FullName = s.substring(s.lastIndexOf("fullname")+11,s.lastIndexOf("password")-3);
                    s = s.substring(s.lastIndexOf("password")+11,s.length()-14);
                    if(password.equals(s)){
                        LoginSuccess = Boolean.TRUE;
                    }
                    else{
                        throw new IOException();
                    }
                }
                else{
                    throw new IOException();
                }
            }
            else{
                throw new IOException();
            }
            Unirest.shutdown();
        }catch(Exception e){
            Log.d("LOGINEXCEPTION","login() exception is reached!" + e);
        }
        }).start();

        new android.os.Handler().postDelayed(
                () -> {
                    // On complete call either onLoginSuccess or onLoginFailed
                    if(LoginSuccess) {
                        onLoginSuccess();
                    }
                    else {
                        onLoginFailed();
                    }
                    progressDialog.dismiss();
                }, 3000);

    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        Intent intent  = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}