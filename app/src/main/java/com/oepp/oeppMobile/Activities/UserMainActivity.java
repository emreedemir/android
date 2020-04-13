package com.oepp.oeppMobile.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.oepp.oeppMobile.R;

public class UserMainActivity extends AppCompatActivity
{
    public Button findCoursesActivityButton;

    public Button signOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
    }
}
