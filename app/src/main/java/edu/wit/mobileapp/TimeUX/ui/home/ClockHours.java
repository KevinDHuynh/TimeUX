package edu.wit.mobileapp.TimeUX.ui.home;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.mashape.unirest.http.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Future;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.wit.mobileapp.TimeUX.MainActivity;
import edu.wit.mobileapp.TimeUX.R;

public class ClockHours extends AppCompatActivity {

    public String username;
    public String date;

    @BindView(R.id.spinner_status) Spinner statusSpinner_;
    @BindView(R.id.spinner_location) Spinner locationSpinner_;
    @BindView(R.id.edit_comment) EditText commentEdit_;
    @BindView(R.id.btn_updateStatus) Button updateStatusBtn_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clockhours);
        ButterKnife.bind(this);
        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this,
                R.array.status_values, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner_.setAdapter(statusAdapter);

        ArrayAdapter<CharSequence> locationAdapter = ArrayAdapter.createFromResource(this,
                R.array.location_values, android.R.layout.simple_spinner_item);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner_.setAdapter(locationAdapter);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @OnClick(R.id.btn_updateStatus)
    public void submit(View view){
        String spinner = statusSpinner_.getSelectedItem().toString();
        String location = locationSpinner_.getSelectedItem().toString();
        String comment = commentEdit_.getText().toString();

        DateTimeFormatter dateF = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter timeF = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        String date = dateF.format(now);
        String time = timeF.format(now);



            try{
                Future<HttpResponse<JsonNode>> response =
                        Unirest.post("http://137.135.120.16:8080/TimeTracking")
                                .basicAuth("admin", "secret")
                                .header("accept","application/json")
                                .header("content-type","application/json")
                                .body("[{\"User\":\"" +"sestabrook" //TODO replace sestabrook with user login creds
                                        +"\",\"Date\":\""+date
                                        +"\",\"Time\":\""+time
                                        +"\",\"Time\":\""+time
                                        +"\",\"Status\":\""+spinner
                                        +"\",\"Location\":\""+location
                                        +"\",\"Comment\":\""+comment+"\"}]")
                                .asJsonAsync();


                while(!response.isDone()) {
                    Thread.sleep(300);
                }

            } catch (Exception e) {
                Log.d("LOGINEXCEPTION","An exception occurred clocking hours: " + e);
            }
        //}).start();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}
