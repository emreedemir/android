package com.oepp.oeppMobile.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.oepp.oeppMobile.Database.SqlLiteHelper;
import com.oepp.oeppMobile.Models.User;
import com.oepp.oeppMobile.R;
import com.oepp.oeppMobile.Utility.AccountSingleton;
import com.oepp.oeppMobile.Utility.SignUtility;

public class SignUpActivity extends AppCompatActivity
{
    public EditText nameAndSurnameEditText;

    public  EditText emailEditText;

    public  EditText passwordEditText;

    public  EditText passwordRetyEditText;

    public Button signUpButton;

    public Button openSignInActivityButton;

    public AccountSingleton accountSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initiliazeUi();
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        accountSingleton =accountSingleton.getInstance();

        if(accountSingleton.isLoginProvided())
        {
            sendUserToUserMainActivity();
        }
    }

    private void initiliazeUi()
    {
        nameAndSurnameEditText =findViewById(R.id.nameAndSurname_signUpActivity);

        emailEditText =findViewById(R.id.email_signUpActivity);

        passwordEditText =findViewById(R.id.password_signUpActivty);

        passwordRetyEditText =findViewById(R.id.passwordRety_signUpActivty);

        signUpButton =findViewById(R.id.signUpButton);

        openSignInActivityButton =findViewById(R.id.signInActivityButton);

        signUpButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onSignUpButtonPressed();
            }
        });

        openSignInActivityButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onSignInActivityButtonPressed();
            }
        });
    }

    public void onSignUpButtonPressed()
    {
        String nameandsurname =nameAndSurnameEditText.getText().toString();

        String email =emailEditText.getText().toString();

        String password =passwordEditText.getText().toString();

        String passwordRety =passwordRetyEditText.getText().toString();

        if (SignUtility.instance.canSignUp(nameandsurname,email,password,passwordRety))
        {
            if (isUserNotRegister(email,password))
            {
                saveUserToDatabase();
                User user =getUserFromDatabase();
                AccountSingleton.getInstance().setUser(user);
                sendUserToUserMainActivity();
            }
            else
            {
                Toast.makeText(SignUpActivity.this,"Account alreadt exists",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(SignUpActivity.this,"Please fill correctly",Toast.LENGTH_SHORT).show();
        }
    }

    User getUserFromDatabase()
    {
        return  null;
    }

    public void saveUserToDatabase()
    {

    }

    boolean isUserNotRegister(String email,String password)
    {
        return true;
    }

    public  void onSignInActivityButtonPressed()
    {
        sendToUserSignInActivity();
    }

    public void sendUserToUserMainActivity()
    {
        Intent intent = new Intent(SignUpActivity.this,UserMainActivity.class);
        startActivity(intent);
    }

    void sendToUserSignInActivity()
    {
        Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
        startActivity(intent);
    }
}
