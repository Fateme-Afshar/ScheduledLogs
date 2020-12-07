package com.example.scheduledlogs.view.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.scheduledlogs.R;
import com.example.scheduledlogs.databinding.MainViewBinding;
import com.example.scheduledlogs.model.RandomName;
import com.example.scheduledlogs.repository.RandomNameRepository;
import com.example.scheduledlogs.service.AlarmService;
import com.example.scheduledlogs.service.LogWorker;
import com.example.scheduledlogs.viewModel.RandNameViewModel;

public class MainFragment extends Fragment {
    private MainViewBinding mBinding;
    private RandNameViewModel mViewModel;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel=new ViewModelProvider(this).get(RandNameViewModel.class);
       mViewModel.getRandomNameLiveData().observe(this, new Observer<RandomName>() {
           @Override
           public void onChanged(RandomName randomName) {
               String text=getString(R.string.rand_name_info,
                       randomName.getName(),
                       String.valueOf(randomName.getTimeStamp()),
                       String.valueOf(randomName.getDate()));
               mBinding.tvLogs.setText(mBinding.tvLogs.getText()+text);
           }
       });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.main_view,
                container,
                false);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }
}