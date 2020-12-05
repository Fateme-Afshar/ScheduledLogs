package com.example.scheduledlogs.view.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.scheduledlogs.view.SingleFragmentActivity;
import com.example.scheduledlogs.view.fragment.MainFragment;

public class MainActivity extends SingleFragmentActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        //starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    public Fragment getFragment() {
        return MainFragment.newInstance();
    }
}