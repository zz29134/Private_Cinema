package com.mp.private_cinema.activity;

import android.os.Bundle;

import com.mp.private_cinema.R;
import com.mp.private_cinema.base.BaseActivity;
import com.mp.private_cinema.fragment.Fragment_Main;

public class Activity_Main extends BaseActivity {

    @Override
    protected int getContentID() {
        return R.layout.main_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadRootFragment(R.id.main_activity, Fragment_Main.newInstance());

    }
}
