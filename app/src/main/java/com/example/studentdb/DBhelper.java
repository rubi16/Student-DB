package com.example.studentdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="student.db";
    public static final String TABLE_NAME="student_table";
    public static final String COL1="ID";
    public static final String COL2="FIRST_NAME";
    public static final String COL3="LAST_NAME";
    public static final String COL4="MARKS";
    public DBhelper( Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT , FIRST_NAME TEXT, LAST_NAME TEXT,MARKS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertdata(String FirstName , String LastName, String Marks){
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,FirstName);
        contentValues.put(COL3,LastName);
        contentValues.put(COL4,Marks);
        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if(result== -1)
            return false;
        else
            return  true;
    }

    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean UpdateData(String Id,String Fname , String Lname, String Marks){
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,Id);
        contentValues.put(COL2,Fname);
        contentValues.put(COL3,Lname);
        contentValues.put(COL4,Marks);
        sqLiteDatabase.update(TABLE_NAME,contentValues, "Id=?",new String[] {Id});
        return true;
    }

    public Integer delData(String Id){
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,"ID=?",new String[] {Id});
    }
}
