package com.example.scheduledlogs.service;

import android.annotation.SuppressLint;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.scheduledlogs.repository.RandomNameRepository;

import java.util.concurrent.TimeUnit;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class PollJobSchedulerService extends JobService {
    public static final String TAG = "ScheduleLogs";
    public static final int JOB_ID = 1;

    public PollJobSchedulerService() {
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        RandomNameRepository repository=RandomNameRepository.getInstance(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"Start fetch name");
                repository.fetchName();
                jobFinished(params,true);
            }
        }).start();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void getWithJobSchedulerRandomName(Context context, boolean isOn) {
        context = context.getApplicationContext();
        JobScheduler jobScheduler = (JobScheduler)
                context.getSystemService(JOB_SCHEDULER_SERVICE);
            if (!isOn){
                ComponentName componentName=
                        new ComponentName(context.getApplicationContext(),PollJobSchedulerService.class);
                JobInfo jobInfo=new JobInfo.Builder(JOB_ID,componentName).
                        setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED).
                        setPeriodic(TimeUnit.MINUTES.toMillis(1)).
                        setPersisted(true).
                        build();
                jobScheduler.schedule(jobInfo);
            }else {
                jobScheduler.cancel(JOB_ID);
            }
    }
}