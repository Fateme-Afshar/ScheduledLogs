package com.example.scheduledlogs.service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.scheduledlogs.repository.RandomNameRepository;

import java.util.concurrent.TimeUnit;

public class AlarmService extends IntentService {
    public static Intent start(Context context) {
        //  starter.putExtra();
        return new Intent(context, AlarmService.class);
    }

    public static final String TAG = "ScheduleLogs";
    private RandomNameRepository mRepository;

    public AlarmService() {
        super(TAG);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        ConnectivityManager connectivityManager = getSystemService(ConnectivityManager.class);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        mRepository=RandomNameRepository.getInstance(getApplicationContext());
        if (networkInfo == null && !networkInfo.isConnected())
            return;
        Log.d(TAG,"Start fetch name");
        mRepository.fetchName();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void getScheduleRandomName(Context context, boolean isOn) {
        context = context.getApplicationContext();
        AlarmManager alarmManager = context.getSystemService(AlarmManager.class);

        PendingIntent operation = PendingIntent.getService(context,
                1,
                AlarmService.start(context),
                0);
        if (!isOn) {
            alarmManager.setInexactRepeating(
                    AlarmManager.ELAPSED_REALTIME,
                    SystemClock.elapsedRealtime(),
                    TimeUnit.MINUTES.toMillis(1),
                    operation);

        } else {
            alarmManager.cancel(operation);
            operation.cancel();
        }
    }
}
