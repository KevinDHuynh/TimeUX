package edu.wit.mobileapp.TimeUX.ui.home;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        DateTimeFormatter dateF = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String dateStr = dateF.format(now);
        mText.setValue(dateStr);
    }

    public LiveData<String> getText() {
        return mText;
    }
}