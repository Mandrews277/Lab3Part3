package com.example.lab3part3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.security.PublicKey;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       String createUserTable = "CREATE TABLE USER_TABLE (username TEXT PRIMARY KEY, password TEXT NOT NULL)";
       sqLiteDatabase.execSQL(createUserTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean register(UserModel usermodel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("username", usermodel.getUsername());
        cv.put("password", usermodel.getPassword());

        long insert = db.insert("USER_TABLE", null, cv);
        db.close();

        if(insert == -1) {
            return false;
        }
        return true;
    }

    public boolean check(String username, String password) {
        String queryString = "SELECT * FROM USER_TABLE";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                String pass = cursor.getString(1);
                if (name.equals(username) && pass.equals(password)) {
                    cursor.close();
                    db.close();
                    return true;
                }
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return false;
    }
}
