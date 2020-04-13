package com.oepp.oeppMobile.Utility;

import com.oepp.oeppMobile.Models.User;

public class AccountSingleton
{
    public static final AccountSingleton ourInstance = new AccountSingleton();

    public static AccountSingleton getInstance() {
        return ourInstance;
    }

    private User user =null;

    private AccountSingleton()
    {
    }

    public void setUser(User user)
    {
        this.user =user;
    }

    public User getUser()
    {
        return this.user;
    }

    public boolean isLoginProvided()
    {
        if(user !=null)
        {
            return  true;
        }
        else
        {
            return  true;
        }
    }
}
