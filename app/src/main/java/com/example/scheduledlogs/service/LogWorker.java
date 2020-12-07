package com.example.scheduledlogs.service;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.scheduledlogs.repository.RandomNameRepository;

import java.util.concurrent.TimeUnit;

public class LogWorker extends Worker {
    public static final String TAG = "ScheduleLogs";
    public static final String RANDOM_NAME_REQUEST = "RandomNameRequest";
    private RandomNameRepository mRepository;

    public LogWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        mRepository = RandomNameRepository.getInstance(context);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "request to network for receive new random name");
        mRepository.fetchName();
        return Result.success();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void enqueue(Context context, boolean isOn) {
        context = context.getApplicationContext();
        WorkManager workManager = WorkManager.getInstance(context);
        if (!isOn){
            Log.d(TAG, "workManager enqueue");
            Constraints constraint = new Constraints.Builder().
                    setRequiredNetworkType(NetworkType.CONNECTED).
                    build();

            PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.
                    Builder(LogWorker.class, 1, TimeUnit.MINUTES).
                    setConstraints(constraint).
                    build();

            workManager.
                    enqueueUniquePeriodicWork(
                            RANDOM_NAME_REQUEST,
                            ExistingPeriodicWorkPolicy.REPLACE,
                            periodicWorkRequest);
        }else {
            Log.d(TAG, "workManager cancel");
            workManager.cancelUniqueWork(RANDOM_NAME_REQUEST);
        }
    }

}