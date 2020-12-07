package com.example.scheduledlogs.viewModel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.scheduledlogs.model.RandomName;
import com.example.scheduledlogs.repository.RandomNameRepository;
import com.example.scheduledlogs.service.AlarmService;
import com.example.scheduledlogs.service.LogWorker;
import com.example.scheduledlogs.service.PollJobSchedulerService;

public class RandNameViewModel extends AndroidViewModel {
    private RandomNameRepository mRepository;
    private LiveData<RandomName> mRandomNameLiveData;

    public RandNameViewModel(@NonNull Application application) {
        super(application);
        mRepository=RandomNameRepository.getInstance(getApplication());
    }

    public LiveData<RandomName> getRandomNameLiveData() {
        mRandomNameLiveData=mRepository.getRandomNameMutableLiveData();
        return mRandomNameLiveData;
    }

    public void setOnStartBtnListener(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            LogWorker.enqueue(getApplication(),false);
        }
    }

    public void setOnStopBtnListener(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            LogWorker.enqueue(getApplication(),true);
        }
    }
}
