package com.oepp.oeppMobile.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.oepp.oeppMobile.Database.SqlLiteHelper;
import com.oepp.oeppMobile.Models.User;
import com.oepp.oeppMobile.R;
import com.oepp.oeppMobile.Utility.AccountSingleton;
import com.oepp.oeppMobile.Utility.SignUtility;

public class SignInActivity extends AppCompatActivity
{
    public AccountSingleton accountSingleton;

    public Button signInButton;

    public Button openSignUpActivityButton;

    public EditText emailEditText;

    public EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initiliazeUi();
    }

    @Override
    protected void onStart() {
        super.onStart();

        accountSingleton =accountSingleton.getInstance();

        if(accountSingleton.isLoginProvided())
        {
            sendUserToUserMainActivity();
        }
    }

    private void initiliazeUi()
    {
        signInButton =findViewById(R.id.signInButton);
        openSignUpActivityButton=findViewById(R.id.signUpPageButton);

        emailEditText =findViewById(R.id.email_signInActivty);

        passwordEditText =findViewById(R.id.password_signInActivty);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignInButtonPressed();
            }
        });

        openSignUpActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOpenSignUpButtonPressed();
            }
        });
    }

    void onOpenSignUpButtonPressed()
    {
        sendToSignUpActivity();
    }

    boolean IsUserRegistered(String email,String password)
    {
        SqlLiteHelper sqlLiteHelper = new SqlLiteHelper(getApplicationContext());

        User user = new User(email,password);

        if(sqlLiteHelper.isUserRegistered(user))
        {
            AccountSingleton.getInstance().setUser(user);
            return  true;
        }
        return false;
    }

    void onSignInButtonPressed()
    {
        String email =emailEditText.getText().toString();
        String password =passwordEditText.getText().toString();

        if(SignUtility.instance.canSignIn(email,password))
        {
            if(IsUserRegistered(email,password))
            {
                sendUserToUserMainActivity();
            }
            else
            {
                Toast.makeText(SignInActivity.this,"Any User cant find", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(SignInActivity.this,"Please fill correctly", Toast.LENGTH_LONG).show();
        }
    }

    public  void sendUserToUserMainActivity()
    {
        Intent intent = new Intent(SignInActivity.this,UserMainActivity.class);
        startActivity(intent);
    }

    private void  sendToSignUpActivity()
    {
        Intent intent = new Intent(SignInActivity.this,SignUpActivity.class);
        startActivity(intent);
    }
}
