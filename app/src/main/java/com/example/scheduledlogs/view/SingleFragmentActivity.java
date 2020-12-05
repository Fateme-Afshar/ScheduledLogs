package com.example.scheduledlogs.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.scheduledlogs.R;
import com.example.scheduledlogs.databinding.MainViewBinding;

public abstract class SingleFragmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);

        Fragment fragment =
                getSupportFragmentManager().
                        findFragmentById(R.id.fragment_container);

        if (fragment == null)
            getSupportFragmentManager().
                    beginTransaction().
                    add(R.id.fragment_container, getFragment()).
                    commit();
    }

    public abstract Fragment getFragment();
}
