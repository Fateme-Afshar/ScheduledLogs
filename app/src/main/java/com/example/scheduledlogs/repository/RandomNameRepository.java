package com.example.scheduledlogs.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.scheduledlogs.database.RandNameDao;
import com.example.scheduledlogs.database.RandNameSchema;
import com.example.scheduledlogs.database.RandomNameDatabase;
import com.example.scheduledlogs.model.RandomName;
import com.example.scheduledlogs.network.responseModel.RandomNameResponse;
import com.example.scheduledlogs.network.retrofit.RetrofitInstance;
import com.example.scheduledlogs.network.retrofit.RetrofitInterface;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class RandomNameRepository implements IRepository<RandomName> {
    public static final String TAG = "ScheduleLogs";
    private static RandomNameRepository sInstance;
    private RetrofitInterface mRetrofitInterface;
    private RandNameDao mDAO;
    private Context mContext;

    private MutableLiveData<RandomName> mRandomNameMutableLiveData=new MutableLiveData<>();

    private RandomNameRepository(Context context) {
        mContext=context.getApplicationContext();
        mRetrofitInterface=RetrofitInstance.
                getRetrofit().
                create(RetrofitInterface.class);

        RandomNameDatabase database= Room.databaseBuilder(
                        mContext,
                        RandomNameDatabase.class,
                        RandNameSchema.NAME).
                build();

        mDAO=database.getDao();
    }

    public static RandomNameRepository getInstance(Context context) {
        if (sInstance == null)
            sInstance = new RandomNameRepository(context);
        return sInstance;
    }

    // Note: this method request to network and run on main thread
    public void fetchName(){
        Call<RandomNameResponse> call= mRetrofitInterface.sendRequestRandomName();
        try {
            Response<RandomNameResponse> response=call.execute();
            RandomNameResponse randomNameResponse=response.body();
            RandomName randomName=new RandomName(randomNameResponse.getName());
            Log.d(TAG,"insert new random name");
            mRandomNameMutableLiveData.postValue(randomName);
            insert(randomName);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public MutableLiveData<RandomName> getRandomNameMutableLiveData() {
        return mRandomNameMutableLiveData;
    }

    @Override
    public RandomName get(int id) {
        return mDAO.get(id);
    }

    @Override
    public List<RandomName> getList() {
        return mDAO.getList();
    }

    @Override
    public void insert(RandomName randomName) {
        mDAO.insert(randomName);
    }

    @Override
    public void delete(RandomName randomName) {
        mDAO.delete(randomName);
    }

    @Override
    public void update(RandomName randomName) {
        mDAO.update(randomName);
    }
}
