package com.oepp.oeppMobile.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.oepp.oeppMobile.R;
import com.oepp.oeppMobile.Utility.AccountSingleton;

public class MainActivity extends AppCompatActivity
{
    public Button signUpButton;

    public Button signInButton;

    public Button searchButton;

    public EditText searchEditText;

    public AccountSingleton accountSingleton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initiliazeUi();
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        accountSingleton =AccountSingleton.getInstance();

        if(accountSingleton.isLoginProvided())
        {
            sendUserToUserMainActivity();
        }
    }

    public void initiliazeUi()
    {
        searchEditText =findViewById(R.id.searchTextView_mainActivity);

        searchButton =findViewById(R.id.searchButton_mainActivity);

        signInButton =findViewById(R.id.signInActivityButton_mainActivty);

        signUpButton =findViewById(R.id.signUpActivityButton_mainActivty);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchCourses();
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                sendToSignInActivity();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                sendToSignUpActivity();
            }
        });
    }

    void searchCourses()
    {
        String searchedCourses =searchEditText.getText().toString();
        Toast.makeText(MainActivity.this,"No courses",Toast.LENGTH_SHORT).show();
    }

    void sendToSignInActivity()
    {
        Intent intent = new Intent(MainActivity.this,SignInActivity.class);
        startActivity(intent);
    }

    void sendToSignUpActivity()
    {
        Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
        startActivity(intent);
    }

    private void sendUserToUserMainActivity()
    {
        Intent intent = new Intent(MainActivity.this,UserMainActivity.class);
        startActivity(intent);
    }
}
