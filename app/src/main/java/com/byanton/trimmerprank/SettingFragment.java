package com.byanton.trimmerprank;


import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import androidx.annotation.Nullable;


public class SettingFragment extends PreferenceFragment {
    public static final String SOUND_POSITION = "sound_position";
    public static final String NOTIFICATION = "notification";
    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        preferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

                if (key.equals(SOUND_POSITION)) {
                    final Preference sppref = findPreference(key);
                    sppref.setSummary(sharedPreferences.getString(key, "Sound 1"));

                }
                if(key.equals(NOTIFICATION)){
                    Preference switchPreference = findPreference(key);
                    switchPreference.getSharedPreferences().getBoolean(key,true);


                }
            }
        };

    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(preferenceChangeListener);
        final ListPreference sppref = (ListPreference) findPreference(SOUND_POSITION);
        sppref.setSummary(getPreferenceScreen().getSharedPreferences().getString(SOUND_POSITION, "Sound 1"));
        Preference switchPreference = findPreference(NOTIFICATION);
        switchPreference.getSharedPreferences().getBoolean(NOTIFICATION,true);

    }


    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
    }






}

