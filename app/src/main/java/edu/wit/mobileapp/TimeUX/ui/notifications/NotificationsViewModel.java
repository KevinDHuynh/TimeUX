package edu.wit.mobileapp.TimeUX.ui.notifications;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NotificationsViewModel extends ViewModel {

    public class MainActivityViewModel extends ViewModel {

        private String TAG = MainActivityViewModel.class.getSimpleName();

        private MutableLiveData<List<String>> MessageList;

        LiveData<List<String>> getMessageList() {
            if ( MessageList == null) {
                 MessageList = new MutableLiveData<>();
                loadMessages();
            }
            return MessageList;
        }

        private void loadMessages() {
            //
                List<String> messageStringList = new ArrayList<>();
                messageStringList.add("Messages");
                messageStringList.add("Reminders");
                long seed = System.nanoTime();
                Collections.shuffle(messageStringList, new Random(seed));

                MessageList.setValue(messageStringList);
        }

        @Override
        protected void onCleared() {
            super.onCleared();
            Log.d(TAG, "on cleared called");
        }
    }
}
