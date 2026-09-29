package com.eshabudram.smartpantry;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

//this file is settings screen it lets the user turn expiringalerts on and off.
//choice chosen is saved using sharedpreferences, so its remembered next time the app opens.
public class Settings extends AppCompatActivity{
    private static final String PREFS_NAME="smart_pantry_prefs";
    private static final String KEY_EXPIRY_ALERTS ="expiry_alerts_enabled";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        Switch switchExpiryAlerts=findViewById(R.id.switchExpiryAlerts);
        //shared prefernces is a simple storage for small values in android for the on off setting
        SharedPreferences prefs=getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Load the saved setting it defaults to true if it was never set before
        boolean alertsEnabled=prefs.getBoolean(KEY_EXPIRY_ALERTS,true);
        switchExpiryAlerts.setChecked(alertsEnabled);

        //this method saves the new value whenever the switch is changed
        switchExpiryAlerts.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(KEY_EXPIRY_ALERTS, isChecked);
            editor.apply();
        });
        // setting up the boptttom navigation bar so screens can be to pantry or recipes
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.nav_settings); // highlight Settings as currently open

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_pantry) {
                startActivity(new Intent(Settings.this, MainActivity.class));
                return true;
            } else if (id == R.id.nav_recipes) {
                startActivity(new Intent(Settings.this, SuggestedRecipes.class));
                return true;
            }

            // navsettings is this screen already
            return true;
        });
    }
}