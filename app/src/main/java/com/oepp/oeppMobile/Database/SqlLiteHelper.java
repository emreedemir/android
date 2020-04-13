package com.oepp.oeppMobile.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.oepp.oeppMobile.Models.User;

public class SqlLiteHelper extends SQLiteOpenHelper
{
    public static final int Database_Version=1;

    public static final String Database_Name="User_Database";

    public static final String Table_Name="User_Table";

    public static final String User_ID="id";

    public static final String User_email="id";

    public static final String User_nameAndSurname="nameAndSurname";

    public static final String User_password="user_password";

    String DB_Create="CREATE TABLE IF NOT EXISTS " +Table_Name+ "("
        +User_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
        +User_nameAndSurname +" VARCHAR(250),"
        +User_password +" VARCHAR(250),"
        +User_email +" VARCHAR(250)"
        +")";

    Context context;

    public SqlLiteHelper(@Nullable Context context)
    {
        super(context, Database_Name, null, Database_Version);

        this.context=context;

        SQLiteDatabase sqLiteDatabase =getWritableDatabase();

        sqLiteDatabase.execSQL(DB_Create);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
    }

    //Kullanıcı Kayıt Et
    public boolean UserRegistration(User user)
    {
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();

        ContentValues values =new ContentValues();

        values.put(User_nameAndSurname,user.getNameAndSurname());
        values.put(User_email,user.getEmail());
        values.put(User_password,user.getPassword());

        long id=sqLiteDatabase.insert(Table_Name,null,values);

        if(id==-1)
            return false;
        else
            return true;
    }

    //Kullanıcının daha önce kayıt olup olmadıgına bakar
    public boolean isUserRegistered(User user)
    {
        if(getUser(user)==null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

     //Kayıtli kullanıcıyı veritabından alır
    public User getUser(User user)
    {
        SQLiteDatabase database = this.getWritableDatabase();

        String query ="SELECT * FROM "+Table_Name
                +" WHERE " +User_email +" = " +user.getEmail() +" AND "+User_password +" = "+user.getPassword();

        Cursor cursor=database.rawQuery(query,null);

        if(cursor.moveToFirst())
        {
            return user;
        }

        return null;
    }
}
