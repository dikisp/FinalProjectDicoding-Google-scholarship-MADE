package com.diki.submisisatu.settings;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.Toast;

import com.diki.submisisatu.R;
import com.diki.submisisatu.alarm.DailyReminder;
import com.diki.submisisatu.alarm.ReleaseTodayReminder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SettingActivity extends AppCompatActivity
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new SettingFragment())
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.key_daily))) {
            handleDailyService(sharedPreferences.getBoolean(key, false));
        } else if (key.equals(getString(R.string.key_release))) {
            handleReleaseService(sharedPreferences.getBoolean(key, false));
        }
    }

    private void handleDailyService(boolean on) {
        if (on) {
            enableDailyReminder();
        } else {
            disableDailyReminder();
        }
    }

    private void handleReleaseService(boolean on) {
        if (on) {
            enableReleaseService();
        } else {
            disableReleaseService();
        }
    }

    private void enableDailyReminder() {
        setAlarm(DailyReminder.class, DailyReminder.NOTIFICATION_ID, 7);
        Toast.makeText(this, "Daily reminder active", Toast.LENGTH_SHORT).show();
    }

    private void disableDailyReminder() {
        stopAlarm(DailyReminder.class, DailyReminder.NOTIFICATION_ID);
        Toast.makeText(this, "Daily reminder canceled", Toast.LENGTH_SHORT).show();
    }

    private void enableReleaseService() {
        setAlarm(ReleaseTodayReminder.class, ReleaseTodayReminder.NOTIFICATION_ID, 8);
        Toast.makeText(this, "Release daily reminder active", Toast.LENGTH_SHORT).show();
    }

    private void disableReleaseService() {
        stopAlarm(ReleaseTodayReminder.class, ReleaseTodayReminder.NOTIFICATION_ID);
        Toast.makeText(this, "Release daily reminder canceled", Toast.LENGTH_SHORT).show();
    }

    private void setAlarm(Class cls, int notificationId, int hour) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, cls);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, notificationId, intent, 0);
        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }

    private void stopAlarm(Class cls, int notificationId) {
        Intent intent = new Intent(this, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, notificationId, intent, 0);
        pendingIntent.cancel();

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
    }

    public static class SettingFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle bundle, String s) {
            addPreferencesFromResource(R.xml.preferences);
        }
    }
}
