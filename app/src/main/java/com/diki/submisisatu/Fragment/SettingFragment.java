//package com.diki.submisisatu.Fragment;
//
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.preference.Preference;
//import android.preference.PreferenceFragment;
//import android.preference.SwitchPreference;
//import android.provider.Settings;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.diki.submisisatu.Api.RetrofitApi;
//import com.diki.submisisatu.BuildConfig;
//import com.diki.submisisatu.Model.Movie;
//import com.diki.submisisatu.Model.MovieResponse;
//import com.diki.submisisatu.R;
//import com.diki.submisisatu.alarm.DailyAlarmReceiver;
//import com.diki.submisisatu.alarm.ReleaseTodayReminder;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//
//import butterknife.BindString;
//import butterknife.ButterKnife;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//
//import static android.content.ContentValues.TAG;
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {
//
//    private static final String API_KEY = BuildConfig.APIKEY;
//    private static final String LANGUAGE = "en-us";
//    private int currentPage = 1;
//    private String region = null;
//
//    private Call<Movie> call;
//    private Retrofit apiClient = null;
//
//    private List<Movie> nowPlayMovies = new ArrayList<>();
//
//    private DailyAlarmReceiver dailyAlarmReceiver = new DailyAlarmReceiver();
//    private ReleaseTodayReminder releaseTodayReminder = new ReleaseTodayReminder();
//
//    @BindString(R.string.key_daily_reminder)
//    String keyDailyReminder;
//
//    @BindString(R.string.key_release_reminder)
//    String keyReleaseReminder;
//
//    @BindString(R.string.key_setting_language)
//    String keySettingLanguage;
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        addPreferencesFromResource(R.xml.preferences);
//        ButterKnife.bind(this, getActivity());
//
//        SwitchPreference switchDailyReminder = (SwitchPreference) findPreference(keyDailyReminder);
//        switchDailyReminder.setOnPreferenceChangeListener(this);
//        SwitchPreference switchUpcomingReminder = (SwitchPreference) findPreference(keyReleaseReminder);
//        switchUpcomingReminder.setOnPreferenceChangeListener(this);
//        findPreference(keySettingLanguage).setOnPreferenceClickListener(this);
//
//    }
//
//    @Override
//    public boolean onPreferenceChange(Preference preference, Object newValue) {
//        String key = preference.getKey();
//        boolean isSet = (boolean) newValue;
//
//        if (key.equals(keyDailyReminder)) {
//            if (isSet) {
//                dailyAlarmReceiver.setRepeatingAlarm(getActivity());
//            } else {
//                dailyAlarmReceiver.cancelAlarm(getActivity());
//            }
//        } else {
//            if (isSet) {
//                getTodayRelease();
//            } else {
//                releaseTodayReminder.cancelAlarm(getActivity());
//            }
//        }
//
//        return true;
//    }
//
//    @Override
//    public boolean onPreferenceClick(Preference preference) {
//        String key = preference.getKey();
//
//        if (key.equals(keySettingLanguage)) {
//            Intent languageIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
//            startActivity(languageIntent);
//        }
//        return true;
//    }
//
//    private void getTodayRelease() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//        Date date = new Date();
//        final String currentDate = sdf.format(date);
////        final String currentDate = "2018-08-18";
//
//        Log.d(TAG, "getMatchToday: " + dateFormatter(currentDate));
//
//        apiClient = RetrofitApi.getInstance();
//        call = apiClient.ge().getNowPlayingMovies(API_KEY, LANGUAGE, currentPage, region);
//        call.enqueue(new Callback<MovieResponse>() {
//            @Override
//            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
//                if (response.isSuccessful()) {
//                    List<MovieResult> todayMovie = new ArrayList<>();
//                    nowPlayMovies = response.body().getMovieResults();
//                    for (int i = 0; i < nowPlayMovies.size(); i++) {
//
//                        MovieResult movie = nowPlayMovies.get(i);
//                        Date movieDate = dateFormatter(movie.getMovieReleaseDate());
//                        Log.d(TAG, "onResponse: " + movieDate);
//
//                        if (movieDate.equals(dateFormatter(currentDate))) {
//                            todayMovie.add(movie);
//                        }
//
//                        Log.d(TAG, "onResponse: " + todayMovie.size());
//                    }
//                    releaseTodayReminder.setRepeatingAlarm(getActivity(), todayMovie);
//                } else {
//                    Toast.makeText(getActivity(), R.string.toast_failed, Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieResponse> call, Throwable t) {
//                Toast.makeText(getActivity(), R.string.toast_failed, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private Date dateFormatter(String movieDate) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//        Date date = null;
//        try {
//            date = sdf.parse(movieDate);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return date;
//    }
//}
