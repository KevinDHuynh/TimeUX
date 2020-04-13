package edu.wit.mobileapp.TimeUX.ui.settings;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;

import edu.wit.mobileapp.TimeUX.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}

